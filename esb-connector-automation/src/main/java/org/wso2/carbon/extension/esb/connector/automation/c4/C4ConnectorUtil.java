package org.wso2.carbon.extension.esb.connector.automation.c4;
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

import org.w3c.dom.Element;
import org.wso2.carbon.extension.esb.connector.automation.util.AutomationConstants;
import org.wso2.carbon.extension.esb.connector.automation.util.Property;
import org.wso2.carbon.extension.esb.connector.automation.util.SoapConnectorDocumentBuilder;
import org.wso2.carbon.extension.esb.connector.automation.wsdl.ConnectorException;
import org.wso2.carbon.extension.esb.connector.automation.wsdl.OperationInfo;

import java.util.List;

public class C4ConnectorUtil {

    /**
     * @param operationInfo operation information
     * @param path          path to save methods
     * @param mimeType      The content type
     * @throws ConnectorException
     */
    public void generateMethodXML(OperationInfo operationInfo,
                                  String path, String mimeType) throws ConnectorException {
        // building XML file using this helper class
        SoapConnectorDocumentBuilder docBuilder = new SoapConnectorDocumentBuilder(operationInfo);
        docBuilder.buildMessageText();
        String name = operationInfo.getTargetMethodName();
        String endPoint = operationInfo.getTargetURL();

        List<Property> userProperties;
        userProperties = operationInfo.getParameters();

        String attr = AutomationConstants.NAME + "~" + name + ";"
                + AutomationConstants.NS_NAME + "~" + AutomationConstants.NS_VALUE;
        // writing the root tag
        Element root = docBuilder.addPayload(AutomationConstants.TEMPLATE, attr);

        // building parameters section if any
        docBuilder.buildSynapseParameters(root, userProperties, docBuilder);

        Element sequence = docBuilder.addChildTo(root, AutomationConstants.SEQUENCE, "", true);
        sequence.setAttribute(AutomationConstants.NS_NAME, AutomationConstants.NS_VALUE);

        // Note: Ideally we should use consume MIME type and based on its value
        // we should decide what payload factory we are going to create. However,
        // consume MIME is a required field in the specification.

        Element payload = docBuilder.builSoapPayload(userProperties, docBuilder, AutomationConstants.METHOD);
        if (payload != null) {
            sequence.appendChild(payload);
            String props = AutomationConstants.MESSAGE_TYPE_PROPERTY + mimeType;
            docBuilder.addChildTo(sequence, AutomationConstants.PROPERTY, "", props, true);
        }
        docBuilder.addChildTo(sequence, AutomationConstants.PROPERTY, "",
                AutomationConstants.NAME + "~" + AutomationConstants.HTTP_METHOD + ";"
                        + AutomationConstants.VALUE + "~" + AutomationConstants.POST + ";"
                        + AutomationConstants.SCOPE + "~" + AutomationConstants.AXIS2, true);
        docBuilder.addChildTo(sequence, AutomationConstants.HEADER, "",
                AutomationConstants.NAME + "~" + AutomationConstants.SOAP_ACTION + ";"
                        + AutomationConstants.VALUE + "~''", true);
        docBuilder.addChildTo(sequence, AutomationConstants.HEADER, "",
                AutomationConstants.NAME + "~" + AutomationConstants.TO + ";"
                        + AutomationConstants.VALUE + "~" + endPoint, true);

        Element call = docBuilder.addChildTo(sequence, AutomationConstants.CALL, "", true);
        Element endpointEle = docBuilder.addChildTo(call, AutomationConstants.ENDPOINT, "", true);
        docBuilder.addChildTo(endpointEle, AutomationConstants.DEFAULT, "",
                AutomationConstants.FORMAT + "~" + AutomationConstants.SOAP_EP, true);
        docBuilder.save(path + "/" + name + AutomationConstants.XML_EXTENSION);

    }

    /**
     * This method builds the XML file for each component of the connector
     *
     * @param component
     * @param path
     * @param componentName
     * @param isInitExist
     * @throws ConnectorException
     */
    public void generateComponentXML(List component, String path, String componentName, Boolean isInitExist) {
        SoapConnectorDocumentBuilder docBuilder = new SoapConnectorDocumentBuilder();
        String attributes = AutomationConstants.NAME + "~" + componentName + ";"
                + AutomationConstants.TYPE + "~" + AutomationConstants.TYPE_VALUE;
        // writing the root tag
        Element root = docBuilder.addPayload(AutomationConstants.COMPONENT, attributes);
        Element subComponent = docBuilder.addChildTo(root, AutomationConstants.SUB_COMPONENT, false);
        for (Object operation : component) {
            String prop = AutomationConstants.NAME + "~" + operation;
            Element compoEle = docBuilder.addChildTo(subComponent,
                    AutomationConstants.COMPONENT, "", prop, false);
            docBuilder.addChildTo(compoEle, AutomationConstants.FILE,
                    operation + AutomationConstants.XML_EXTENSION, false);
            docBuilder.addChildTo(compoEle, AutomationConstants.DESCRIPTION, "", false);
        }
        if (isInitExist) {
            //Init operation
            String prop = AutomationConstants.NAME + "~" + AutomationConstants.INIT;
            Element compoEle = docBuilder.addChildTo(subComponent,
                    AutomationConstants.COMPONENT, "", prop, false);
            docBuilder.addChildTo(compoEle, AutomationConstants.FILE, AutomationConstants.INIT_XML, false);
            docBuilder.addChildTo(compoEle, AutomationConstants.DESCRIPTION, "", false);
        }
        docBuilder.save(path + "/" + AutomationConstants.COMPONENT_XML);
    }

    /**
     * @param name
     * @param path
     * @param connector
     * @throws ConnectorException
     */
    public void generateConnectorXML(String name, String path, List connector) {
        // building XML file
        SoapConnectorDocumentBuilder docBuilder = new SoapConnectorDocumentBuilder();

        // writing the root tag
        Element root = docBuilder.addPayload();
        String prop = AutomationConstants.NAME + "~" + name + ";"
                + AutomationConstants.PACKAGE + "~" + AutomationConstants.PACKAGE_NAME;
        Element component = docBuilder.addChildTo(root, AutomationConstants.COMPONENT, "", prop, false);
        for (Object dependency : connector) {
            docBuilder.addChildTo(component, AutomationConstants.DEPENDENCY, "",
                    AutomationConstants.COMPONENT + "~" + dependency.toString(), false);
        }
        // adding the description
        root.appendChild(component);
        docBuilder.addChildTo(component, AutomationConstants.DESCRIPTION, " ", false);
        docBuilder.save(path + "/" + AutomationConstants.CONNECTOR_XML);
    }
}