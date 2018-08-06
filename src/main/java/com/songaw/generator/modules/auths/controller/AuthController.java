package com.songaw.generator.modules.auths.controller;

import com.songaw.generator.common.pojo.dto.Result;
import com.songaw.generator.modules.auths.entity.Menu;
import com.songaw.generator.modules.auths.pojo.dto.JwtLoginDto;
import com.songaw.generator.modules.auths.pojo.dto.MenuDto;
import com.songaw.generator.modules.auths.pojo.vo.AddUserVo;
import com.songaw.generator.modules.auths.pojo.vo.JwtLoginVo;
import com.songaw.generator.modules.auths.security.TreeMenuBuilder;
import com.songaw.generator.modules.auths.service.AuthService;
import com.songaw.generator.modules.auths.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "权限接口", tags = { "权限接口" })
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;
    @Value("${jwt.expiration}")
    private Long expiration;
    @Autowired
    private AuthService authService;
    @Autowired
    MenuService menuService;

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public Result createAuthenticationToken(
            @RequestBody JwtLoginVo authenticationRequest) throws AuthenticationException {
       JwtLoginDto jwtLoginDto = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        return Result.getSuccessResult(jwtLoginDto);
    }

    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public Result refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException{
        String token = request.getHeader(tokenHeader);
        JwtLoginDto jwtLoginDto =  authService.refresh(token);
        if(jwtLoginDto == null) {
            return  Result.getParamsErrorResult("刷新失败");
        } else {
            return Result.getSuccessResult(jwtLoginDto);

        }
    }

    @RequestMapping(value = "${jwt.route.authentication.register}", method = RequestMethod.POST)
    public Result register(@RequestBody AddUserVo addedUser) throws AuthenticationException{
        return authService.register(addedUser);
    }

    @ApiOperation(value = "根据用户获取用户的菜单树形", notes = "根据用户获取用户的菜单树形")
    @ApiResponses({ @ApiResponse(code = 400, message = "请求参数没填好"), @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对", response = Result.class) })
    @RequestMapping(path = "/trees",method = RequestMethod.GET)
    public Result<List<MenuDto>> auths(){
      /*  AuthUserDto authUserDto = (AuthUserDto) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();*/
    //    List<MenuDto> menuDtos =authService.getMenusByUserId(authUserDto.getId());
        Example example = new Example(Menu.class);
        example.setOrderByClause("`parent_ids` ASC,sort ASC");
        List<Menu> menuDtos=  menuService.selectByExample(example);
       // List<Menu> menuDtos =menuService.selectAll();
        menuDtos=menuDtos.stream().filter(item->item.getIsShow()).collect(Collectors.toList());
        List<MenuDto> list = new ArrayList<>();
        for(Menu menu:menuDtos){
            MenuDto menuDto = new MenuDto();
            BeanUtils.copyProperties(menu,menuDto);
            list.add(menuDto);
        }
        list=TreeMenuBuilder.bulid(list);
        return Result.getSuccessResult(list);
    }

}
