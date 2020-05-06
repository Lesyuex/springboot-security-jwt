package com.jobeth.service;

import com.jobeth.po.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jobeth
 * @since 2020-04-24
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 根据条件查找权限
     *
     * @param map map
     * @return List<Permission>
     */
    List<Permission> listPermissionByMap(Map<String, String> map);
}
