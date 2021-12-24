package com.example.controller;

import com.example.config.Result;
import com.example.entity.User;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: yangjie
 * 用户管理
 * @data:2021/12/22 15:42
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService us;

    /**
     * 注册账号
     * @param user
     * @return
     */
    @PostMapping("create")
    @ResponseBody
    public Result createUser(User user){
        return us.createUser(user);
    }

    /**
     * 登录账号
     */
    @PostMapping("luser")
    @ResponseBody
    public Result loginUser(User user){
        return  us.loginUser(user);
    }

    @PostMapping("activition")
    @ResponseBody
    public Result activitionUser(String confirmCode){
        return us.activitionUser(confirmCode);
    }
}
