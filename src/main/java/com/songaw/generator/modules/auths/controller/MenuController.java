package com.songaw.generator.modules.auths.controller;

import com.songaw.generator.common.pojo.dto.Result;
import com.songaw.generator.modules.auths.entity.Menu;
import com.songaw.generator.modules.auths.pojo.dto.MenuDto;
import com.songaw.generator.modules.auths.pojo.vo.AddMenuVo;
import com.songaw.generator.modules.auths.pojo.vo.UpdateMenuVo;
import com.songaw.generator.modules.auths.service.MenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.validation.Valid;
import java.util.List;

/**
 * TODO
 *
 * @author songaw
 * @date 2018/8/1 10:48
 */
@Api(value = "菜单管理接口", tags = { "菜单管理接口" })
@RestController
@RequestMapping("/v1/menus")
public class MenuController {
    @Autowired
    MenuService menuService;
    @RequestMapping(
            value = "/list",
            method = RequestMethod.GET)
    public Result<List<Menu>> list(){
        Example example = new Example(Menu.class);
        example.setOrderByClause("`parent_ids` ASC,sort ASC");
      List<Menu> list=  menuService.selectByExample(example);
      return Result.getSuccessResult(list);
    }
    @PostMapping
    public Result addMenuAndApis(@Valid @RequestBody AddMenuVo addMenuVo){
        menuService.addMenuAndApis(addMenuVo);
        return Result.getSuccessResult("添加成功");
    }
    @PutMapping
    public Result updateMenuAndApis(@Valid @RequestBody UpdateMenuVo updateMenuVo){
        menuService.updateMenuAndApis(updateMenuVo);
        return Result.getSuccessResult("添加成功");
    }
    //获取apidos
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public Result findById(@PathVariable("id") Long id) {
        MenuDto menuDto=menuService.findById(id);

        return Result.getSuccessResult(menuDto);
    }
    //获取apidos
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteMenuById(@PathVariable("id") Long id) {

        menuService.deleteMenuById(id);
        return Result.getSuccessResult(null);
    }
}
