package com.songaw.generator.modules.auths.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 修改菜单
 *
 * @author songaw
 * @date 2018/8/2 10:25
 */
@ApiModel(value = "UpdateMenuVo")
@Data
public class UpdateMenuVo {
    @NotNull
    @ApiModelProperty(value = "编号")
    private Long id;
    /**
     * 父级编号
     */
    @NotNull
    @ApiModelProperty(value = "父级编号")
    private Long parentId;

    @NotNull
    @ApiModelProperty(value = "父级编号s")
    private String parentIds;


    /**
     * 名称
     */
    @NotNull
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 排序
     */
    @NotNull
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 路由
     */
    @NotNull
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
    @NotNull
    @ApiModelProperty(value = "菜单类型(0普通菜单 1 日期框 2 组合下拉框)")
    private String menuType;

    /**
     * 是否在左侧菜单中显示
     */
    @NotNull
    @ApiModelProperty(value = "是否在左侧菜单中显示")
    private Boolean leftShow;

    @NotNull
    @ApiModelProperty(value = "是否显示")
    private Boolean isShow;

    @NotNull
    @ApiModelProperty(value = "接口列表")
    private List<String> apis = new ArrayList<>();
}
