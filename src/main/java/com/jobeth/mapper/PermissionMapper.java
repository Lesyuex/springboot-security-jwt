package com.jobeth.mapper;

import com.jobeth.po.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jobeth.po.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Jobeth
 * @since 2020-04-24
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据条件查找权限
     *
     * @param map map
     * @return List<Permission>
     */
    List<Permission> listPermissionByMap(Map<String, String> map);
}
