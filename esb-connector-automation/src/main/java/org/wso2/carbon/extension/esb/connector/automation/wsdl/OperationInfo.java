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
import org.wso2.carbon.extension.esb.connector.automation.util.AutomationConstants;
import org.wso2.carbon.extension.esb.connector.automation.util.Property;

import javax.wsdl.Message;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines an in memory model to support a SOAP invocation
 */

public class OperationInfo {

    /**
     * The URL where the target object is located.
     */
    private String targetURL = "";

    /**
     * The namespace URI used for this SOAP operation.
     */
    private String namespaceURI = "";

    /**
     * The name used to when making an invocation.
     */
    private String targetMethodName = "";

    /**
     * The encoding type "document" vs. "rpc"
     */
    private String style = AutomationConstants.DOCUMENT;

    private List<Property> userProperties = new ArrayList<Property>();
    private Message inMsg;
    private Schema wsdlType;


    /**
     * Constructor
     */
    public OperationInfo() {
        super();
    }

    /**
     * Constructor
     *
     * @param style Pass "document" or "rpc"
     */
    public OperationInfo(String style) {
        super();
        setStyle(style);
    }

    /**
     * Sets the encoding style for this operation.
     *
     * @param value The encoding style
     */
    public void setEncodingStyle(String value) {
    }

    /**
     * Gets the Target URL used to make a SOAP invocation for this operation
     *
     * @return The target URL is returned
     */
    public String getTargetURL() {
        return targetURL;
    }

    /**
     * Sets the Target URL used to make a SOAP invocation for this operation
     *
     * @param value The target URL
     */
    public void setTargetURL(String value) {
        targetURL = value;
    }

    /**
     * Gets the namespace URI used for this
     *
     * @return The namespace URI of the target object
     */
    public String getNamespaceURI() {
        return namespaceURI;
    }

    /**
     * Sets the namespace URI used for this operation
     *
     * @param value The namespace URI to use
     */
    public void setNamespaceURI(String value) {
        namespaceURI = value;
    }

    /**
     * Sets the Target Object's URI used to make an invocation
     *
     * @param value The URI of the target object
     */
    public void setTargetObjectURI(String value) {
    }

    /**
     * Gets the name of the target method to call
     *
     * @return The name of the method to call
     */
    public String getTargetMethodName() {
        return targetMethodName;
    }

    /**
     * Sets the name of the target method to call
     *
     * @param value The name of the method to call
     */
    public void setTargetMethodName(String value) {
        targetMethodName = value;
    }

    /**
     * Sets the value of the target's input message name
     *
     * @param value The name of input message
     */
    public void setInputMessageName(String value) {
    }

    /**
     * Sets the value for the SOAP Action URI used to make a SOAP invocation
     *
     * @param value The SOAP Action URI value for the SOAP invocation
     */
    public void setSoapActionURI(String value) {
    }

    /**
     * Returns the style "document" or "rpc"
     *
     * @return The style type is returned
     */
    public String getStyle() {
        return style;
    }

    /**
     * Sets the encoding document/literal vs. rpc/encoded
     *
     * @return value A string value "document" or "rpc" should be passed.
     */
    public void setStyle(String value) {
        style = value;
    }

    /**
     * Override toString to return a name for the operation
     *
     * @return The name of the operation is returned
     */
    public String toString() {
        return getTargetMethodName();
    }

    /**
     * @return
     */
    public List<Property> getParameters() {
        return userProperties;
    }

    /**
     * @param parameters
     */
    public void setParameters(Property parameters) {
        userProperties.add(parameters);
    }

    /**
     * @param inMsg
     */
    public void setInMessage(Message inMsg) {
        this.inMsg = inMsg;
    }

    /**
     * @return
     */
    public Message getInMsg() {
        return inMsg;
    }

    /**
     * @param wsdlFactory
     */
    public void setWSDLType(Schema wsdlFactory) {
        this.wsdlType = wsdlFactory;
    }

    /**
     * @return
     */
    public Schema getWsdlType() {
        return wsdlType;
    }

}