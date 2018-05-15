package com.yeta.sbl2.controller;

import com.yeta.sbl2.utils.MyResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author YETA
 * @date 2018/05/10/21:46
 */
@RestController
@RequestMapping(value = "/login_out")
public class LoginController {

    @PostMapping(value = "login")
    public MyResponse login(@RequestParam(value = "username") String username,
                            @RequestParam(value = "password") String password,
                            HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        MyResponse myResponse = new MyResponse();
        if ("yeta".equals(username) && "666".equals(password)) {
            //写cookie
            Cookie cookie = new Cookie("login", "true");
            cookie.setPath("/");
            response.addCookie(cookie);
            //设置返回结果
            myResponse.setData(request.getServletPath().replace("/", ""));
        } else {
            myResponse.setMessage("用户名或密码错误，请重新尝试！");
        }
        return myResponse;
    }
}
