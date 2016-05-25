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

import org.wso2.siddhi.core.config.ExecutionPlanContext;
import org.wso2.siddhi.core.executor.ExpressionExecutor;
import org.wso2.siddhi.core.query.selector.attribute.aggregator.AttributeAggregator;
import org.wso2.siddhi.query.api.definition.Attribute;

/**
 * This Sample implemented for count you can implement based on this sample
 */
public class ${siddhi_aggregator_name}AggregateFunction extends AttributeAggregator {

    private static Attribute.Type type = Attribute.Type.LONG;
    private long value = 0l;

    /**
     * The initialization method for ${siddhi_aggregator_name}AggregatorFunction
     *
     * @param attributeExpressionExecutors are the executors of each attributes in the function
     * @param executionPlanContext         Execution plan runtime context
     */
    @Override
    protected void init(ExpressionExecutor[] attributeExpressionExecutors, ExecutionPlanContext executionPlanContext) {

    }

    public Attribute.Type getReturnType() {
        return type;
    }

    /**
     * The process add method of the ${siddhi_aggregator_name}AggregateFunction, used when zero or one function parameter is provided
     *
     * @param data null if the function parameter ${siddhi_aggregator_name} is zero or runtime data value of the function parameter
     * @return the ${siddhi_aggregator_name} value
     */
    @Override
    public Object processAdd(Object data) {
        //Sample code
        value++;
        return value;
    }

    /**
     * The process add method of the ${siddhi_aggregator_name}AggregateFunction, used when more than one function parameters are provided
     *
     * @param data the data values for the function parameters
     * @return the ${siddhi_aggregator_name} value
     */
    @Override
    public Object processAdd(Object[] data) {
        //Sample code
        value++;
        return value;
    }

    /**
     * The process remove method of the ${siddhi_aggregator_name}AggregateFunction, used when zero or one function parameter is provided
     *
     * @param data null if the function parameter ${siddhi_aggregator_name} is zero or runtime data value of the function parameter
     * @return the ${siddhi_aggregator_name} value
     */
    @Override
    public Object processRemove(Object data) {
        //Sample code
        value--;
        return value;
    }

    /**
     * The process remove method of the ${siddhi_aggregator_name}AggregateFunction, used when more than one function parameters are provided
     *
     * @param data the data values for the function parameters
     * @return the ${siddhi_aggregator_name} value
     */
    @Override
    public Object processRemove(Object[] data) {
        //Sample code
        value--;
        return value;
    }

    /**
     * Reset ${siddhi_aggregator_name} value
     *
     * @return reset value
     */
    @Override
    public Object reset() {
        //Sample code
        value = 0l;
        return value;
    }

    /**
     * This will be called only once and this can be used to acquire
     * required resources for the processing element.
     * This will be called after initializing the system and before
     * starting to process the events.
     */
    @Override
    public void start() {
        //Nothing to start
    }

    /**
     * This will be called only once and this can be used to release
     * the acquired resources for processing.
     * This will be called before shutting down the system.
     */
    @Override
    public void stop() {
        //nothing to stop
    }

    /**
     * Used to collect the serializable state of the processing element, that need to be
     * persisted for the reconstructing the element to the same state on a different point of time
     *
     * @return stateful objects of the processing element as an array
     */
    @Override
    public Object[] currentState() {
        return new Object[]{value};
    }

    /**
     * Used to restore serialized state of the processing element, for reconstructing
     * the element to the same state as if was on a previous point of time.
     *
     * @param state the stateful objects of the element as an array on
     *              the same order provided by currentState().
     */
    @Override
    public void restoreState(Object[] state) {
        //Sample code
        value = (Long) state[0];
    }
}