package com.jobeth.dto;

import com.jobeth.vo.MenuVO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * description 描述
 *
 * @author Jobeth
 * @date Created by IntelliJ IDEA on 14:53 2020/4/10
 */
@Data
public class UserDTO implements UserDetails, Serializable {
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 状态：0：禁用 1：启用
     */
    private Boolean enabled;
    /**
     * 用户权限
     */
    private Collection<GrantedAuthority> authorities;
    /**
     * 账户是否过期
     */
    private Boolean accountNonExpired;
    /**
     * 账户是否被锁定
     */
    private Boolean accountNonLocked;
    /**
     * 用户角色
     */
    private List<RoleDTO> roleList;

    private List<MenuVO> menuVOList;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (roleList != null) {
            for (RoleDTO role : roleList) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        }
        return authorities;
    }
}
