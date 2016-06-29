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

package ${package}.internal.ds;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.event.output.adapter.core.OutputEventAdapterFactory;
import ${package}.${carbon_analytics_publisher_name}EventAdapterFactory;


/**
 * @scr.component component.name="output.${carbon_analytics_publisher_name}.AdapterService.component" immediate="true"
 */

public class ${carbon_analytics_publisher_name}EventAdapterServiceDS {

    private static final Log log = LogFactory.getLog(${carbon_analytics_publisher_name}EventAdapterServiceDS.class);

    /**
     * Deployment of the ${carbon_analytics_publisher_name} event adapter service will be done.
     *
     * @param context.
     */
    protected void activate(ComponentContext context) {
        try {
            OutputEventAdapterFactory ${carbon_analytics_publisher_name.toLowerCase()}EventAdaptorFactory =
                    new ${carbon_analytics_publisher_name}EventAdapterFactory();
            context.getBundleContext().registerService(OutputEventAdapterFactory.class.getName(),
                    ${carbon_analytics_publisher_name.toLowerCase()}EventAdaptorFactory, null);
            if (log.isDebugEnabled()) {
                log.debug("Successfully deployed the Output ${carbon_analytics_publisher_name} event service.");
            }
        } catch (RuntimeException e) {
            log.error("Can not create  the Output ${carbon_analytics_publisher_name} event service. ", e);
        }
    }
}