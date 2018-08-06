package com.songaw.generator.modules.auths.service.impl;

import com.songaw.generator.common.pojo.dto.Result;
import com.songaw.generator.modules.auths.entity.User;
import com.songaw.generator.modules.auths.mapper.SecurityExMapper;
import com.songaw.generator.modules.auths.pojo.dto.AuthUserDto;
import com.songaw.generator.modules.auths.pojo.dto.JwtLoginDto;
import com.songaw.generator.modules.auths.pojo.dto.MenuDto;
import com.songaw.generator.modules.auths.pojo.dto.UserDto;
import com.songaw.generator.modules.auths.pojo.vo.AddUserVo;
import com.songaw.generator.modules.auths.service.AuthService;
import com.songaw.generator.modules.auths.service.UserService;
import com.songaw.generator.modules.auths.security.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * TODO
 *
 * @author songaw
 * @date 2018/7/27 16:01
 */
@Service
public class AuthServiceImpl implements AuthService,UserDetailsService{
    @Autowired
    SecurityExMapper securityExMapper;
    @Autowired
    UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AuthUserDto authUserDto =  securityExMapper.loadUserByUsername(username);
        if(authUserDto!=null){

            return authUserDto;
        }else{
            throw new UsernameNotFoundException("用户["+username+"]不存在");
        }
    }
    public Result register(AddUserVo vo){
        if(StringUtils.isNotBlank(vo.getUserName())){
            User user = new User();
            user.setUserName(vo.getUserName());
            List<User> list= userService.select(user);
            if(!CollectionUtils.isEmpty(list)&&list.size()>0){

                return Result.getSystemErrorResult("该用户名已存在!");

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
    public JwtLoginDto login(String username, String password){
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthUserDto userDetails= (AuthUserDto) authentication.getPrincipal();
        final String token = jwtTokenUtil.generateToken(userDetails);
        JwtLoginDto jwtLoginDto = new JwtLoginDto(token,userDetails.getId(),userDetails.getUsername(),userDetails.getMenus());
        return jwtLoginDto;

    }
    public   JwtLoginDto refresh(String oldToken){
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        AuthUserDto userDetails = (AuthUserDto) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token)){

            JwtLoginDto jwtLoginDto = new JwtLoginDto(jwtTokenUtil.refreshToken(token),userDetails.getId(),userDetails.getUsername(),userDetails.getMenus());
            return jwtLoginDto;
        }
        return null;


    }

    @Override
    public List<MenuDto> getMenusByUserId(Long userId) {
        return securityExMapper.getMenusByUserId(userId);
    }

}
