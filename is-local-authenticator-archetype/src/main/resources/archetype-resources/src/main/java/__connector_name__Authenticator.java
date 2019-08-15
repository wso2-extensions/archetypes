/*
 *  Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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
 */

package ${package};

import org.wso2.carbon.identity.application.authentication.framework.AbstractApplicationAuthenticator;
import org.wso2.carbon.identity.application.authentication.framework.LocalApplicationAuthenticator;
import org.wso2.carbon.identity.application.authentication.framework.context.AuthenticationContext;
import org.wso2.carbon.identity.application.authentication.framework.exception.AuthenticationFailedException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Authenticator of ${connector_name}
 */
public class ${connector_name}Authenticator extends AbstractApplicationAuthenticator
        implements LocalApplicationAuthenticator {

    private static Log log = LogFactory.getLog(${connector_name}Authenticator.class);

    @Override
    protected void initiateAuthenticationRequest(HttpServletRequest request,
                                                 HttpServletResponse response, AuthenticationContext context)
        throws AuthenticationFailedException {

        // Use this to do any initializations to the authentication. eg. this may be used to redirect user to a page
        // where he can submit the credentials

        //TODO: Magic!
        super.initiateAuthenticationRequest(request, response, context);
    }

    @Override
    public boolean canHandle(HttpServletRequest request) {
        // In a multi option scenario, check whether the request from user is meant to this authenticator or not.
        // eg. For basic authentication, this would be a check to see if request has both username and password params.

        //TODO: Magic!
        return true;
    }

    @Override
    protected void processAuthenticationResponse(HttpServletRequest request,
                                                 HttpServletResponse response, AuthenticationContext context)
        throws AuthenticationFailedException {
        // Does the actual authentication

        //TODO: Magic!
    }

    @Override
    public String getContextIdentifier(HttpServletRequest request) {
        // The requests are correlated by a UUID which is generated at the beginning of the authentication flow
        // This method is to get the correlation ID from the request
        // In most cases this should be unchanged.
        return request.getParameter("sessionDataKey");
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
}
