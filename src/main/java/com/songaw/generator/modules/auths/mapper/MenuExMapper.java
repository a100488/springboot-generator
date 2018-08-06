package com.songaw.generator.modules.auths.mapper;

import com.songaw.generator.modules.auths.pojo.dto.MenuDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuExMapper{
    MenuDto findOneWithRolesById(Long id);

    void insertBatchMenuJoinApi(@Param("menuId")Long menuId,@Param("apis")List<String> apis);
}