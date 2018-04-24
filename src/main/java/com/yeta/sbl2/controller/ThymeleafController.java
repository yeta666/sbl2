package com.yeta.sbl2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转控制类
 * Created by YETA666 on 2018/4/19 0019.
 */
@Controller
public class ThymeleafController {

    @RequestMapping(value = "/home")
    public String home() {
        return "home";
    }
}
