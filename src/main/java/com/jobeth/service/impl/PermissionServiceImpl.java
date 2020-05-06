package com.jobeth.service.impl;

import com.jobeth.po.Permission;
import com.jobeth.mapper.PermissionMapper;
import com.jobeth.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jobeth
 * @since 2020-04-24
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    private final PermissionMapper permissionMapper;

    public PermissionServiceImpl(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Override
    public List<Permission> listPermissionByMap(Map<String, String> map) {
        return permissionMapper.listPermissionByMap(map);
    }
}
