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

package org.wso2.carbon.extension.esb.connector.automation.wsdl;

/**
 *
 */
public class ConnectorException extends Exception {

    /**
     * @param message
     */
    public ConnectorException(String message) {
        super(message);
    }

    /**
     * @param msg
     * @param ex
     */
    public static void handleException(String msg, Exception ex) {
        System.err.print("Error code: " + msg);
        ex.printStackTrace();
    }

    /**
     * @param msg
     */
    public static void handleException(String msg) {
        new ConnectorException(msg);
    }
}

