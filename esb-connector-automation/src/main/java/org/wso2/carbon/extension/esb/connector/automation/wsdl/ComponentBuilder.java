package org.wso2.carbon.extension.esb.connector.automation.wsdl;
/*
* Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import org.exolab.castor.xml.schema.Schema;
import org.exolab.castor.xml.schema.SimpleTypesFactory;
import org.jdom.Namespace;
import org.jdom.input.DOMBuilder;
import org.wso2.carbon.extension.esb.connector.automation.util.AutomationConstants;

import javax.wsdl.*;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.wsdl.extensions.UnknownExtensibilityElement;
import javax.wsdl.extensions.soap.SOAPAddress;
import javax.wsdl.extensions.soap.SOAPBinding;
import javax.wsdl.extensions.soap.SOAPBody;
import javax.wsdl.extensions.soap.SOAPOperation;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.xml.namespace.QName;
import java.util.*;


/**
 * A class that defines methods for building components to invoke a web service
 * by analyzing a WSDL document.
 */

public class ComponentBuilder {

    /**
     * The default SOAP encoding to use.
     */
    public final static String DEFAULT_SOAP_ENCODING_STYLE = "http://schemas.xmlsoap.org/soap/encoding/";
    /**
     * WSDL Factory instance
     */
    WSDLFactory wsdlFactory = null;

    /**
     * Simple types factory
     */
    SimpleTypesFactory simpleTypesFactory = null;

    /**
     * WSDL type schema
     */
    private Schema wsdlTypes = null;

    /**
     * Constructor
     */
    public ComponentBuilder() {
        try {
            wsdlFactory = WSDLFactory.newInstance();
            simpleTypesFactory = new SimpleTypesFactory();
        } catch (Throwable t) {
            ConnectorException.handleException(t.getMessage());
        }
    }

    /**
     * Returns the desired ExtensibilityElement if found in the List
     *
     * @param extensibilityElements The list of extensibility elements to search
     * @param elementType           The element type to find
     * @return Returns the first matching element of type found in the list
     */
    private static ExtensibilityElement findExtensibilityElement(List extensibilityElements, String elementType) {
        if (extensibilityElements != null) {
            for (Object extensibilityElement : extensibilityElements) {
                ExtensibilityElement element = (ExtensibilityElement) extensibilityElement;
                if (element.getElementType().getLocalPart().equalsIgnoreCase(elementType)) {
                    // Found it
                    return element;
                }
            }
        }
        return null;
    }

    /**
     * Builds a List of ServiceInfo components for each Service defined in a WSDL Document
     *
     * @param wsdlURI A URI that points to a WSDL XML Definition. Can be a filename or URL.
     * @return A List of SoapComponent objects populated for each service defined
     * in a WSDL document. A null is returned if the document can't be read.
     */
    public List buildComponents(String wsdlURI) {
        // The list of components that will be returned
        List serviceList = Collections.synchronizedList(new ArrayList());

        // Create the WSDL Reader object
        WSDLReader reader = wsdlFactory.newWSDLReader();

        // Read the WSDL and get the top-level Definition object
        Definition wsdlDefinition = null;
        try {
            wsdlDefinition = reader.readWSDL(null, wsdlURI);
        } catch (WSDLException e) {
            ConnectorException.handleException("CAT001", e);
        }

        // Create a castor schema from the types element defined in WSDL
        // This method will return null if there are types defined in the WSDL
        wsdlTypes = createSchemaFromTypes(wsdlDefinition);

        // Get the services defined in the document
        Map services = null;
        if (wsdlDefinition != null) {
            services = wsdlDefinition.getServices();
        }

        if (services != null) {
            // Create a component for each service defined
            Iterator serviceIter = services.values().iterator();

            for (int i = 0; serviceIter.hasNext(); i++) {
                // Create a new ServiceInfo component for each service found
                ServiceInfo serviceInfo = new ServiceInfo();

                // Populate the new component from the WSDL Definition read
                populateComponent(serviceInfo, (Service) serviceIter.next());

                // Add the new component to the List to be returned
                serviceList.add(serviceInfo);
            }
        }

        // return the List of services we created
        return serviceList;
    }

