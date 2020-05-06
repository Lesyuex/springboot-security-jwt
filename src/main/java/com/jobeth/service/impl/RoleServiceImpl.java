package com.jobeth.service.impl;

import com.jobeth.po.Role;
import com.jobeth.mapper.RoleMapper;
import com.jobeth.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jobeth
 * @since 2020-04-10
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public List<Role> listByPermissionId(Long permissionId) {
        return roleMapper.listByPermissionId(permissionId);
    }
}
