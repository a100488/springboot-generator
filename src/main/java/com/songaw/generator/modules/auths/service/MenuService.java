package com.songaw.generator.modules.auths.service;

import com.songaw.generator.common.service.BaseService;
import com.songaw.generator.modules.auths.entity.Menu;
import com.songaw.generator.modules.auths.pojo.dto.MenuDto;
import com.songaw.generator.modules.auths.pojo.vo.AddMenuVo;
import com.songaw.generator.modules.auths.pojo.vo.UpdateMenuVo;

/**
 * TODO
 *
 * @author songaw
 * @date 2018/8/1 11:08
 */
public interface MenuService extends BaseService<Menu,Long> {
     void addMenuAndApis(AddMenuVo addMenuVo);
     void updateMenuAndApis(UpdateMenuVo updateMenuVo);
     MenuDto findById(Long id);
     void deleteMenuById(Long id);
}
