/*
 *  Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */

package ${package}.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.identity.provisioning.AbstractProvisioningConnectorFactory;
import ${package}.${connector_name}ProvisioningConnectorFactory;

/**
 * @scr.component name=
 * "${package}.internal.${connector_name}ConnectorServiceComponent"
 * immediate="true"
 */
public class ${connector_name}ConnectorServiceComponent {

    private static Log log = LogFactory.getLog(${connector_name}ConnectorServiceComponent.class);

    protected void activate(ComponentContext context) {
        if (log.isDebugEnabled()) {
            log.debug("Activating ${connector_name}ConnectorServiceComponent");
        }
        try {
            ${connector_name}ProvisioningConnectorFactory provisioningConnectorFactory = new ${connector_name}ProvisioningConnectorFactory();
            context.getBundleContext().registerService(
                    AbstractProvisioningConnectorFactory.class.getName(),
                        provisioningConnectorFactory, null);
            if (log.isDebugEnabled()) {
                log.debug("${connector_name} Identity Provisioning Connector bundle is activated");
            }
        } catch (Throwable e) {
            log.fatal(" Error while activating ${connector_name} Identity Provisioning Connector ", e);
        }
    }
}
