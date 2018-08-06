package com.songaw.generator.modules.auths.pojo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.songaw.generator.modules.auths.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 用户实体返回
 *
 * @author songaw
 * @date 2018/7/26 15:25
 */
@Data
public class UserDtoTest2 extends User {
    
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


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+08:00")
    @JsonProperty("insertDate")
    public Date getInsertDate() {
        return new Date(super.getInsertTime());
    }
}
