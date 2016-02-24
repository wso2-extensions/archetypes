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

package ${package};

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.application.common.model.Property;
import org.wso2.carbon.identity.provisioning.*;

import java.util.Properties;

public class ${connector_name}ProvisioningConnector extends AbstractOutboundProvisioningConnector {

    private static final Log log = LogFactory.getLog(${connector_name}ProvisioningConnector.class);
    private ${connector_name}ProvisioningConnectorConfig configHolder;

    @Override
    public void init(Property[] provisioningProperties) throws IdentityProvisioningException {
        Properties configs = new Properties();

        if (provisioningProperties != null && provisioningProperties.length > 0) {
            for (Property property : provisioningProperties) {
                //Add your code to add property to the configHolder
                configs.put(property.getName(), property.getValue());
                if (IdentityProvisioningConstants.JIT_PROVISIONING_ENABLED.equals(property
                        .getName())) {
                    if ("1".equals(property.getValue())) {
                        jitProvisioningEnabled = true;
                    }
                }
            }
        }

        configHolder = new ${connector_name}ProvisioningConnectorConfig(configs);
    }

    @Override
    public ProvisionedIdentifier provision(ProvisioningEntity provisioningEntity)
            throws IdentityProvisioningException {
        String provisionedId = null;
        if (provisioningEntity != null) {
            if (provisioningEntity.isJitProvisioning() && !isJitProvisioningEnabled()) {
                log.debug("JIT provisioning disabled for ${connector_name} connector");
                return null;
            }
            //Add your code here
        }

        // creates a provisioned identifier for the provisioned user.
        ProvisionedIdentifier identifier = new ProvisionedIdentifier();
        identifier.setIdentifier(provisionedId);
        return identifier;
    }
}
