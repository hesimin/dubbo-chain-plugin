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

@Activate(group = {Constants.PROVIDER})
public class DubboCallLogProviderFilter {
    private static Logger logger = LoggerFactory.getLogger(DubboCallLogProviderFilter.class);

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String serviceName = invoker.getInterface().getName();
        Class serviceType = invoker.getInterface();
        String methodName = invocation.getMethodName();
        Class[] parameterTypes = invocation.getParameterTypes();
        Object[] argumentValues = invocation.getArguments();

        RpcInvocation rpcInvocation = (RpcInvocation) invocation;

        //服务方获取设置全局请求id、节点请求id
        String gId = rpcInvocation.getAttachment(DubboThreadLocal.GLOBAL_REQUEST_ID_KEY);
        String nId = rpcInvocation.getAttachment(DubboThreadLocal.NODE_REQUEST_ID_KEY);
        if (gId == null) {
            gId = DubboThreadLocal.getNewGid();
            nId = "0";
            String clientIp = RpcContext.getContext().getRemoteHost();
            logger.info(">==未获取到globalRequestId，可能是调用方未引入jar包,ip={},createGlobalRequestId={}", clientIp, gId);
        } else {
            DubboThreadLocal.put(DubboThreadLocal.GLOBAL_REQUEST_ID_KEY, gId);
            DubboThreadLocal.put(DubboThreadLocal.NODE_REQUEST_ID_KEY, nId);
        }

        Result result = null;

        String prefix = RpcContext.getContext().isConsumerSide() ? "==>" : "<==";
        try {
            result = invoker.invoke(invocation);
            if (!result.hasException()) {
                logger.info("{}{} dubboCall=[{}] paramters={}  result={}", prefix, gId, serviceName + "#" + methodName, JSON.toJSONString(argumentValues),
                        JSON.toJSONString(result.getValue()));
            } else {
                logger.error("{}{} dubboCall=[{}] paramters={}  resultException={}", prefix, gId, serviceName + "#" + methodName, JSON.toJSONString(argumentValues),
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
