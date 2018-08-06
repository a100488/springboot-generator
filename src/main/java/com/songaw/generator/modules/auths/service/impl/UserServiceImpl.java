package com.songaw.generator.modules.auths.service.impl;

import com.songaw.generator.common.service.impl.BaseServiceImpl;
import com.songaw.generator.modules.auths.entity.User;
import com.songaw.generator.modules.auths.mapper.UserExMapper;
import com.songaw.generator.modules.auths.mapper.UserMapper;
import com.songaw.generator.modules.auths.pojo.dto.UserDto;
import com.songaw.generator.modules.auths.pojo.dto.UserDtoTest2;
import com.songaw.generator.modules.auths.pojo.vo.SearchUserVo;
import com.songaw.generator.modules.auths.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * TODO
 *
 * @author songaw
 * @date 2018/7/26 16:34
 */
@Data
@Service
public class UserServiceImpl extends BaseServiceImpl<User,Serializable> implements UserService  {

    @Autowired
    UserMapper baseMapper;
    @Autowired
    UserExMapper userExMapper;
    @Override
    public List<UserDto> findUserDtos(SearchUserVo searchUserVo) {

        return userExMapper.findUsers(searchUserVo);
    }

    @Override
    public List<UserDtoTest2> findUsersTest2(SearchUserVo user) {
        return userExMapper.findUsersTest2(user);
    }
}
