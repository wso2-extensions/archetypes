/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package ${package};

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wso2.siddhi.core.ExecutionPlanRuntime;
import org.wso2.siddhi.core.SiddhiManager;
import org.wso2.siddhi.core.event.Event;
import org.wso2.siddhi.core.query.output.callback.QueryCallback;
import org.wso2.siddhi.core.stream.input.InputHandler;
import org.wso2.siddhi.core.util.EventPrinter;


public class ${siddi_stream_processor_name}StreamProcessorIntegrationTest {
    private static Logger logger = Logger.getLogger(${siddi_stream_processor_name}StreamProcessorIntegrationTest.class);
    protected static SiddhiManager siddhiManager;

    @Test
    public void testProcess() throws Exception {
        logger.info("${siddi_stream_processor_name}StreamProcessor TestCase");

        siddhiManager = new SiddhiManager();
        String inValueStream = "";

        String eventFuseExecutionPlan = ("");
        ExecutionPlanRuntime executionPlanRuntime = siddhiManager
                .createExecutionPlanRuntime(inValueStream + eventFuseExecutionPlan);

        executionPlanRuntime.addCallback("", new QueryCallback() {
            @Override
            public void receive(long l, Event[] events, Event[] events1) {

            }
        });

        InputHandler inputHandler = executionPlanRuntime.getInputHandler("");
        executionPlanRuntime.start();
        inputHandler.send(new Object[]{""});
        Thread.sleep(1000);
        inputHandler.send(new Object[]{""});
        Thread.sleep(1000);

        executionPlanRuntime.shutdown();
    }
}