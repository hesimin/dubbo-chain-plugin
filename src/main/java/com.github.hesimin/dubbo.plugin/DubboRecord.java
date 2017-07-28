package com.github.hesimin.dubbo.plugin;

import java.io.Serializable;

/**
 * @author hesimin
 * @date 2016/10/17
 */
public class DubboRecord implements Serializable {

    private Long id;

    /**
     * 请求id
     */
    private String gid;

    /**
     * 请求节点id
     */
    private String nid;

    /**
     * 记录者：consumer、provider
     */
    private String recorder;

    /**
     * 调用次数统计
     */
    private Integer requestCount;

    /**
     * 是否成功
     */
    private Integer success;

    /**
     * 方法名字(uri或者rpc方法-含类全名#方法名)
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

    public Integer getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Integer requestCount) {
        this.requestCount = requestCount;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
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