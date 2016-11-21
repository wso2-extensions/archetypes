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
import org.wso2.carbon.extension.esb.connector.automation.wsdl.GenerateWSDLConnector;

import java.util.Scanner;

/**
 * Generate connector main class.
 */

public class GenerateConnector {

    public static void main(String[] args) {
        int outputType;
        Connector connector;
        GenerateConnectorSourceCode generateConnectorSourceCode;
        System.out.println(AutomationConstants.WELCOME_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select your preference \n 1) WSDL to C4 \n 2) Swagger to C4");
        outputType = scanner.nextInt();
        if (outputType == 1) {
            connector = new GenerateWSDLConnector(outputType);
            generateConnectorSourceCode=new C4Connector(connector.readResource(),AuthenticationMethod());
            generateConnectorSourceCode.generateConnector();
        } else if (outputType == 2) {
            System.err.println("current implementation not supported to swagger");
            System.exit(0);
        } else {
            System.err.println("please select the number from the list");
            System.exit(0);
        }
    }

    /**
     *
     * @return authentication method name
     */
    public static String AuthenticationMethod(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter select service provider name and type the number");
        System.out.println("1.Salesforce \n2.No authentication");
        String authenticationMethod = null;
        int authentication = scanner.nextInt();
        if (authentication == 1) {
            authenticationMethod = AutomationConstants.SALESFORCE;
        } else if (authentication == 2) {
            authenticationMethod = "";
            System.out.println("Running without extra Authentication method");
        } else {
            System.err.println("Please select the number from the list");
            System.exit(0);
        }
        return authenticationMethod;
    }
}