package com.songaw.generator.modules.auths.mapper;

import com.songaw.generator.modules.auths.pojo.dto.AuthUserDto;
import com.songaw.generator.modules.auths.pojo.dto.MenuDto;
import com.songaw.generator.modules.auths.pojo.dto.RoleDto;

import java.util.List;

/**
 * 权限管理Mapper
 *
 * @author songaw
 * @date 2018/7/27 16:02
 */
public interface SecurityExMapper {
    AuthUserDto loadUserByUsername(String username);

    /**
     * 获取用户拥有的角色
     * @param userId
     * @return
     */
    List<RoleDto> getRolesByUserId(Long userId);
    /**
     * 获取用户拥有的权限
     * @param userId
     * @return
     */
    List<MenuDto> getMenusByUserId(Long userId);

}
