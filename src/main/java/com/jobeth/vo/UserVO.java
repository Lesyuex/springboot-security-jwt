package com.jobeth.vo;

import lombok.Data;

import java.util.List;

/**
 * Desc
 *
 * @author Jobeth
 * @since 2020/5/3 23:00
 */
@Data
public class UserVO {
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 角色集合
     */
    private List<RoleVO> roleList;
    /**
     * 菜单集合
     */
    private List<MenuVO> treeMenu;
}
