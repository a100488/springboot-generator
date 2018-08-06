package com.songaw.generator.modules.auths.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by stephan on 20.03.16.
 */
@Data
public class JwtLoginDto implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;
    @ApiModelProperty(value = "token")
    private final String token;
    @ApiModelProperty(value = "ID ")
    private final Long id;
    @ApiModelProperty(value = "用户名")
    private final String userName;

    @ApiModelProperty(value = "菜单列表")
    private final List<MenuDto> menus;
}