package com.songaw.generator.modules.auths.pojo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户实体返回
 *
 * @author songaw
 * @date 2018/7/26 15:25
 */
@Data
public class UserDto  implements Serializable {

    private static final long serialVersionUID = 6990082044286286197L;


    @ApiModelProperty(value = "ID ")
    private Long id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")

    private String userName;

    @JSONField(serialize=false)
    private String password;
    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名")
    private String realName;

}
