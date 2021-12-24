package com.example.services;

import com.example.config.Result;
import com.example.entity.User;

/**
 * @author: yangjie
 * @data:2021/12/22 15:14
 */
public interface UserService {
    /**
    * 注册账号
    */
    public Result createUser(User user);

    /**
     * 登录账号
     */
    Result loginUser(User user);

    /**
     * 激活账号
     */
    Result activitionUser(String confirmCode);
}
