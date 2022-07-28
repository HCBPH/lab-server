package com.ycj.lab.controller;

import com.ycj.lab.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 53059
 * @date 2021/7/13 13:32
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestMapper mapper;
    @RequestMapping("/check")
    public int checkUser(@RequestParam String uid){
        return mapper.checkUser(uid);
    }
}
