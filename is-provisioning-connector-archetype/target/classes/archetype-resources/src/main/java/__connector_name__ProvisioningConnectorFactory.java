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
import org.wso2.carbon.identity.provisioning.AbstractOutboundProvisioningConnector;
import org.wso2.carbon.identity.provisioning.AbstractProvisioningConnectorFactory;
import org.wso2.carbon.identity.provisioning.IdentityProvisioningException;

import java.util.ArrayList;
import java.util.List;

public class ${connector_name}ProvisioningConnectorFactory extends AbstractProvisioningConnectorFactory {

    private static final Log log = LogFactory.getLog(${connector_name}ProvisioningConnectorFactory.class);
    private static final String CONNECTOR_TYPE = "${connector_name}";

    @Override
    protected AbstractOutboundProvisioningConnector buildConnector(
            Property[] provisioningProperties) throws IdentityProvisioningException {
        ${connector_name}ProvisioningConnector connector = new ${connector_name}ProvisioningConnector();
        connector.init(provisioningProperties);
        if (log.isDebugEnabled()) {
            log.debug("${connector_name} provisioning connector created successfully.");
        }
        return connector;
    }

    @Override
    public String getConnectorType() {
        return CONNECTOR_TYPE;
    }

    @Override
    public List<Property> getConfigurationProperties() {
        List<Property> configProperties = new ArrayList<Property>();
        //Add your code for the UI
        return configProperties;
    }

}
