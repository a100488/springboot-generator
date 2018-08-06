package com.songaw.generator.modules.auths.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO
 *
 * @author songaw
 * @date 2018/4/1 19:46
 */
@ApiModel(value = "AddUserVo")
@Data
public class AddUserVo {
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