    /**
     * Creates a castor schema based on the types defined by a WSDL document
     *
     * @param wsdlDefinition The WSDL4J instance of a WSDL definition.
     * @return A castor schema is returned if the WSDL definition contains
     * a types element. null is returned otherwise.
     */
    protected Schema createSchemaFromTypes(Definition wsdlDefinition) {
        // Get the schema element from the WSDL definition
        org.w3c.dom.Element schemaElement = null;

        if (wsdlDefinition.getTypes() != null) {
            ExtensibilityElement schemaExtElem = findExtensibilityElement(wsdlDefinition.getTypes().getExtensibilityElements(), "schema");
            if (schemaExtElem != null && schemaExtElem instanceof UnknownExtensibilityElement) {
                schemaElement = ((UnknownExtensibilityElement) schemaExtElem).getElement();
            }
        }

        if (schemaElement == null) {
            // No schema to read
            System.err.println("Unable to find schema extensibility element in WSDL");
            return null;
        }

        // Convert from DOM to JDOM
        DOMBuilder domBuilder = new DOMBuilder();
        org.jdom.Element jdomSchemaElement = domBuilder.build(schemaElement);

        if (jdomSchemaElement == null) {
            System.err.println("Unable to read schema defined in WSDL");
            return null;
        }

        // Add namespaces from the WSDL
        Map namespaces = wsdlDefinition.getNamespaces();

        if (namespaces != null && !namespaces.isEmpty()) {

            for (Object o : namespaces.keySet()) {
                String nsPrefix = (String) o;
                String nsURI = (String) namespaces.get(nsPrefix);

                if (nsPrefix != null && nsPrefix.length() > 0) {
                    Namespace nsDecl = Namespace.getNamespace(nsPrefix, nsURI);
                    jdomSchemaElement.addNamespaceDeclaration(nsDecl);
                }
            }
        }

        // Make sure that the types element is not processed
        jdomSchemaElement.detach();

        // Convert it into a Castor schema instance
        Schema schema;
        schema = XMLSupport.convertElementToSchema(jdomSchemaElement);
        // Return it
        return schema;
    }

    /**
     * Populates a ServiceInfo instance from the specified Service definiition
     *
     * @param component The component to populate
     * @param service   The Service to populate from
     * @return The populated component is returned representing the Service parameter
     */
    private ServiceInfo populateComponent(ServiceInfo component, Service service) {
        // Get the qualified service name information
        QName qName = service.getQName();

        // Get the service's namespace URI
        String namespace = qName.getNamespaceURI();

        // Use the local part of the qualified name for the component's name
        String name = qName.getLocalPart();

        // Set the name
        component.setName(name);

        // Get the defined ports for this service
        Map ports = service.getPorts();

        // Use the Ports to create OperationInfos for all request/response messages defined

        for (Object o : ports.values()) {
            // Get the next defined port
            Port port = (Port) o;

            // Get the Port's Binding
            Binding binding = port.getBinding();

            // Now we will create operations from the Binding information
            List operations = buildOperations(binding);

            // Process objects built from the binding information
            for (Object operation1 : operations) {
                OperationInfo operation = (OperationInfo) operation1;

                // Set the namespace URI for the operation.
                operation.setNamespaceURI(namespace);

                // Find the SOAP target URL
                ExtensibilityElement addrElem = findExtensibilityElement(port.getExtensibilityElements(), "address");

                if (addrElem != null && addrElem instanceof SOAPAddress) {
                    // Set the SOAP target URL
                    SOAPAddress soapAddr = (SOAPAddress) addrElem;
                    operation.setTargetURL(soapAddr.getLocationURI());
                }

                // Add the operation info to the component
                component.addOperation(operation);
            }
        }

        return component;
    }

