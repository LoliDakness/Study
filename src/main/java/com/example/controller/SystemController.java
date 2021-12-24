package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: yangjie
 * @data:2021/12/22 11:07
 */
@Controller
public class SystemController {

    /**
     * 登录跳转
     * @return
     */
    @GetMapping("login")
    public String login(){
        return ("login");
    }

    /**
     * 登录
     * @return
     */
    @GetMapping("registry")
    public String registry(){
        return ("registry");
    }
}
