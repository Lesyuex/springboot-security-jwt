package com.jobeth.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.jobeth.po.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Jobeth
 * @since 2020-04-10
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * listByUserId
     *
     * @param id id
     * @return List<Role>
     */
    @Select("select " +
            "r.id, " +
            "r.name, " +
            "r.remark, " +
            "r.create_time createTime, " +
            "r.update_time updateTime" +
            " from sys_role r,sys_users_roles ur" +
            " where r.id = ur.user_id" +
            " and user_id=#{id}")
    List<Role> listByUserId(Long id);


    /**
     * 根据权限查找角色集合
     * @param permissionId  permissionId
     * @return  List<Role>
     */
    @Select("select " +
            "r.id, " +
            "r.name, " +
            "r.remark," +
            "r.create_time createTime," +
            "r.update_time updateTime" +
            " from sys_roles_permissions rp,sys_role r" +
            " where rp.role_id = r.id" +
            " and rp.perm_id=#{permissionId}")
    List<Role> listByPermissionId(Long permissionId);
}
