package com.songaw.generator.modules.auths.security;

import com.songaw.generator.modules.auths.entity.BackendApi;
import com.songaw.generator.modules.auths.mapper.BackendApiExMapper;
import com.songaw.generator.modules.auths.mapper.MenuExMapper;
import com.songaw.generator.modules.auths.pojo.dto.BackendApiDto;
import com.songaw.generator.modules.auths.pojo.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author songaw
 * @date 2018/7/31 16:32
 */
public class DaoSecurityMetadataSource implements org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource{
    private FilterInvocationSecurityMetadataSource superMetadataSource;
    @Autowired
    @Lazy
    private MenuExMapper  menuExMapper;
    @Autowired
    @Lazy
    private BackendApiExMapper backendApiExMapper;
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    public DaoSecurityMetadataSource(FilterInvocationSecurityMetadataSource expressionBasedFilterInvocationSecurityMetadataSource){
        this.superMetadataSource = expressionBasedFilterInvocationSecurityMetadataSource;


    }

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();



    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) object;

        List<RoleDto> neededRoles = this.getRequestNeededRoles(fi.getRequest().getMethod(), fi.getRequestUrl());

        if (neededRoles != null) {
            return SecurityConfig.createList(neededRoles.stream().map(role -> role.getName()).collect(Collectors.toList()).toArray(new String[]{}));
        }
        System.out.println("查询权限==========================================查询权限==========================查询权限=========================");
        //  返回默认配置
        return superMetadataSource.getAttributes(object);
    }

    public List<RoleDto> getRequestNeededRoles(String method, String path) {
        String rawPath = path;
        //  remove parameters
        if(path.indexOf("?")>-1){
            path = path.substring(0,path.indexOf("?"));
        }
        // /menus/{id}
        BackendApi searchBackendApi = new BackendApi();
        searchBackendApi.setPath(path);
        searchBackendApi.setMethod(method);
        List<BackendApiDto> apis = backendApiExMapper.findByPathAndMethod(path,method);
        BackendApiDto api = null;
        if(apis!=null&&apis.size()>0){
            api= apis.get(0);
        }
        if (api == null){
            // try fetch by remove last path
            api = loadFromSimilarApi(method, path, rawPath);
        }

        if (api != null && api.getMenus().size() > 0) {
            return api.getMenus()
                    .stream()
                    .flatMap(menu -> menuExMapper.findOneWithRolesById(menu.getId()).getRoles().stream())
                    .collect(Collectors.toList());
        }
        return null;
    }

    private BackendApiDto loadFromSimilarApi(String method, String path, String rawPath) {
        if(path.lastIndexOf("/")>-1){
            path = path.substring(0,path.lastIndexOf("/"));
            List<BackendApiDto> apis = backendApiExMapper.findByPathStartsWithAndMethod(path, method);

            // 如果为空，再去掉一层path
            while(apis==null){
                if(path.lastIndexOf("/")>-1) {
                    path = path.substring(0, path.lastIndexOf("/"));
                    apis = backendApiExMapper.findByPathStartsWithAndMethod(path, method);
                }else{
                    break;
                }
            }

            if(apis!=null){
                for(BackendApiDto backendApi : apis){
                    if (antPathMatcher.match(backendApi.getPath(), rawPath)) {
                        return backendApi;
                    }
                }
            }
        }
        return null;
    }
 /*   public void init(MenuExMapper menuExMapper, BackendApiExMapper backendApiExMapper) {
        this.menuExMapper = menuExMapper;
        this.backendApiExMapper = backendApiExMapper;
    }*/
    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
