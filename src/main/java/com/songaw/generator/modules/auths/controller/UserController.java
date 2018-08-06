package com.songaw.generator.modules.auths.controller;

import com.github.pagehelper.PageHelper;
import com.songaw.generator.common.pojo.dto.PageInfoDto;
import com.songaw.generator.common.pojo.dto.Result;
import com.songaw.generator.modules.auths.entity.User;
import com.songaw.generator.modules.auths.pojo.dto.UserDto;
import com.songaw.generator.modules.auths.pojo.dto.UserDtoTest2;
import com.songaw.generator.modules.auths.pojo.vo.AddUserVo;
import com.songaw.generator.modules.auths.pojo.vo.SearchUserVo;
import com.songaw.generator.modules.auths.pojo.vo.UpdateUserVo;
import com.songaw.generator.modules.auths.service.UserService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author songaw
 * @date 2018/7/26 11:09
 */
@Api(value = "用户管理接口", tags = { "用户管理接口" })
@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    UserService userService;


    /**
     * 注册用户
     * @param vo
     */
    @ApiOperation(value = "添加用户", notes = "添加用户")
    @ApiImplicitParam(name = "vo", value = "用户账号详细实体addUserVo", required = true, dataType = "AddUserVo")
    @ApiResponses({ @ApiResponse(code = 400, message = "请求参数没填好"), @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对", response = Result.class) })
    @PostMapping
    public Result<UserDto> add(@RequestBody AddUserVo vo) {
        if(StringUtils.isNotBlank(vo.getUserName())){
            User user = new User();
            user.setUserName(vo.getUserName());
            List<User> list= userService.select(user);
            if(!CollectionUtils.isEmpty(list)&&list.size()>0){
                try {
                    if (list.size() > 1) {
                        return Result.getSystemErrorResult("该用户名已存在!");
                    }
                }catch (Exception e){
                    return Result.getSystemErrorResult("该用户名已存在!");
                }
            }
        }
        User user = new User();
        BeanUtils.copyProperties(vo,user);
        String hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hash);
        userService.insert(user);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return Result.getSuccessResult(userDto);
    }
    @ApiOperation(value = "修改用户", notes = "修改用户")
    @ApiImplicitParam(name = "vo", value = "用户账号详细实体", required = true, dataType = "UpdateUserVo")
    @ApiResponses({ @ApiResponse(code = 400, message = "请求参数没填好"), @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对", response = Result.class) })
    @PutMapping
    public Result<Integer> updateAppAccountAccount(@RequestBody UpdateUserVo vo){
        try {

            Long id=vo.getId();
            if(id!=null){
                return Result.getSystemErrorResult("传入参数错误");
            }

            if(StringUtils.isNotBlank(vo.getUserName())){
                User user = new User();
                user.setUserName(vo.getUserName());
                List<User> list= userService.select(user);
                if(!CollectionUtils.isEmpty(list)&&list.size()>0){
                    try {
                        if (list.size() > 1) {
                            return Result.getSystemErrorResult("该用户名已存在!");
                        } else if (list.size() == 1 && !list.get(0).getId().equals(vo.getId())) {
                            return Result.getSystemErrorResult("该用户名已存在!");
                        }
                    }catch (Exception e){
                        return Result.getSystemErrorResult("该用户名已存在!");
                    }
                }
            }
            User user = new User();
            BeanUtils.copyProperties(vo,user);
            if(StringUtils.isNotBlank(vo.getPassword())) {
                user.setPassword(DigestUtils.md5DigestAsHex((user.getPassword()).getBytes()));
            }
            userService.update(user);
            return Result.getSuccessResult(1);
        }catch (Exception e){
            return Result.getSystemErrorResult(e.getMessage());
        }
    }
    @ApiOperation(value = "删除用户", notes = "删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String")
    @ApiResponses({ @ApiResponse(code = 400, message = "请求参数没填好"), @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对", response = Result.class) })
    @DeleteMapping(value = "/{id}")
    public Result<Integer> deleteUserById(@PathVariable("id") Long id){
        try {
            User c =userService.selectByPk(id);
            if(c!=null){
                c.setDeleteFlag("1");
                userService.update(c);
            }else{
                return Result.getSystemErrorResult("不存在");
            }

            return Result.getSuccessResult(1);
        }catch (Exception e){
            return Result.getSystemErrorResult(e.getMessage());
        }
    }
    @ApiOperation(value = "删除一组用户", notes = "删除一组用户")
    @ApiImplicitParam(name = "ids", value = "用户ids:id1,id2,id3", required = true, dataType = "String")
    @ApiResponses({ @ApiResponse(code = 400, message = "请求参数没填好"), @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对", response = Result.class) })
    @DeleteMapping(value = "/deleteUserByIds")
    public Result<Integer> deleteUserByIds(@RequestParam("ids") String ids){
        try {
            String [] idstr=ids.split(",");
            List<Long> idList = new ArrayList<>();
            for(String id:idstr){
                idList.add(Long.parseLong(id));
            }
            userService.deleteByPks(idList);
            return Result.getSuccessResult(1);
        }catch (Exception e){
            return Result.getSystemErrorResult(e.getMessage());
        }
    }
    @ApiOperation("获取用户信息")
    @GetMapping(value = "/{id}")
    @ApiImplicitParam(name = "id", value = "用户ID", dataType = "Long", paramType = "query")
    @ResponseBody
    public Result<UserDto> getCategoryById(@PathVariable("id") Long id) {
        User user = userService.selectByPk(id);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return Result.getSuccessResult(userDto);
    }


    @ApiOperation(value = "简单分页查询用户", notes = "简单分页查询用户")
    @ApiResponses({ @ApiResponse(code = 400, message = "请求参数没填好"), @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对", response = Result.class) })
    @RequestMapping(path = "/list/search",method = RequestMethod.GET)
    public Result<PageInfoDto> findUsersPage(@ModelAttribute SearchUserVo searchVo){
        PageHelper.startPage(searchVo.getPageIndex(),searchVo.getPageSize());
        User searchUser = new User();
        BeanUtils.copyProperties(searchVo,searchUser);
        List<User>  list=  userService.select(searchUser);
        PageInfoDto<User> thePage=new PageInfoDto<User>(list);
        return Result.getSuccessResult(thePage);
    }
    @ApiOperation(value = "复杂分页查询用户", notes = "复杂分页查询用户")
    @ApiResponses({ @ApiResponse(code = 400, message = "请求参数没填好"), @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对", response = Result.class) })
    @RequestMapping(path = "/list/search2",method = RequestMethod.GET)
    public Result<PageInfoDto<UserDto>> findUsersPage2(@ModelAttribute SearchUserVo searchVo){
        PageHelper.startPage(searchVo.getPageIndex(),searchVo.getPageSize());
        List<UserDto>  list=  userService.findUserDtos(searchVo);
        PageInfoDto<UserDto> thePage=new PageInfoDto<UserDto>(list);
        return Result.getSuccessResult(thePage);
    }
    @ApiOperation(value = "复杂分页查询用户2", notes = "复杂分页查询用户2")
    @ApiResponses({ @ApiResponse(code = 400, message = "请求参数没填好"), @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对", response = Result.class) })
    @RequestMapping(path = "/list/search3",method = RequestMethod.GET)
    public Result<PageInfoDto<UserDtoTest2>> findUsersPage3(@ModelAttribute SearchUserVo searchVo){
        PageHelper.startPage(searchVo.getPageIndex(),searchVo.getPageSize());
        List<UserDtoTest2>  list=  userService.findUsersTest2(searchVo);
        PageInfoDto<UserDtoTest2> thePage=new PageInfoDto<UserDtoTest2>(list);
        return Result.getSuccessResult(thePage);
    }
}
