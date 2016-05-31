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

public class ${siddhi_aggregator_name}AggregateFunctionTestCase {
    private static Logger logger = Logger.getLogger(${siddhi_aggregator_name}AggregateFunctionTestCase.class);
    protected static SiddhiManager siddhiManager;
    private Object count;

    @Before
    public void init() {
        count = 0;
    }

    @Test
    public void testProcess() throws Exception {
        logger.info("${siddhi_aggregator_name}AggregateFunction TestCase");
        siddhiManager = new SiddhiManager();
        String executionPlan = "define stream pizzaOrder (orderNo int); ";
        String eventFuseExecutionPlan = ("@info(name = 'query1') from pizzaOrder#window.time(5 sec) "
                + " select ${siddhi_aggregator_name}:${siddhi_aggregator_name}Aggregator(orderNo) as totalOrders "
                + " insert into OutMediationStream;");
        ExecutionPlanRuntime executionPlanRuntime = siddhiManager
                .createExecutionPlanRuntime(executionPlan + eventFuseExecutionPlan);
        executionPlanRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                for (org.wso2.siddhi.core.event.Event event : inEvents) {
                    count = event.getData(0);
                }
            }
        });
        InputHandler inputHandler = executionPlanRuntime.getInputHandler("pizzaOrder");
        executionPlanRuntime.start();
        Thread.sleep(100);
        inputHandler.send(new Object[]{10863690});
        Thread.sleep(100);
        inputHandler.send(new Object[]{7868});
        Thread.sleep(100);
        inputHandler.send(new Object[]{823863});
        Thread.sleep(100);
        inputHandler.send(new Object[]{8368});
        Thread.sleep(100);
        executionPlanRuntime.shutdown();
        Assert.assertEquals("Event count ",Long.valueOf(4),count);
    }
}