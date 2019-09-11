#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package ${package}.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import org.wso2.carbon.user.core.listener.UserOperationEventListener;
import ${groupId}.${listener-name-prefix}UserOperationEventListener;

@Component(name = "${package}.${listener-name-prefix}UserOperationEventListenerServiceComponent",
        immediate = true)
public class ${listener-name-prefix}UserOperationEventListenerServiceComponent {

    private static Log log = LogFactory.getLog(${listener-name-prefix}UserOperationEventListenerServiceComponent.class);

    @Activate
    protected void activate(ComponentContext context) {

        //register the custom listener as an OSGI service.
        context.getBundleContext().registerService(UserOperationEventListener.class.getName(),
                new ${listener-name-prefix}UserOperationEventListener(),null);
        log.info("${listener-name-prefix}UserOperationEventListenerServiceComponent bundle activated successfully..");
    }

    protected void deactivate(ComponentContext context) {
        if (log.isDebugEnabled()) {
            log.debug("${listener-name-prefix}UserOperationEventListenerServiceComponent is deactivated ");
        }
    }

}
