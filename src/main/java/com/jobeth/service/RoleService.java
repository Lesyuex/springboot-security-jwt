package com.jobeth.service;

import com.jobeth.po.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Jobeth
 * @since 2020-04-10
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据权限查找角色集合
     * @param permissionId  permissionId
     * @return  List<Role>
     */
    List<Role> listByPermissionId(Long permissionId);
}
