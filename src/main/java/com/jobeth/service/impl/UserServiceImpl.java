package com.jobeth.service.impl;

import com.jobeth.dto.RoleDTO;
import com.jobeth.dto.UserDTO;
import com.jobeth.mapper.PermissionMapper;
import com.jobeth.mapper.RoleMapper;
import com.jobeth.po.Permission;
import com.jobeth.po.Role;
import com.jobeth.po.User;
import com.jobeth.mapper.UserMapper;
import com.jobeth.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jobeth.utils.MenuUtil;
import com.jobeth.vo.MenuVO;
import com.jobeth.vo.RoleVO;
import com.jobeth.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author Jobeth
 * @since 2020-04-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final PermissionMapper permissionMapper;

    public UserServiceImpl(UserMapper userMapper, RoleMapper roleMapper, PermissionMapper permissionMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public UserDTO loadUserByUsername(String username) {
        User user = userMapper.selectByUsername(username);
        if (user != null) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            //查角色
            List<Role> roleList = roleMapper.listByUserId(user.getId());
            List<RoleDTO> roleDTOList = new ArrayList<>();
            for (Role role : roleList) {
                RoleDTO roleDTO = new RoleDTO();
                BeanUtils.copyProperties(role, roleDTO);
                roleDTOList.add(roleDTO);
            }
            userDTO.setRoleList(roleDTOList);
            return userDTO;
        }
        return null;
    }

    @Override
    public void generateUserAllInfo(UserVO userVO) {
        //查角色
        List<Role> roleList = roleMapper.listByUserId(userVO.getId());
        List<RoleVO> roles = new ArrayList<>();
        for (Role role : roleList) {
            RoleVO roleVO = new RoleVO();
            BeanUtils.copyProperties(role, roleVO);
            System.out.println(roleVO);
            roles.add(roleVO);
        }
        userVO.setRoleList(roles);
        //查菜单给前端,菜单类型type=menu
        Map<String, String> map = new HashMap<>(16);
        map.put("userId", userVO.getId().toString());
        map.put("type", "menu");
        List<Permission> permissions = permissionMapper.listPermissionByMap(map);
        List<MenuVO> menuVOList = new ArrayList<>();
        for (Permission permission : permissions) {
            MenuVO menuVO = new MenuVO();
            BeanUtils.copyProperties(permission, menuVO);
            menuVOList.add(menuVO);
        }
        List<MenuVO> treeMenu = MenuUtil.generateTreeMenu(menuVOList);
        userVO.setTreeMenu(treeMenu);
    }
}
