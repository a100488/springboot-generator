package com.songaw.generator.modules.auths.pojo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.songaw.generator.modules.auths.entity.Menu;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 权限相关的角色信息
 *
 * @author songaw
 * @date 2018/7/27 15:18
 */
@Data
public class MenuDto extends Menu {
    @JSONField(serialize=false)
    private List<RoleDto> roles ;

    @ApiModelProperty(value = "拥有的接口")
    private List<BackendApiDto> apis ;
    @ApiModelProperty(value = "子菜单")
    private List<MenuDto> children;
    /**
     * 名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String name;
    @ApiModelProperty(value = "菜单ID")
    private Long id;

    /**
     * 父级编号
     */
    @ApiModelProperty(value = "菜单父级ID")
    private Long parentId;


    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 路由
     */
    @ApiModelProperty(value = "路由")
    private String link;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;

    /**
     * 菜单类型(0普通菜单 1 日期框 2 组合下拉框)
     */
    @ApiModelProperty(value = "菜单类型(0普通菜单 1 日期框 2 组合下拉框)")
    private String menuType;

    /**
     * 是否在菜单中显示
     */
    @ApiModelProperty(value = "是否在左侧菜单中显示")
    private Boolean leftShow;
}
