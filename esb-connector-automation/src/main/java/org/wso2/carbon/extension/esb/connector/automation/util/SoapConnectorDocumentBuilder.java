/*
 * Copyright (c) 2005-2014, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.extension.esb.connector.automation.util;

import org.exolab.castor.xml.schema.*;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.wso2.carbon.extension.esb.connector.automation.wsdl.OperationInfo;
import org.wso2.carbon.extension.esb.connector.automation.wsdl.ConnectorException;

import javax.wsdl.Message;
import javax.wsdl.Part;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.*;

/**
 * This class consists of a set of utility methods for building DOC trees
 */
public class SoapConnectorDocumentBuilder {

    public Element rootEl;
    DocumentBuilderFactory docFactory;
    DocumentBuilder docBuilder;
    IntHolder argCounter;
    OperationInfo operationInfo;
    Element argsElement;
    private Document doc;
    private Element root;
    /**
     * WSDL type schema
     */
    private Schema wsdlTypes = null;


    private Set<String> basicTypes;

    /**
     * @throws ConnectorException
     */
    public SoapConnectorDocumentBuilder() {

        docFactory = DocumentBuilderFactory.newInstance();
        basicTypes = new HashSet<String>();
        basicTypes.add(AutomationConstants.TYPE_INTEGER);
        basicTypes.add(AutomationConstants.TYPE_NUMBER);
        basicTypes.add(AutomationConstants.TYPE_STRING);
        basicTypes.add(AutomationConstants.TYPE_ARRAY);
        basicTypes.add(AutomationConstants.FILE);
        argCounter = new IntHolder();
        try {
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            ConnectorException.handleException("CAT103", e);
        }
    }

    public SoapConnectorDocumentBuilder(OperationInfo operationInfo) {
        docFactory = DocumentBuilderFactory.newInstance();
        argCounter = new IntHolder();
        this.operationInfo = operationInfo;

        basicTypes = new HashSet<String>();
        basicTypes.add(AutomationConstants.TYPE_INTEGER);
        basicTypes.add(AutomationConstants.TYPE_NUMBER);
        basicTypes.add(AutomationConstants.TYPE_STRING);
        basicTypes.add(AutomationConstants.TYPE_ARRAY);
        basicTypes.add(AutomationConstants.FILE);

        try {
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            ConnectorException.handleException("CAT103", e);
        }
    }

    /**
     * @param name
     * @param properties
     * @return
     */
    public Element addPayload(String name, String properties) {
        Map<String, String> attributes = propertiesMap(properties);
        root = doc.createElement(name);
        addAttributes(root, attributes);
        doc.appendChild(doc.createComment(AutomationConstants.LICENCE));
        doc.appendChild(root);
        return root;
    }


    /**
     * @param element
     * @return
     */
    public Element addSFInit(Element element) {
        Element format = doc.createElement(AutomationConstants.FORMAT);
        Element envelope = doc.createElement(AutomationConstants.SOAP_ENVELOPE);
        envelope.setAttribute(AutomationConstants.SOAP_NS, AutomationConstants.SOAP_NS_VALUE);
        envelope.setAttribute(AutomationConstants.URN_NS, AutomationConstants.URN_NS_VALUE);
        Element body = doc.createElement(AutomationConstants.SOAP_BODY);
        Element login = doc.createElement(AutomationConstants.URN_LOGIN);
        Element username = doc.createElement(AutomationConstants.URN_USERNAME);
        username.setTextContent("$" + argCounter.getValue());
        argCounter.increase();
        Element password = doc.createElement(AutomationConstants.URN_PASSWORD);
        password.setTextContent("$" + argCounter.getValue());
        argCounter.increase();
        login.appendChild(username);
        login.appendChild(password);

        body.appendChild(login);

        envelope.appendChild(body);
        format.appendChild(envelope);
        element.appendChild(format);

        return format;
    }