    /**
     * Creates Info objects for each Binding Operation defined in a Port Binding
     *
     * @param binding The Binding that defines Binding Operations used to build info objects from
     * @return A List of built and populated OperationInfos is returned for each Binding Operation
     */
    private List buildOperations(Binding binding) {
        // Create the array of info objects to be returned
        List operationInfos = new ArrayList();

        // Get the list of Binding Operations from the passed binding
        List operations = binding.getBindingOperations();

        if (operations != null && !operations.isEmpty()) {
            // Determine encoding
            ExtensibilityElement soapBindingElem = findExtensibilityElement(binding.getExtensibilityElements(), "binding");
            String style = AutomationConstants.DOCUMENT; // default

            if (soapBindingElem != null && soapBindingElem instanceof SOAPBinding) {
                SOAPBinding soapBinding = (SOAPBinding) soapBindingElem;
                style = soapBinding.getStyle();
            }

            // For each binding operation, create a new OperationInfo
            for (Object operation : operations) {
                BindingOperation oper = (BindingOperation) operation;

                // We currently only support soap:operation bindings
                // filter out http:operations for now until we can dispatch them properly
                ExtensibilityElement operElem = findExtensibilityElement(oper.getExtensibilityElements(), "operation");

                if (operElem != null && operElem instanceof SOAPOperation) {
                    // Create a new operation info
                    OperationInfo operationInfo = new OperationInfo(style);

                    // Populate it from the Binding Operation
                    buildOperation(operationInfo, oper);

                    // Add to the return list
                    operationInfos.add(operationInfo);
                }
            }
        }
        return operationInfos;
    }

    /**
     * Populates an OperationInfo from the specified Binding Operation
     *
     * @param operationInfo The component to populate
     * @param bindingOper   A Binding Operation to define the OperationInfo from
     * @return The populated OperationInfo object is returned.
     */
    private OperationInfo buildOperation(OperationInfo operationInfo, BindingOperation bindingOper) {
        // Get the operation
        Operation operation = bindingOper.getOperation();

        // Set the name using the operation name
        operationInfo.setTargetMethodName(operation.getName());

        // Set the action URI
        ExtensibilityElement operElem = findExtensibilityElement(bindingOper.getExtensibilityElements(), "operation");

        if (operElem != null && operElem instanceof SOAPOperation) {
            SOAPOperation soapOperation = (SOAPOperation) operElem;
            operationInfo.setSoapActionURI(soapOperation.getSoapActionURI());
        }

        // Get the Binding Input
        BindingInput bindingInput = bindingOper.getBindingInput();

        // Get the SOAP Body
        ExtensibilityElement bodyElem = findExtensibilityElement(bindingInput.getExtensibilityElements(), "body");

        if (bodyElem != null && bodyElem instanceof SOAPBody) {
            SOAPBody soapBody = (SOAPBody) bodyElem;

            // The SOAP Body contains the encoding styles
            List styles = soapBody.getEncodingStyles();
            String encodingStyle = null;

            if (styles != null) {
                // Use the first in the list
                encodingStyle = styles.get(0).toString();
            }

            if (encodingStyle == null) {
                // An ecoding style was not found, give it a default
                encodingStyle = DEFAULT_SOAP_ENCODING_STYLE;
            }

            // Assign the encoding style value
            operationInfo.setEncodingStyle(encodingStyle);

            // The SOAP Body contains the target object's namespace URI.
            operationInfo.setTargetObjectURI(soapBody.getNamespaceURI());
        }
        // Get the Operation's Input definition
        Input inDef = operation.getInput();
        if (inDef != null) {
            // Build input parameters
            Message inMsg = inDef.getMessage();

            if (inMsg != null) {
                operationInfo.setInMessage(inMsg);
            }
        }
        operationInfo.setWSDLType(wsdlTypes);
        // Finished, return the populated object
        return operationInfo;
    }
}