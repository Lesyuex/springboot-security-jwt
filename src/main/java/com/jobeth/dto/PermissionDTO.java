package com.jobeth.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * description 描述
 *
 * @author Jobeth
 * @since 2020/4/16 20:53
 */
@Data
public class PermissionDTO {
    /**
     * 权限id
     */
    private Long id;
    /**
     * 权限名
     */
    private String name;
    /**
     * 资源
     */
    private String path;
    private String icon;
    /**
     * 类型 menu,button
     */
    private String type;
    /**
     * 父id
     */
    private Long parentId;
    private String component;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 可访问该资源的角色
     */
    List<RoleDTO> roleDTOList;
}
