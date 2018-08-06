package com.songaw.generator.modules.auths.service;

import com.songaw.generator.common.pojo.dto.Result;
import com.songaw.generator.modules.auths.pojo.dto.JwtLoginDto;
import com.songaw.generator.modules.auths.pojo.dto.MenuDto;
import com.songaw.generator.modules.auths.pojo.vo.AddUserVo;

import java.util.List;

/**
 * TODO
 *
 * @author songaw
 * @date 2018/7/27 17:32
 */
public interface AuthService {
    Result register(AddUserVo userToAdd);
    JwtLoginDto login(String username, String password);
    JwtLoginDto refresh(String oldToken);

    List<MenuDto> getMenusByUserId(Long userId);
}
