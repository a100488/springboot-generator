package com.songaw.generator.modules.auths.entity;

import javax.persistence.*;

@Table(name = "`t_role`")
public class Role {
    /**
     * 编号
     */
    @Id
    @Column(name = "`id`")
    private Long id;

    /**
     * 角色名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 角色权限标识
     */
    @Column(name = "`code`")
    private String code;

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
     * 获取编号
     *
     * @return id - 编号
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取角色名称
     *
     * @return name - 角色名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名称
     *
     * @param name 角色名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取角色权限标识
     *
     * @return code - 角色权限标识
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置角色权限标识
     *
     * @param code 角色权限标识
     */
    public void setCode(String code) {
        this.code = code;
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