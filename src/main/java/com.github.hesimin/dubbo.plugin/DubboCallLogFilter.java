package com.github.hesimin.dubbo.plugin;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author hesimin 2017-02-10
 */

@Activate(group = { Constants.CONSUMER, Constants.PROVIDER })
public class DubboCallLogFilter {
    private static Logger logger = LoggerFactory.getLogger(DubboCallLogFilter.class);

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String serviceName = invoker.getInterface().getName();
        Class serviceType = invoker.getInterface();
        String methodName = invocation.getMethodName();
        Class[] parameterTypes = invocation.getParameterTypes();
        Object[] argumentValues = invocation.getArguments();

        Result result = null;

        String prefix = RpcContext.getContext().isConsumerSide() ? "==>" : "<==";
        try {
            result = invoker.invoke(invocation);
            if (!result.hasException()) {
                logger.info("{} dubboCall=[{}] paramters={}  result={}", prefix, serviceName + "#" + methodName, JSON.toJSONString(argumentValues),
                        JSON.toJSONString(result.getValue()));
            } else {
                logger.error("{} dubboCall=[{}] paramters={}  resultException={}", prefix, serviceName + "#" + methodName, JSON.toJSONString(argumentValues),
                        result.getException().getMessage());
            }
        } catch (RpcException e) {
            logger.error(prefix + "dubbo调用发生异常", e);
            throw e;
        } finally {
        }
        return result;
    }
}
