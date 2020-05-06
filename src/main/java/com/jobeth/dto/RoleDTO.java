package com.jobeth.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

/**
 * description 描述
 *
 * @author Jobeth
 * @since 2020/4/16 20:53
 */
@Data
public class RoleDTO {

    /**
     * 角色id
     */
    private Long id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    private Timestamp updateTime;

    private List<PermissionDTO> permissionDTOList;
}
