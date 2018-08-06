package com.songaw.generator.modules.auths.entity;

import javax.persistence.*;

@Table(name = "`t_menu`")
public class Menu {
    /**
     * 编号
     */
    @Id
    @Column(name = "`id`")
    private Long id;

    /**
     * 父级编号
     */
    @Column(name = "`parent_id`")
    private Long parentId;

    /**
     * 所有父级编号
     */
    @Column(name = "`parent_ids`")
    private String parentIds;

    /**
     * 名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 排序
     */
    @Column(name = "`sort`")
    private Integer sort;

    /**
     * 路由
     */
    @Column(name = "`link`")
    private String link;

    /**
     * 图标
     */
    @Column(name = "`icon`")
    private String icon;

    /**
     * 菜单类型(0普通菜单 1 日期框 2 组合下拉框)
     */
    @Column(name = "`menu_type`")
    private String menuType;

    /**
     * 是否在左侧菜单中显示
     */
    @Column(name = "`left_show`")
    private Boolean leftShow;

    @Column(name = "`is_show`")
    private Boolean isShow;

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
     * 获取父级编号
     *
     * @return parent_id - 父级编号
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父级编号
     *
     * @param parentId 父级编号
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取所有父级编号
     *
     * @return parent_ids - 所有父级编号
     */
    public String getParentIds() {
        return parentIds;
    }

    /**
     * 设置所有父级编号
     *
     * @param parentIds 所有父级编号
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取路由
     *
     * @return link - 路由
     */
    public String getLink() {
        return link;
    }

    /**
     * 设置路由
     *
     * @param link 路由
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * 获取图标
     *
     * @return icon - 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取菜单类型(0普通菜单 1 日期框 2 组合下拉框)
     *
     * @return menu_type - 菜单类型(0普通菜单 1 日期框 2 组合下拉框)
     */
    public String getMenuType() {
        return menuType;
    }

    /**
     * 设置菜单类型(0普通菜单 1 日期框 2 组合下拉框)
     *
     * @param menuType 菜单类型(0普通菜单 1 日期框 2 组合下拉框)
     */
    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    /**
     * 获取是否在左侧菜单中显示
     *
     * @return left_show - 是否在左侧菜单中显示
     */
    public Boolean getLeftShow() {
        return leftShow;
    }

    /**
     * 设置是否在左侧菜单中显示
     *
     * @param leftShow 是否在左侧菜单中显示
     */
    public void setLeftShow(Boolean leftShow) {
        this.leftShow = leftShow;
    }

    /**
     * @return is_show
     */
    public Boolean getIsShow() {
        return isShow;
    }

    /**
     * @param isShow
     */
    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
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