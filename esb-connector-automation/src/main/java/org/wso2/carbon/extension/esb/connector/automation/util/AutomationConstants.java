package org.wso2.carbon.extension.esb.connector.automation.util;
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

public class AutomationConstants {

    public static final String NS_NAME = "xmlns";
    public static final String NS_VALUE = "http://ws.apache.org/ns/synapse";
    public static final String SOAP_ENVELOPE = "SOAP-ENV:Envelope";
    public static final String SOAP_HEADER = "SOAP-ENV:Header";
    public static final String SOAP_BODY = "SOAP-ENV:Body";
    public static final String SESSION_HEADER = "SessionHeader";
    public static final String SESSION_ID = "sessionId";
    public static final String SOAP_NS = "xmlns:SOAP-ENV";
    public static final String SOAP_NS_VALUE = "http://schemas.xmlsoap.org/soap/envelope/";
    public static final String URN_NS = "xmlns:urn";
    public static final String URN_NS_VALUE = "urn:partner.soap.sforce.com";
    public static final String URN_LOGIN = "urn:login";
    public static final String URN_USERNAME = "urn:username";
    public static final String URN_PASSWORD = "urn:password";
    public static final String CONNECTOR_NAME = "connector.name";
    public static final String ARTIFACT_ID = "artifactId";
    public static final String ESB_VERSION = "esb.version";
    public static final String ESB_VERSION_VALUE = "5.0.0";
    public static final String DEPENDENCY = "dependency";
    public static final String DESCRIPTION = "description";
    public static final String TEMPLATE = "template";
    public static final String CONNECTOR = "connector";
    public static final String COMPONENT = "component";
    public static final String SUB_COMPONENT = "subComponents";
    public static final String COMPONENT_XML = "component.xml";
    public static final String SEQUENCE = "sequence";
    public static final String PROPERTY = "property";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String DOCUMENT = "document";
    public static final String NAME = "name";
    public static final String INIT = "init";
    public static final String METHOD = "method";
    public static final String TYPE_STRING = "string";
    public static final String TYPE_NUMBER = "number";
    public static final String TYPE_INTEGER = "integer";
    public static final String TYPE_ARRAY = "array";
    public static final String XML = "xml";
    public static final String ARG = "arg";
    public static final String ARGS = "args";
    public static final String CALL = "call";
    public static final String ENDPOINT = "endpoint";
    public static final String PAYLOAD_FACTORY = "payloadFactory";
    public static final String MEDIA_TYPE = "media-type";
    public static final String TEXT_XML = "text/xml";
    public static final String IA_NAME = "{http://xml.apache.org/xslt}indent-amount";
    public static final String IA_VALUE = "4";
    public static final String ENCODING = "UTF-8";
    public static final String NO = "no";
    public static final String SCOPE = "scope";
    public static final String PARAMETER = "parameter";
    public static final String FUNC_EXPRESSION = "expression~$func:";
    public static final String CONTEXT_EXPRESSION = "expression~$ctx:";
    public static final String VALUE = "value";
    public static final String TYPE = "type";
    public static final String TYPE_VALUE = "synapse/template";
    public static final String AXIS2 = "axis2";
    public static final String FILE = "file";
    public static final String HTTP_METHOD = "HTTP_METHOD";
    public static final String POST = "POST";
    public static final String TO = "To";
    public static final String SOAP_EP = "soap11";
    public static final String SOAP_ACTION = "Action";
    public static final String PACKAGE_NAME = "org.wso2.carbon.connectors";
    public static final String PACKAGE = "package";
    public static final String SALESFORCE = "salesforce";
    public static final String RESOURCE_PATH = "/src/main/resources/";
    public static final String HEADER = "header";
    public static final String FORMAT = "format";
    public static final String MESSAGE_TYPE = "messageType";
    public static final String MESSAGE_TYPE_PROPERTY = NAME + "~" + MESSAGE_TYPE + ";" + SCOPE + "~" + AXIS2 + ";" + VALUE + "~";
    public static final String DEFAULT = "default";
    public static final String INIT_XML = "init.xml";
    public static final String POM_XML = "pom.xml";
    public static final String CONNECTOR_XML = "connector.xml";
    public static final String XML_EXTENSION = ".xml";
    public static final String SALESFORCE_LOGIN_ENDPOINT = "https://login.salesforce.com/services/Soap/u/37.0";
    public static final String SF_GET_SESSION_ID = "expression~//ns:loginResponse/ns:result/ns:sessionId/text();name~sessionId;xmlns:ns~urn:partner.soap.sforce.com";
    public static final String ARCHETYPE_RESOURCE_PATH = "./archetype-resources";
    public static final String INDENT_YES = "yes";
    public static final String WELCOME_MESSAGE =
            "\n############################### \n" +
                    "## Connector Automation Tool ## \n" +
                    "############################### \n";

    public static final String LICENCE =
            " \n  ~ Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.\n" +
                    " ~\n" +
                    " ~  WSO2 Inc. licenses this file to you under the Apache License,\n" +
                    " ~  Version 2.0 (the \"License\"); you may not use this file except\n" +
                    " ~  in compliance with the License.\n" +
                    " ~  You may obtain a copy of the License at\n" +
                    " ~\n" +
                    " ~    http://www.apache.org/licenses/LICENSE-2.0\n" +
                    " ~\n" +
                    " ~  Unless required by applicable law or agreed to in writing,\n" +
                    " ~  software distributed under the License is distributed on an\n" +
                    " ~  \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY\n" +
                    " ~  KIND, either express or implied.  See the License for the\n" +
                    " ~  specific language governing permissions and limitations\n" +
                    " ~  under the License.\n\n";

}
