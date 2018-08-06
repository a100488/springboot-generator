package com.songaw.generator.modules.auths.entity;

import javax.persistence.*;

@Table(name = "`t_user`")
public class User {
    @Id
    @Column(name = "`id`")
    private Long id;

    @Column(name = "`USER_NAME`")
    private String userName;

    @Column(name = "`PASSWORD`")
    private String password;

    @Column(name = "`REAL_NAME`")
    private String realName;

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
     * @return USER_NAME
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return PASSWORD
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return REAL_NAME
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName
     */
    public void setRealName(String realName) {
        this.realName = realName;
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