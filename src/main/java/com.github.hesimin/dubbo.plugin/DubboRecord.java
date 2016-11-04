package com.github.hesimin.dubbo.plugin;

import java.io.Serializable;

/**
 * @author hesimin
 * @date 2016/10/17
 */
public class DubboRecord implements Serializable {

    private Long id;

    /**
     * uuid 32+2位
     */
    private String globalRequestId;

    /**
     * uuid 32+2位
     */
    private String nodeRequestId;

    /**
     * 记录者：consumer、provider
     */
    private String recorder;

    /**
     * 调用dubbo次数统计
     */
    private Integer requestDubboCount;

    /**
     * 是否成功
     */
    private Integer success;

    /**
     * 接口类型
     */
    private String serviceType;

    /**
     * 方法名字
     */
    private String methodName;

    /**
     * 参数类型
     */
    private String parameterTypes;

    /**
     * 创建/开始时间
     */
    private Long startTime;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 参数值
     */
    private String parameterValues;

    /**
     * 异常
     */
    private String ex;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGlobalRequestId() {
        return globalRequestId;
    }

    public void setGlobalRequestId(String globalRequestId) {
        this.globalRequestId = globalRequestId == null ? null : globalRequestId.trim();
    }

    public String getNodeRequestId() {
        return nodeRequestId;
    }

    public void setNodeRequestId(String nodeRequestId) {
        this.nodeRequestId = nodeRequestId == null ? null : nodeRequestId.trim();
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder == null ? null : recorder.trim();
    }

    public Integer getRequestDubboCount() {
        return requestDubboCount;
    }

    public void setRequestDubboCount(Integer requestDubboCount) {
        this.requestDubboCount = requestDubboCount;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    public String getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(String parameterTypes) {
        this.parameterTypes = parameterTypes == null ? null : parameterTypes.trim();
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getParameterValues() {
        return parameterValues;
    }

    public void setParameterValues(String parameterValues) {
        this.parameterValues = parameterValues == null ? null : parameterValues.trim();
    }

    public String getEx() {
        return ex;
    }

    public void setEx(String exception) {
        this.ex = exception == null ? null : exception.trim();
    }
}