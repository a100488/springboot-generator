package com.songaw.generator.modules.auths.mapper;


import com.songaw.generator.modules.auths.pojo.dto.UserDto;
import com.songaw.generator.modules.auths.pojo.dto.UserDtoTest2;
import com.songaw.generator.modules.auths.pojo.vo.SearchUserVo;

import java.util.List;

/**
 * @author Anwei.S
 * @CreateDate 2017年2月15日 下午1:50:09
 * @Description:
 */
public interface UserExMapper {
	/**
	 * 根据对象中字段进行查询(userId,mobile)
	 * @param user
	 * @return
	 */
	List<UserDto> findUsers(SearchUserVo user);
	/**
	 * 根据对象中字段进行查询(userId,mobile)
	 * @param user
	 * @return
	 */
	List<UserDtoTest2> findUsersTest2(SearchUserVo user);


}
