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

import org.wso2.carbon.extension.esb.connector.automation.Connector;

import java.util.List;
import java.util.Scanner;

public class GenerateWSDLConnector implements Connector {
    int outputType;

    public GenerateWSDLConnector(int outputType) {
        this.outputType = outputType;
    }

    @Override
    public List readResource() {
        ComponentBuilder builder = new ComponentBuilder();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter WSDL path");
        String wsdlPath = scanner.next();
        List components = null;
        try {
            if (outputType == 1) {
                 components = builder.buildComponents(wsdlPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return components;
    }
}
