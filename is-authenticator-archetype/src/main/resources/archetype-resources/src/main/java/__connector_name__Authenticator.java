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

import org.apache.oltu.oauth2.client.response.OAuthClientResponse;
import org.wso2.carbon.identity.application.authenticator.oidc.OpenIDConnectAuthenticator;
import org.wso2.carbon.identity.application.authentication.framework.FederatedApplicationAuthenticator;
import org.wso2.carbon.identity.application.common.model.Property;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Authenticator of ${connector_name}
 */
public class ${connector_name}Authenticator extends OpenIDConnectAuthenticator
        implements FederatedApplicationAuthenticator {

    private static Log log = LogFactory.getLog(${connector_name}Authenticator.class);

    /**
     * Get ${connector_name} authorization endpoint.
     */
    @Override
    protected String getAuthorizationServerEndpoint(Map< String, String > authenticatorProperties) {
        return ${connector_name}AuthenticatorConstants.${connector_name}_OAUTH_ENDPOINT;
    }

    /**
     * Get ${connector_name} token endpoint.
     */
    @Override
    protected String getTokenEndpoint(Map< String, String > authenticatorProperties) {
        return ${connector_name}AuthenticatorConstants.${connector_name}_TOKEN_ENDPOINT;
    }

    /**
     * Get ${connector_name} user info endpoint.
     */
    @Override
    protected String getUserInfoEndpoint(OAuthClientResponse token, Map< String, String > authenticatorProperties) {
        return ${connector_name}AuthenticatorConstants.${connector_name}_USERINFO_ENDPOINT;
    }

    /**
     * Check ID token in ${connector_name} OAuth.
     */
    @Override
    protected boolean requiredIDToken(Map< String, String > authenticatorProperties) {
        return false;
    }

    /**
     * Get the friendly name of the Authenticator
     */
    @Override
    public String getFriendlyName() {
        return ${connector_name}AuthenticatorConstants.AUTHENTICATOR_FRIENDLY_NAME;
    }

    /**
     * Get the name of the Authenticator
     */
    @Override
    public String getName() {
        return ${connector_name}AuthenticatorConstants.AUTHENTICATOR_NAME;
    }

    /**
     * Get Configuration Properties
     */
    @Override
    public List<Property> getConfigurationProperties() {

        List<Property> configProperties = new ArrayList<Property>();
        //Add your configuration properties
        return configProperties;
    }
}