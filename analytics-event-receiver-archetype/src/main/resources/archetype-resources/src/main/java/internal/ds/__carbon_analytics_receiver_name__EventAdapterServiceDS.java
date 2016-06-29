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
import org.wso2.carbon.event.input.adapter.core.InputEventAdapterFactory;
import ${package}.${carbon_analytics_receiver_name}EventAdapterFactory;

/**
 * @scr.component component.name="input.${carbon_analytics_receiver_name}.AdapterService.component" immediate="true"
 */

public class ${carbon_analytics_receiver_name}EventAdapterServiceDS {

    private static final Log log = LogFactory.getLog(${carbon_analytics_receiver_name}EventAdapterServiceDS.class);

    /**
     * Deployment of the ${carbon_analytics_receiver_name} event adapter service will be done.
     *
     * @param context
     */
    protected void activate(ComponentContext context) {
        try {
            InputEventAdapterFactory ${carbon_analytics_receiver_name.toLowerCase()}EventAdaptorFactory =
                    new ${carbon_analytics_receiver_name}EventAdapterFactory();
            context.getBundleContext().registerService(InputEventAdapterFactory.class.getName(),
                    ${carbon_analytics_receiver_name.toLowerCase()}EventAdaptorFactory, null);
            if (log.isDebugEnabled()) {
                log.debug("Successfully deployed the input ${carbon_analytics_receiver_name} event service.");
            }
        } catch (RuntimeException e) {
            log.error("Can not create  the input ${carbon_analytics_receiver_name} event service. ", e);
        }
    }
}