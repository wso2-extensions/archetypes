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

import org.wso2.carbon.event.input.adapter.core.InputEventAdapter;
import org.wso2.carbon.event.input.adapter.core.InputEventAdapterListener;
import org.wso2.carbon.event.input.adapter.core.exception.InputEventAdapterException;
import org.wso2.carbon.event.input.adapter.core.exception.InputEventAdapterRuntimeException;

public class ${carbon_analytics_receiver_name}EventAdapter implements InputEventAdapter {
    /**
     * This method is called when initiating event receiver bundle.
     * Relevant code segments which are needed when loading OSGI bundle can be included in this method.
     *
     * @param inputEventAdapterListener
     * @throws InputEventAdapterException
     */
    @Override
    public void init(InputEventAdapterListener inputEventAdapterListener) throws InputEventAdapterException {
    }

    /**
     * This method checks whether the receiving server is available.
     *
     * @throws org.wso2.carbon.event.input.adapter.core.exception.TestConnectionNotSupportedException
     * @throws InputEventAdapterRuntimeException
     * @throws org.wso2.carbon.event.input.adapter.core.exception.ConnectionUnavailableException
     */
    @Override
    public void testConnect() throws org.wso2.carbon.event.input.adapter.core.exception.TestConnectionNotSupportedException, InputEventAdapterRuntimeException, org.wso2.carbon.event.input.adapter.core.exception.ConnectionUnavailableException {
    }

    /**
     * This method will be called after calling the init() method.
     * Intention is to connect to a receiving end
     * and if it is not available "ConnectionUnavailableException" will be thrown.
     *
     * @throws InputEventAdapterRuntimeException
     * @throws org.wso2.carbon.event.input.adapter.core.exception.ConnectionUnavailableException
     */
    @Override
    public void connect() throws InputEventAdapterRuntimeException, org.wso2.carbon.event.input.adapter.core.exception.ConnectionUnavailableException {
    }

    /**
     * This method can be called when it is needed to disconnect from the connected receiving server.
     */
    @Override
    public void disconnect() {
    }

    /**
     * The method can be called when removing an event receiver.
     * The cleanups that has to be done when removing the receiver can be done over here.
     */
    @Override
    public void destroy() {
    }

    /**
     * Returns a boolean output stating whether an event is duplicated in a cluster or not.
     * This can be used in clustered deployment.
     *
     * @return
     */
    @Override
    public boolean isEventDuplicatedInCluster() {
        return false;
    }

    /**
     * Checks whether events get accumulated at the adapter and clients connect to it to collect events.
     *
     * @return
     */
    @Override
    public boolean isPolling() {
        return false;
    }
}