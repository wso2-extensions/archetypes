/*
*  Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/

package ${package};

import org.wso2.carbon.event.output.adapter.core.OutputEventAdapter;
import org.wso2.carbon.event.output.adapter.core.OutputEventAdapterConfiguration;
import org.wso2.carbon.event.output.adapter.core.OutputEventAdapterFactory;
import org.wso2.carbon.event.output.adapter.core.Property;

import java.util.List;
import java.util.Map;

/**
 * The ${carbon_analytics_publisher_name} event adapter factory class to create
 * an ${carbon_analytics_publisher_name} output adapter.
 */
public class ${carbon_analytics_publisher_name}EventAdapterFactory extends OutputEventAdapterFactory {

    /**
     * Here type needs to be specified,
     * this string will be displayed in the publisher interface in the adapter type drop down list.
     *
     * @return
     */
    @Override
    public String getType() {
        return null;
    }

    /**
     * Specify supported message formats for the created publisher type.
     *
     * @return
     */
    @Override
    public List<String> getSupportedMessageFormats() {
        return null;
    }

    /**
     * Here static properties have to be specified.
     * These properties will use the values assigned when creating a publisher.
     * For more information on adapter properties see Event Publisher Configuration.
     *
     * @return
     */
    @Override
    public List<Property> getStaticPropertyList() {
        return null;
    }

    /**
     * You can define dynamic properties similar to static properties,
     * the only difference is dynamic property values can be derived by events handling by publisher.
     * For more information on adapter properties see Event Publisher Configuration.
     *
     * @return
     */
    @Override
    public List<Property> getDynamicPropertyList() {
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
     * This method creates the publisher by specifying event adapter configuration
     * and global properties which are common to every adapter type.
     *
     * @param outputEventAdapterConfiguration
     * @param map
     * @return
     */
    @Override
    public OutputEventAdapter createEventAdapter(OutputEventAdapterConfiguration outputEventAdapterConfiguration,
                                                 Map<String, String> map) {
        return null;
    }
}