package com.ycj.lab.controller;

import com.ycj.lab.entity.RegisterMsg;
import com.ycj.lab.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.ycj.lab.until.StringUntil.isNull;

/**
 * @author 53059
 * @date 2021/5/31 15:35
 */
@Slf4j
@RestController
public class RegisterController {
    @Autowired
    RegisterService registerService;

    @RequestMapping(value = "/register")
    public RegisterMsg register(
            @RequestParam String username, @RequestParam String password,
            @RequestParam String email, @RequestParam String tel
    ) {
        RegisterMsg msg = new RegisterMsg();

        if (isNull(username) || isNull(password) || isNull(email) || isNull(tel)) {
            log.info("字段不能为空");
            msg.setcreatestate(0);
        } else {
            int res = registerService.checkExist(username, tel, email);
//            logger.info(String.valueOf(res));
            if (res != 0) {
                msg.setcreatestate(-1);
            } else if (registerService.register(username, password, email, tel)) {
                log.info("注册成功:" + username);
                msg.setcreatestate(1);
            } else {
                log.info("数据库添加异常");
                msg.setcreatestate(0);
            }
        }
        return msg;
    }
}
