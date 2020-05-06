package com.jobeth.utils;

import com.jobeth.vo.MenuVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单工具类
 *
 * @author Jobeth
 * @since 2020/4/24 21:27
 */
public class MenuUtil {
    private final static String ROOT_MENU = "0";

    /**
     * 生成树状菜单
     *
     * @param menus 所用菜单（未格式的菜单）
     * @return 格式后的树状菜单
     */
    public static List<MenuVO> generateTreeMenu(List<MenuVO> menus) {
        //存放树状菜单
        ArrayList<MenuVO> menuList = new ArrayList<>();
        //找到根节点
        for (MenuVO menuVO : menus) {
            if (menuVO.getParentId().toString().equals(MenuUtil.ROOT_MENU)) {
                menuList.add(menuVO);
            }
        }
        //找到根节点的孩子节点
        for (MenuVO menu : menuList) {
            List<MenuVO> childrenList = getChildrenList(menus, menu.getId().toString());
            menu.setChildren(childrenList);
        }
        return menuList;
    }

    /**
     * 生成某个菜单的子菜单
     *
     * @param menus    所有菜单
     * @param parentId 父菜单Id
     * @return 子菜单集合
     */
    public static List<MenuVO> getChildrenList(List<MenuVO> menus, String parentId) {
        //存放子菜单
        ArrayList<MenuVO> menu = new ArrayList<>();
        //寻找菜单（parentId）的次级菜单
        for (MenuVO menuVO : menus) {
            if (menuVO.getParentId().toString().equals(parentId)) {
                menu.add(menuVO);
            }
        }
        //未找到次级菜单,直接返回
        if (menu.isEmpty()) {
            return new ArrayList<>();
        }
        //继续找到次级菜单的子菜单
        for (MenuVO menuVO : menu) {
            List<MenuVO> childrenList = getChildrenList(menus, menuVO.getId().toString());
            menuVO.setChildren(childrenList);
        }
        return menu;
    }
}
