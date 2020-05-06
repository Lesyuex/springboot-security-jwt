package com.jobeth.config.security.filter;

import com.jobeth.po.Permission;
import com.jobeth.po.Role;
import com.jobeth.service.PermissionService;
import com.jobeth.service.RoleService;
import com.jobeth.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 自定义的元数据源类，用来提供鉴权过程中，访问资源所需的角色
 *
 * @author Jobeth
 * @since 2020/4/16 15:48
 */
@Component
@Slf4j
public class PermissionMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final PermissionService permissionService;
    private final RoleService roleService;

    public PermissionMetadataSource(PermissionService permissionService, RoleService roleService) {
        this.permissionService = permissionService;
        this.roleService = roleService;
    }

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) {
        log.info("【鉴权-开始】");
        //从object中得到需要访问的资源
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        //从数据库取所有资源和访问其的角色
        List<Permission> permissionList = permissionService.list(null);
        for (Permission permission : permissionList) {
            List<Role> roleList = roleService.listByPermissionId(permission.getId());
            int size = roleList.size();
            String[] roles = new String[size];
            if (antPathMatcher.match(permission.getPath(), requestUrl) && size > 0) {
                for (int i = 0; i < size; i++) {
                    roles[i] = roleList.get(i).getName();
                }
                //请求所需要的角色
                log.info("【鉴权-权限所需角色:{}】", JsonUtil.toJsonString(roles));
                return SecurityConfig.createList(roles);
            }
        }
        //没有匹配上的资源，都是登录访问
        log.info("【鉴权-数据库未匹配到url,默认登录可以访问：{}】", "ROLE_LOGIN");
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
