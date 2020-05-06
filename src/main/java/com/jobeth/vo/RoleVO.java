package com.jobeth.vo;

import lombok.ToString;

/**
 * Desc
 *
 * @author Jobeth
 * @since 2020/5/3 23:00
 */
@ToString
public class RoleVO {
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
}
