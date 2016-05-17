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

package ${package};

import org.wso2.carbon.event.output.adapter.core.OutputEventAdapter;
import org.wso2.carbon.event.output.adapter.core.exception.ConnectionUnavailableException;
import org.wso2.carbon.event.output.adapter.core.exception.OutputEventAdapterException;
import org.wso2.carbon.event.output.adapter.core.exception.TestConnectionNotSupportedException;

import java.util.Map;

public class ${carbon_analytics_publisher_name}EventAdapter implements OutputEventAdapter {

    /**
     * This method is called when initiating event publisher bundle.
     * Relevant code segments which are needed when loading OSGI bundle can be included in this method.
     *
     * @throws OutputEventAdapterException
     */
    public void init() throws OutputEventAdapterException {

    }

    /**
     * This method is used to test the connection of the publishing server.
     *
     * @throws TestConnectionNotSupportedException
     * @throws ConnectionUnavailableException
     */
    public void testConnect() throws TestConnectionNotSupportedException, ConnectionUnavailableException {

    }

    /**
     * Can be called to connect to back end before events are published.
     *
     * @throws ConnectionUnavailableException
     */
    public void connect() throws ConnectionUnavailableException {

    }

    /**
     * Publish events. Throws ConnectionUnavailableException if it cannot connect to the back end.
     *
     * @param o
     * @param map
     * @throws ConnectionUnavailableException
     */
    public void publish(Object o, Map<String, String> map) throws ConnectionUnavailableException {

    }

    /**
     * Will be called after publishing is done, or when ConnectionUnavailableException is thrown.
     */
    public void disconnect() {

    }

    /**
     * The method can be used to clean all the resources consumed.
     */
    public void destroy() {

    }

    /**
     * Checks whether events get accumulated at the adapter and clients connect to it to collect events.
     *
     * @return
     */
    public boolean isPolled() {
        return false;
    }
}