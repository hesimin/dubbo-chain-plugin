package com.github.hesimin.dubbo.plugin;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcInvocation;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hesimin 2017-02-10
 */

@Activate(group = {Constants.CONSUMER})
public class DubboCallLogConsumerFilter {
    private static Logger logger = LoggerFactory.getLogger(DubboCallLogConsumerFilter.class);

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String serviceName = invoker.getInterface().getName();
        Class serviceType = invoker.getInterface();
        String methodName = invocation.getMethodName();
        Class[] parameterTypes = invocation.getParameterTypes();
        Object[] argumentValues = invocation.getArguments();

        RpcInvocation rpcInvocation = (RpcInvocation) invocation;

        // 全局请求id
        String gId = DubboThreadLocal.getGid();
        // 节点请求id（本节点id由调用方生成）
        String nId = DubboThreadLocal.getNid();
        // 为null则为发起源生成全局id，如果是上节点未传则由DubboProviderInvokeFilter生成
        String calledNodeId = null;
        if (gId == null) {
            gId = DubboThreadLocal.getNewGid();
            nId = "0";
            calledNodeId = "0-0";
        } else {
            nId = nId + "-" + DubboThreadLocal.getRequestDubboCount();
        }

        // 通过dubbo的添加附加参数功能将请求id传递给服务提供方
        rpcInvocation.setAttachment(DubboThreadLocal.GLOBAL_REQUEST_ID_KEY, gId);
        rpcInvocation.setAttachment(DubboThreadLocal.NODE_REQUEST_ID_KEY, calledNodeId == null ? nId : calledNodeId);

        Result result = null;

        String prefix = RpcContext.getContext().isConsumerSide() ? "==>" : "<==";
        try {
            result = invoker.invoke(invocation);
        } finally {
            if (!result.hasException()) {
                logger.info("{}{} dubboCall=[{}] paramters={}  result={}", prefix, gId, serviceName + "#" + methodName, JSON.toJSONString(argumentValues),
                        JSON.toJSONString(result.getValue()));
            } else {
                logger.error("{}{} dubboCall=[{}] paramters={}  resultException={}", prefix, gId, serviceName + "#" + methodName, JSON.toJSONString(argumentValues),
                        result.getException().getMessage());
            }
        }
        return result;
    }
}
