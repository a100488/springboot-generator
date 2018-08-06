package com.songaw.generator.modules.auths.mapper;

import com.songaw.generator.modules.auths.pojo.dto.BackendApiDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO
 *
 * @author songaw
 * @date 2018/7/31 16:44
 */
public interface BackendApiExMapper {
    List<BackendApiDto> findByPathStartsWithAndMethod(@Param("path")String path,@Param("method")   String method);
    List<BackendApiDto> findByPathAndMethod(@Param("path") String path, @Param("method")  String method);
    List<BackendApiDto> findBackendApiByMenuId(@Param("menuId") Long menuId);
    void deleteMenuJoinBackend(@Param("menuId") Long menuId);
}
