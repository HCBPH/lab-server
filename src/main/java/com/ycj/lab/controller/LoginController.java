package com.ycj.lab.controller;

import com.ycj.lab.service.LoginService;
import com.ycj.lab.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.ycj.lab.until.StringUntil.isNull;

/**
 * @author 53059
 * @date 2021/5/31 15:01
 */
@Slf4j
@RestController
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    LoginService loginService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestParam String username, @RequestParam String password) {
        logger.info("login");
        Map<String, Object> map = new HashMap<>();
//        if(userService.findUser(username)==null){
//            msg.setloginstate(0);
//        }

        if (isNull(username)) {
            log.info("用户名为空");
            map.put("loginstate", 0);
        } else {
            if (loginService.login(username, password)) {
                log.info(username + "登陆成功");
                map.put("loginstate", 1);
                map.put("host", userService.getHost(username));
            } else {
                log.info("密码错误");
                map.put("loginstate", 0);
                map.put("host", userService.getHost(username));
            }
        }
        return map;
    }

}
