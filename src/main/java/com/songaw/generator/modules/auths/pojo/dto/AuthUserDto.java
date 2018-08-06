package com.songaw.generator.modules.auths.pojo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.songaw.generator.modules.auths.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

/**
 * 权限相关的用户封装
 *
 * @author songaw
 * @date 2018/7/26 15:25
 */
@Data
public class AuthUserDto extends User implements Serializable,UserDetails {


    private static final long serialVersionUID = 4627181341779158481L;
    @ApiModelProperty(value = "ID")
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

    @ApiModelProperty(value = "角色列表")
    private List<RoleDto> roles;

    @ApiModelProperty(value = "菜单列表")
    private List<MenuDto> menus;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return userName;
    }
    // 帐户是否过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    // 帐户是否被冻结
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    // 帐户密码是否过期，一般有的密码要求性高的系统会使用到，比较每隔一段时间就要求用户重置密码
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    // 帐号是否可用
    @Override
    public boolean isEnabled() {

        return true;
    }
}
