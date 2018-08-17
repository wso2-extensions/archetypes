package org.wso2.carbon.extension.esb.connector.automation;
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

import org.wso2.carbon.extension.esb.connector.automation.c4.C4Connector;
import org.wso2.carbon.extension.esb.connector.automation.util.AutomationConstants;
import org.wso2.carbon.extension.esb.connector.automation.util.GenerateConnectorSourceCode;
import org.wso2.carbon.extension.esb.connector.automation.wsdl.ConnectorException;
import org.wso2.carbon.extension.esb.connector.automation.wsdl.GenerateWSDLConnector;

import java.util.Scanner;

/**
 * Generate connector main class.
 */

public class GenerateConnector {

    public static void main(String[] args) {
        int outputType;
        ConnectorInput connectorInput;
        GenerateConnectorSourceCode generateConnectorSourceCode;
        System.out.println(AutomationConstants.WELCOME_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select your preference \n 1) WSDL to C4 \n 2) Swagger to C4 (Not Support)");
        outputType = scanner.nextInt();
        if (outputType == 1) {
            connectorInput = new GenerateWSDLConnector(outputType);
            generateConnectorSourceCode=new C4Connector(connectorInput.readResource(),connectorInput.AuthenticationMethod());
            try {
                generateConnectorSourceCode.generateConnector();
            } catch (ConnectorException e) {
                ConnectorException.handleException("Cannot proceed with execution", e);
            }
        } else if (outputType == 2) {
            ConnectorException.handleException("current implementation not supported to swagger");
        } else {
            ConnectorException.handleException("please select the number from the list");
        }
    }
}