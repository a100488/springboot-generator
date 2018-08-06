package com.songaw.generator.modules.auths.service.impl;

import com.songaw.generator.common.service.impl.BaseServiceImpl;
import com.songaw.generator.modules.auths.entity.Menu;
import com.songaw.generator.modules.auths.mapper.BackendApiExMapper;
import com.songaw.generator.modules.auths.mapper.MenuExMapper;
import com.songaw.generator.modules.auths.mapper.MenuMapper;
import com.songaw.generator.modules.auths.pojo.dto.BackendApiDto;
import com.songaw.generator.modules.auths.pojo.dto.MenuDto;
import com.songaw.generator.modules.auths.pojo.vo.AddMenuVo;
import com.songaw.generator.modules.auths.pojo.vo.UpdateMenuVo;
import com.songaw.generator.modules.auths.service.MenuService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * TODO
 *
 * @author songaw
 * @date 2018/8/1 16:01
 */
@Data
@Service
@Transactional
public class MenuServiceImpl extends BaseServiceImpl<Menu,Long> implements MenuService {
    @Autowired
    MenuMapper baseMapper;
    @Autowired
    MenuExMapper menuExMapper;
    @Autowired
    BackendApiExMapper backendApiExMapper;
    @Override
    public void addMenuAndApis(AddMenuVo addMenuVo) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(addMenuVo,menu);
        insert(menu);
        //apis
        if(addMenuVo.getApis().size()>0) {
            menuExMapper.insertBatchMenuJoinApi(menu.getId(), addMenuVo.getApis());
        }

    }

    @Override
    public void updateMenuAndApis(UpdateMenuVo updateMenuVo) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(updateMenuVo,menu);
        update(menu);
        //apis
        backendApiExMapper.deleteMenuJoinBackend(menu.getId());
        if(updateMenuVo.getApis().size()>0) {

            menuExMapper.insertBatchMenuJoinApi(menu.getId(), updateMenuVo.getApis());
        }

    }
    @Override
    public MenuDto findById(Long id) {
        Menu menu = baseMapper.selectByPrimaryKey(id);
        MenuDto menuDto = new MenuDto();
        BeanUtils.copyProperties(menu,menuDto);
        List<BackendApiDto> apiDtos= backendApiExMapper.findBackendApiByMenuId(id);
        menuDto.setApis(apiDtos);
        return menuDto;
    }

    @Override
    public void deleteMenuById(Long id) {
        Example example = new Example(Menu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("parentIds","%"+id);
        deleteByExample(example);
        deleteByPk(id);
    }


}
