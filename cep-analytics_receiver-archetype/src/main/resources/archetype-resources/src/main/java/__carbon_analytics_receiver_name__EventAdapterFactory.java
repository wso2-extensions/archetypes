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
import org.wso2.carbon.event.input.adapter.core.InputEventAdapterConfiguration;
import org.wso2.carbon.event.input.adapter.core.InputEventAdapterFactory;
import org.wso2.carbon.event.input.adapter.core.Property;

import java.util.List;
import java.util.Map;

public class ${carbon_analytics_receiver_name}EventAdapterFactory extends InputEventAdapterFactory {
    /**
     * This method returns the receiver type as a String.
     *
     * @return
     */
    @Override
    public String getType() {
        return null;
    }

    /**
     * Specify supported message formats for the created receiver type.
     *
     * @return
     */
    @Override
    public List<String> getSupportedMessageFormats() {
        return null;
    }

    /**
     * Here the properties have to be defined for the receiver.
     * When defining properties you can implement to configure property values from the management console.
     *
     * @return
     */
    @Override
    public List<Property> getPropertyList() {
        return null;
    }

    /**
     * Specify any hints to be displayed in the management console.
     *
     * @return
     */
    @Override
    public String getUsageTips() {
        return null;
    }

    /**
     * This method creates the receiver by specifying event adapter configuration
     * and global properties which are common to every adapter type.
     *
     * @param inputEventAdapterConfiguration
     * @param map
     * @return
     */
    @Override
    public InputEventAdapter createEventAdapter(InputEventAdapterConfiguration inputEventAdapterConfiguration, Map<String, String> map) {
        return null;
    }
}