    /**
     * @param parent
     * @return
     */
    public Element addPayload(Element parent) {
        Element format = doc.createElement(AutomationConstants.FORMAT);
        Element envelope = doc.createElement(AutomationConstants.SOAP_ENVELOPE);
        envelope.setAttribute(AutomationConstants.SOAP_NS, AutomationConstants.SOAP_NS_VALUE);
        Element header = doc.createElement(AutomationConstants.SOAP_HEADER);
        Element sessionHeader = doc.createElement(AutomationConstants.SESSION_HEADER);
        Element sessionId = doc.createElement(AutomationConstants.SESSION_ID);
        sessionId.setTextContent("$" + argCounter.getValue());
        argCounter.increase();

        operationInfo.setParameters(new Property(AutomationConstants.SESSION_ID, ParamLocation.BODY));

        Element body = doc.createElement(AutomationConstants.SOAP_BODY);
        body.appendChild(rootEl);
        sessionHeader.appendChild(sessionId);
        header.appendChild(sessionHeader);
        envelope.appendChild(header);
        envelope.appendChild(body);
        format.appendChild(envelope);
        parent.appendChild(format);
        return format;
    }

    /**
     * @return
     */
    public Element addPayload() {
        return addPayload(AutomationConstants.CONNECTOR, "");
    }

    /**
     * @param name
     * @param properties
     * @return
     * @throws ConnectorException
     */
    public Element createChild(String name, String properties, boolean includeNs) {
        Element child = doc.createElement(name);
        if (includeNs) {
            child.setAttribute(AutomationConstants.NS_NAME, AutomationConstants.NS_VALUE);
        }
        if (properties == null || properties.isEmpty()) {
            ConnectorException.handleException("properties can't be null or empty");
        }
        Map<String, String> attributes = propertiesMap(properties);
        addAttributes(child, attributes);
        return child;
    }

    /**
     * @param parent
     * @param name
     * @param text
     * @param properties
     * @return
     * @throws ConnectorException
     */
    public Element addChildTo(Element parent, String name, String text, String properties,
                              boolean includeNs) {
        if (parent == null) {
            ConnectorException.handleException("Root element doesn't exist in the XML document");
        }
        Map<String, String> attributes = propertiesMap(properties);
        Element child = doc.createElement(name);
        if (includeNs) {
            child.setAttribute(AutomationConstants.NS_NAME, AutomationConstants.NS_VALUE);
        }
        addAttributes(child, attributes);
        child.appendChild(doc.createTextNode(text));
        if (parent != null) {
            parent.appendChild(child);
        }
        return child;
    }

    /**
     * @param parent
     * @param name
     * @param text
     * @return
     * @throws ConnectorException
     */
    public Element addChildTo(Element parent, String name, String text, boolean includeNs) {
        if (parent == null) {
            ConnectorException.handleException("Root element doesn't exist in the XML document");
        }
        Element child = doc.createElement(name);
        if (includeNs) {
            child.setAttribute(AutomationConstants.NS_NAME, AutomationConstants.NS_VALUE);
        }
        child.appendChild(doc.createTextNode(text));
        if (parent != null) {
            parent.appendChild(child);
        }
        return child;
    }

    /**
     * @param parent
     * @param name
     * @return
     * @throws ConnectorException
     */
    public Element addChildTo(Element parent, String name, boolean includeNs) {
        return addChildTo(parent, name, "", includeNs);
    }

