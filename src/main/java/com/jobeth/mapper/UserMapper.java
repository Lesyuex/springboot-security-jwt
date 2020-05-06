package com.jobeth.mapper;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.jobeth.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author Jobeth
 * @since 2020-04-10
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 按用户名查询
     *
     * @param username username
     * @return User
     */
    @Select("select id, username, password, enabled from sys_user where username=#{username}")
    User selectByUsername(String username);

    //List<User> selectList(@Param(Constants.WRAPPER) Wrapper<User> queryWrapper);

}
