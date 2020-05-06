package com.jobeth.service;

import com.jobeth.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jobeth.vo.UserVO;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author Jobeth
 * @since 2020-04-10
 */
public interface UserService extends IService<User> {

    /**
     * 加载角色和树状菜单
     * @param userVO userVO
     */
    void generateUserAllInfo(UserVO userVO);
}
