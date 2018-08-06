package com.songaw.generator.modules.auths.pojo.dto;

import com.songaw.generator.modules.auths.entity.Role;
import org.springframework.security.core.GrantedAuthority;

/**
 * 权限相关的角色信息
 *
 * @author songaw
 * @date 2018/7/27 15:18
 */
public class RoleDto extends Role implements  GrantedAuthority {
    private static final long serialVersionUID = 7938815305887155880L;

    @Override
    public String getAuthority() {
        return getCode();
    }
}
