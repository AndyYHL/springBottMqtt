package com.tuyou.mqtt.producer.web.controller;

import com.tuyou.mqtt.producer.pojo.vo.User;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author yhl
 */
@ApiIgnore(value = "不显示")
@Api(tags = "用户登录",value = "用户登录",hidden = true)
@RestController
@RequestMapping(value = "/")
public class IndexController {
    @RequestMapping(value = "/session", produces = "text/plain;charset=UTF-8")
    public String getSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "用好未登录" + session.getId();
        } else {
            return "用好已登录：用户名："+ user.getUserName() + session.getId();
        }
    }

    @RequestMapping(value = "/get", produces = "text/plain;charset=UTF-8")
    public String get(HttpServletRequest request) {
        User user = new User();
        user.setUserName("chf");
        user.setPwd("pass");
        request.getSession().setAttribute("user", user);

        return "登录成功 " + request.getSession().getId();
    }
}
