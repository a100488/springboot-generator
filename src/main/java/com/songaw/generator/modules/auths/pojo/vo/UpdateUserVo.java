package com.songaw.generator.modules.auths.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 修改用户
 *
 * @author songaw
 * @date 2018/7/1 19:46
 */
@ApiModel(value = "UpdateUserVo")
@Data
public class UpdateUserVo {
    /**
     * id
     */
    @ApiModelProperty(value = "用户id")
    private Long id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名")
    private String realName;

}