    /**
     * Save this XMl document to the given location
     *
     * @param location
     * @throws ConnectorException
     */
    public void save(String location) {
        if (location == null || location.isEmpty()) {
            ConnectorException.handleException("Document saving path can't be null or empty");
        }
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException e) {
            ConnectorException.handleException("CAT201", e);
        }
        // initialize pretty printer
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, AutomationConstants.NO);
        transformer.setOutputProperty(OutputKeys.METHOD, AutomationConstants.XML);
        transformer.setOutputProperty(OutputKeys.INDENT, AutomationConstants.INDENT_YES);
        transformer.setOutputProperty(OutputKeys.ENCODING, AutomationConstants.ENCODING);
        transformer.setOutputProperty(OutputKeys.MEDIA_TYPE, AutomationConstants.XML);
        transformer.setOutputProperty(AutomationConstants.IA_NAME, AutomationConstants.IA_VALUE);
        Result output = new StreamResult(new File(location));
        Source input = new DOMSource(doc);
        try {
            transformer.transform(input, output);
        } catch (TransformerException e) {
            ConnectorException.handleException("CAT200", e);
        }
    }
    // Listing some private helper methods

    /**
     * @param node
     * @param attributes
     */
    private void addAttributes(Element node, Map<String, String> attributes) {
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            Attr attr = doc.createAttribute(entry.getKey());
            attr.setValue(entry.getValue());
            node.setAttributeNode(attr);
        }
    }

    /**
     * @param properties
     * @return
     */
    private Map<String, String> propertiesMap(String properties) {
        Map<String, String> props = new HashMap<String, String>();
        if (properties.length() > 0) {
            String[] keyVals = properties.split(";");
            for (String keyVal : keyVals) {
                String[] keyValArr = keyVal.split("~");
                props.put(keyValArr[0], keyValArr[1]);
            }
        }
        return props;
    }

    /**
     * Builds and adds parameters to the supplied info object
     * given a SOAP Message definition (from WSDL)
     **/
    public Element buildMessageText() {
        Message msg = operationInfo.getInMsg();
        if (msg != null) {
            // Set the name of the operation's input message
            operationInfo.setInputMessageName(msg.getQName().getLocalPart());
        }
        // Create the root message element
        int val = argCounter.getValue();
        rootEl = doc.createElement(operationInfo.getTargetMethodName());
        rootEl.setAttribute(AutomationConstants.NS_NAME, operationInfo.getNamespaceURI());
        // Get the message parts
        List msgParts = msg.getOrderedParts(null);
        // Process each part
        Iterator iter = msgParts.iterator();
        while (iter.hasNext()) {
            // Get each part
            Part part = (Part) iter.next();
            // Add content for each message part
            String partName = part.getName();
            wsdlTypes = operationInfo.getWsdlType();
            if (partName != null) {
                // Determine if this message part's type is complex
                XMLType xmlType = getXMLType(part);
                if (xmlType != null && xmlType.isComplexType()) {
                    // Build the message structure
                    buildComplexPart((ComplexType) xmlType, rootEl);
                } else {
                    // Build the element that will be added to the message
                    Element partElem = doc.createElement(partName);
                    // Add some default content as just a place holder
                    argCounter.increase();
                    partElem.setTextContent("$" + (val));
                    if (basicTypes.contains(part.getTypeName().getLocalPart())) {
                        operationInfo.setParameters(new Property(partName, " ", part.getTypeName().getLocalPart()));
                    }
                    if (operationInfo.getStyle().equalsIgnoreCase("rpc")) {
                        // If this is an RPC style operation, we need to include some type information
                        partElem.setAttribute(AutomationConstants.TYPE, part.getTypeName().getLocalPart());
                    }
                    // Add this message part
                    rootEl.appendChild(partElem);
                }
            }
        }
        return rootEl;
    }

    /**
     * Populate a JDOM element using the complex XML type passed in
     *
     * @param complexType The complex XML type to build the element for
     * @param partElem    The JDOM element to build content for
     */
    protected void buildComplexPart(ComplexType complexType, org.w3c.dom.Element partElem) {
        // Find the group
        Enumeration particleEnum = complexType.enumerate();
        Group group = null;
        while (particleEnum.hasMoreElements()) {
            Particle particle = (Particle) particleEnum.nextElement();
            if (particle instanceof Group) {
                group = (Group) particle;
                break;
            }
        }
        if (group != null) {
            Enumeration groupEnum = group.enumerate();
            while (groupEnum.hasMoreElements()) {
                Structure item = (Structure) groupEnum.nextElement();
                int val = argCounter.getValue();
                argCounter.increase();
                if (item.getStructureType() == Structure.ELEMENT) {
                    ElementDecl elementDecl = (ElementDecl) item;
                    Element childElem = doc.createElement(elementDecl.getName());
                    XMLType xmlType = elementDecl.getType();
                    if (xmlType != null && xmlType.isComplexType()) {
                        buildComplexPart((ComplexType) xmlType, childElem);
                    } else {
                        childElem.setTextContent("$" + (val));
                        if (xmlType != null) {
                            operationInfo.setParameters(new Property(elementDecl.getName(), " ", xmlType.getName()));
                        }
                    }
                    partElem.appendChild(childElem);
                }
            }
        }
    }

    /**
     * Gets an XML Type from a SOAP Message Part read from WSDL
     *
     * @param part The SOAP Message part
     * @return The corresponding XML Type is returned.
     * null is returned if not found or if a simple type
     */
    protected XMLType getXMLType(Part part) {
        if (wsdlTypes == null) {
            // No defined types, Nothing to do
            return null;
        }
        // Find the XML type
        XMLType xmlType = null;
        // First see if there is a defined element
        if (part.getElementName() != null) {
            // Get the element name
            String elemName = part.getElementName().getLocalPart();
            // Find the element declaration
            ElementDecl elemDecl = wsdlTypes.getElementDecl(elemName);
            if (elemDecl != null) {
                // From the element declaration get the XML type
                xmlType = elemDecl.getType();
            }
        }
        return xmlType;
    }

    /**
     * This method builds Synapse parameter and values for these parameters will be
     * provided by the caller. Additionally, parameters which are related to properties
     * of model objects prefixed by object name.
     *
     * @param parent
     * @param parameters
     * @param docBuilder
     * @throws ConnectorException
     */
    public void buildSynapseParameters(Element parent, List<Property> parameters,
                                       SoapConnectorDocumentBuilder docBuilder) {
        for (Property parameter : parameters) {
            String prop = AutomationConstants.NAME + "~" + parameter.getName() + ";" + AutomationConstants.DESCRIPTION + "~" + parameter.getDescription();
            docBuilder.addChildTo(parent, AutomationConstants.PARAMETER, "", prop, true);
        }
    }

    /**
     * @param parameters
     * @param docBuilder
     * @param type
     * @return
     * @throws ConnectorException
     */
    public Element builSoapPayload(List<Property> parameters,
                                   SoapConnectorDocumentBuilder docBuilder, String type) {
        boolean haveBodayTypeParameters = !parameters.isEmpty();
        List<Element> args = new ArrayList<Element>();
        if (haveBodayTypeParameters) {
            Element payloadFactory = docBuilder.createChild(AutomationConstants.PAYLOAD_FACTORY, AutomationConstants.MEDIA_TYPE + "~" + AutomationConstants.XML, true);
            //add payloadfactory
            if (type.equals(AutomationConstants.METHOD)) {
                docBuilder.addPayload(payloadFactory);
            } else {
                docBuilder.addSFInit(payloadFactory);
            }
            for (Property parameter : parameters) {
                List<Element> elementArg = buildArguments(parameter, docBuilder);
                args.addAll(elementArg);
            }
            argsElement = docBuilder.addChildTo(payloadFactory, AutomationConstants.ARGS, "", true);
            for (Element arg : args) {
                argsElement.appendChild(arg);
            }
            payloadFactory.appendChild(argsElement);
            return payloadFactory;
        }
        return null;
    }

    /**
     * @param parameter
     * @param docBuilder
     * @return
     * @throws ConnectorException
     */
    private List<Element> buildArguments(Property parameter,
                                         SoapConnectorDocumentBuilder docBuilder) {
        List<Element> args = new ArrayList<Element>();
        String props;
        if (parameter.getParameterLocation() == ParamLocation.BODY) {
            // basic types are sending in the body
            props = AutomationConstants.CONTEXT_EXPRESSION + parameter.getName();
        } else {
            props = AutomationConstants.FUNC_EXPRESSION + parameter.getName();
        }
        Element arg = docBuilder.createChild(AutomationConstants.ARG, props, true);
        args.add(arg);
        return args;
    }

    /**
     *
     */
    private class IntHolder {
        private int value;

        public IntHolder() {
            this.value = 1;
        }

        public void increase() {
            this.value += 1;
        }

        public int getValue() {
            return value;
        }
    }
}