package com.github.hesimin.dubbo.plugin;

import java.io.Serializable;

/**
 * @author hesimin
 * @date 2016/10/17
 */
public class DubboRecord implements Serializable {

    private Long id;

    /**
     * uuid 32+2位（全局id）
     */
    private String gid;

    /**
     * uuid 32+2位(节点id)
     */
    private String nid;

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

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
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
        this.serviceType = serviceType;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(String parameterTypes) {
        this.parameterTypes = parameterTypes;
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
        this.parameterValues = parameterValues;
    }

    public String getEx() {
        return ex;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }
}