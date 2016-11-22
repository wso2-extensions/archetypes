package org.wso2.carbon.extension.esb.connector.automation.init;
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

import java.util.ArrayList;
import java.util.List;

public class GenerateSFInit implements Init {

    /**
     * @param path     path to the file
     * @param mimeType content type
     * @throws ConnectorException
     */
    public void generateInitXML(String path, String mimeType) {
        // building XML file using this helper class
        SoapConnectorDocumentBuilder docBuilder = new SoapConnectorDocumentBuilder();
        Property username = new Property(AutomationConstants.USERNAME, " ", AutomationConstants.TYPE_STRING);
        Property password = new Property(AutomationConstants.PASSWORD, " ", AutomationConstants.TYPE_STRING);
        List<Property> userProperties = new ArrayList<Property>();
        userProperties.add(username);
        userProperties.add(password);
        String attributes = AutomationConstants.NAME + "~" + AutomationConstants.INIT + ";"
                + AutomationConstants.NS_NAME + "~" + AutomationConstants.NS_VALUE;
        // writing the root tag
        Element root = docBuilder.addPayload(AutomationConstants.TEMPLATE, attributes);
        // building parameters section if any
        docBuilder.buildSynapseParameters(root, userProperties, docBuilder);
        Element sequence = docBuilder.addChildTo(root, AutomationConstants.SEQUENCE, "", true);
        sequence.setAttribute(AutomationConstants.NS_NAME, AutomationConstants.NS_VALUE);
        // Note: Ideally we should use consume MIME type and based on its value
        // we should decide what payload factory we are going to create. However,
        // consume MIME is a required field in the specification.
        Element payload = docBuilder.builSoapPayload(userProperties, docBuilder, AutomationConstants.INIT);
        if (payload != null) {
            sequence.appendChild(payload);
            String props = AutomationConstants.MESSAGE_TYPE_PROPERTY + mimeType;
            docBuilder.addChildTo(sequence, AutomationConstants.PROPERTY, "", props, true);
        }
        docBuilder.addChildTo(sequence, AutomationConstants.PROPERTY, "", AutomationConstants.NAME + "~" + AutomationConstants.HTTP_METHOD + ";"
                + AutomationConstants.VALUE + "~" + AutomationConstants.POST + ";" + AutomationConstants.SCOPE + "~" + AutomationConstants.AXIS2, true);
        docBuilder.addChildTo(sequence, AutomationConstants.HEADER, "", AutomationConstants.NAME + "~" + AutomationConstants.SOAP_ACTION + ";"
                + AutomationConstants.VALUE + "~''", true);
        docBuilder.addChildTo(sequence, AutomationConstants.HEADER, "", AutomationConstants.NAME + "~" + AutomationConstants.TO + ";"
                + AutomationConstants.VALUE + "~" + AutomationConstants.SALESFORCE_LOGIN_ENDPOINT, true);
        Element call = docBuilder.addChildTo(sequence, AutomationConstants.CALL, "", true);
        Element endpointEle = docBuilder.addChildTo(call, AutomationConstants.ENDPOINT, "", true);
        docBuilder.addChildTo(endpointEle, AutomationConstants.DEFAULT, "", AutomationConstants.FORMAT + "~" + AutomationConstants.SOAP_EP, true);
        docBuilder.addChildTo(sequence, AutomationConstants.PROPERTY, "", AutomationConstants.SF_GET_SESSION_ID, true);
        docBuilder.save(path + "/" + AutomationConstants.INIT_XML);
    }
}