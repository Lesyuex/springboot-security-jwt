package com.jobeth.vo;

import com.google.gson.annotations.JsonAdapter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Desc
 *
 * @author Jobeth
 * @since 2020/4/24 21:11
 */
@Data
@Getter
@Setter
@ToString
public class MenuVO {
    /**
     * 权限id
     */
    private Long id;

    /**
     * 菜单名
     */
    private String name;

    /**
     * 资源路径
     */
    private String path;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 图标
     */
    private String icon;

    /**
     * 组件资源(用于匹配component组件)
     */
    private String component;

    /**
     * 子菜单
     */
    private List<MenuVO> children;
}
