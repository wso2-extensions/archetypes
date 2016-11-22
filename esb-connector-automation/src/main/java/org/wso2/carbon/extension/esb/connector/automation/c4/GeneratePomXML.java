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

import org.w3c.dom.Document;
import org.wso2.carbon.extension.esb.connector.automation.util.AutomationConstants;
import org.wso2.carbon.extension.esb.connector.automation.wsdl.ConnectorException;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class GeneratePomXML {

    /**
     * @param connectorName Name of the connector
     * @param path          Path to pom file
     */
    public static void generatePomXml(String connectorName, String path) {
        String filePath = path + "/" + AutomationConstants.POM_XML;
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            doc.getElementsByTagName(AutomationConstants.ARTIFACT_ID).item(0).getFirstChild()
                    .setNodeValue(AutomationConstants.PACKAGE_NAME + "." + connectorName);
            doc.getElementsByTagName(AutomationConstants.CONNECTOR_NAME).item(0).getFirstChild()
                    .setNodeValue(connectorName);
            doc.getElementsByTagName(AutomationConstants.NAME).item(0).getFirstChild()
                    .setNodeValue("WSO2 Carbon - Connector For " + connectorName);
            doc.getElementsByTagName(AutomationConstants.ESB_VERSION).item(0).getFirstChild()
                    .setNodeValue(AutomationConstants.ESB_VERSION_VALUE);
            //write the updated document to file or console
            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.setOutputProperty(OutputKeys.INDENT, AutomationConstants.INDENT_YES);
            transformer.transform(source, result);
        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e) {
            ConnectorException.handleException("CAT203", e);
        }
    }
}