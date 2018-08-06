package com.songaw.generator.modules.auths.entity;

import javax.persistence.*;

@Table(name = "`t_backend_api`")
public class BackendApi {
    @Id
    @Column(name = "`id`")
    private Long id;

    @Column(name = "`tag`")
    private String tag;

    @Column(name = "`path`")
    private String path;

    /**
     * HttpMethod
     */
    @Column(name = "`method`")
    private String method;

    @Column(name = "`summary`")
    private String summary;

    @Column(name = "`operation_id`")
    private String operationId;

    @Column(name = "`UPDATE_USER`")
    private Long updateUser;

    @Column(name = "`UPDATE_TIME`")
    private Long updateTime;

    @Column(name = "`INSERT_USER`")
    private Long insertUser;

    @Column(name = "`INSERT_TIME`")
    private Long insertTime;

    @Column(name = "`DELETE_FLAG`")
    private String deleteFlag;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取HttpMethod
     *
     * @return method - HttpMethod
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置HttpMethod
     *
     * @param method HttpMethod
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @return summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return operation_id
     */
    public String getOperationId() {
        return operationId;
    }

    /**
     * @param operationId
     */
    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    /**
     * @return UPDATE_USER
     */
    public Long getUpdateUser() {
        return updateUser;
    }

    /**
     * @param updateUser
     */
    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * @return UPDATE_TIME
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return INSERT_USER
     */
    public Long getInsertUser() {
        return insertUser;
    }

    /**
     * @param insertUser
     */
    public void setInsertUser(Long insertUser) {
        this.insertUser = insertUser;
    }

    /**
     * @return INSERT_TIME
     */
    public Long getInsertTime() {
        return insertTime;
    }

    /**
     * @param insertTime
     */
    public void setInsertTime(Long insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * @return DELETE_FLAG
     */
    public String getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * @param deleteFlag
     */
    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}