package com.songaw.generator.modules.auths.service;

import com.songaw.generator.common.service.BaseService;
import com.songaw.generator.modules.auths.entity.User;
import com.songaw.generator.modules.auths.pojo.dto.UserDto;
import com.songaw.generator.modules.auths.pojo.dto.UserDtoTest2;
import com.songaw.generator.modules.auths.pojo.vo.SearchUserVo;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * TODO
 *
 * @author songaw
 * @date 2018/7/26 14:34
 */
@Service
public interface UserService extends BaseService<User,Serializable> {
    List<UserDto> findUserDtos(SearchUserVo searchUserVo);
    List<UserDtoTest2> findUsersTest2(SearchUserVo user);
}
