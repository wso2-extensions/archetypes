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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Service Info an in memory representation of a service defined in WSDL
 */

public class ServiceInfo {
    /**
     * The service name
     */
    String name = "";

    /**
     * The list of operations that this service defines.
     */
    public List operations = new ArrayList();

    /**
     * Constructor
     */
    public ServiceInfo() {
    }

    /**
     * Gets the name of the service
     *
     * @return The name of the service is returned
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the service
     *
     * @param value The name of the service
     */
    public void setName(String value) {
        name = value;
    }

    /**
     * Add an ooperation info object to this service definition
     *
     * @param operation The operation to add to this service definition
     */
    public void addOperation(OperationInfo operation) {
        operations.add(operation);
    }

    /**
     * Returns the operations defined by this service
     *
     * @return an Iterator that can be used to iterate the operations defined by this service
     */
    public Iterator getOperations() {
        return operations.iterator();
    }

    /**
     * Override toString to return the name of the service
     *
     * @return The name of the service is returned
     */
    public String toString() {
        return getName();
    }
}