package com.ycj.lab.controller;

import com.ycj.lab.entity.UpdateMsg;
import com.ycj.lab.entity.User;
import com.ycj.lab.mapper.CommunityMapper;
import com.ycj.lab.mapper.UserMapper;
import com.ycj.lab.service.UserService;
import com.ycj.lab.until.ImageUntil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.ycj.lab.common.Const.PREFIX;
import static com.ycj.lab.until.StringUntil.isNull;

/**
 * @author 53059
 * @date 2021/5/29 22:18
 */

@Slf4j
@RestController
@RequestMapping("/user")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CommunityMapper communityMapper;

    // 查询所有用户
    @RequestMapping(value = "/find_all_user")
    public ArrayList<User> findAllUser(){
        return userMapper.findAll();
    }

    // 查询用户信息
    @RequestMapping(value = "/find_user")
    public User findUser(@RequestParam String username) {
        User user = userService.findUser(username);
//        log.info(user.getSex());
        log.info(user.toString());
        return user;
    }

    // 更新个人信息
    @RequestMapping(value = "/personal_info", method = RequestMethod.POST)
    public UpdateMsg updatePersonalInfo(
            @RequestParam String username, @RequestParam String sex,
            @RequestParam String birthday, @RequestParam String name
    ) {
        UpdateMsg msg = new UpdateMsg();
        if (isNull(username) || isNull(sex) || isNull(birthday) || isNull(name)) {
            log.info("字段不能为空");
            msg.setSetState(0);
        } else {
            if (userService.updatePersonalInfo(username, sex, birthday, name)) {
                log.info(username + "个人信息更新成功");
                msg.setSetState(1);
            } else {
                msg.setSetState(0);
            }
        }
        log.info(""+msg.getSetState());
        return msg;
    }

    // 更新个人信息（单个）
    @RequestMapping(value = "/personal_single", method = RequestMethod.POST)
    public Map<String, Object> updatePersonalSingle(HttpServletRequest request) {
        String username = request.getParameter("username");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String name = request.getParameter("name");
        String type = request.getParameter("type");

        Map<String, Object> map = new HashMap<>();

        if (type.equals("1")) {
            if (userService.updateSingle(username, "sex", sex)) {
                map.put("updatestate", 1);
            } else {
                map.put("updatestate", 0);
            }
        } else if (type.equals("2")) {
            if (userService.updateSingle(username, "birthday", birthday)) {
                map.put("updatestate", 1);
            } else {
                map.put("updatestate", 0);
            }
        } else if (type.equals("3")) {
            if (userService.updateSingle(username, "name", name)) {
                map.put("updatestate", 1);
            } else {
                map.put("updatestate", 0);
            }
        }
        return map;
    }

    // 更新用户头像
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public Map<String, Object> updateProfile(@RequestParam("file") MultipartFile file, @RequestParam String username) throws IOException {

        Map<String, Object> map = new HashMap<>();

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String t = format.format(System.currentTimeMillis());
        String prefix = PREFIX + "profile/";
        File f = new File(prefix);
        if (!f.isDirectory()) {
            f.mkdirs();
        }
        FileInputStream inputStream = (FileInputStream) file.getInputStream();
        String type = "jpg";
        String img = t + "." + type;
        String path = prefix + img;
        if (ImageUntil.saveImage(inputStream, path)) {
            inputStream.close();
            log.info(username + "头像更新" + img);
            userService.updateSingle(username, "profile", img);
            map.put("uploadstate", 1);
            map.put("profile", img);
            return map;
        }
        inputStream.close();
        map.put("uploadstate", 0);
        return map;
    }

    // 更新密码
    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public Map<String, Object> updatePassword(@RequestParam String uname, @RequestParam String passwd) {
        Map<String, Object> map = new HashMap<>();
        if (userService.updatePassword(uname, passwd)) {
            map.put("updatestate", 1);
        } else {
            map.put("updatestate", 0);
        }
        return map;
    }

    // 禁用账户
    @RequestMapping(value = "/freeze", method = RequestMethod.POST)
    public Map<String, Object> freezeAccount(String username){
        Map<String, Object> res = new HashMap<>();
        try {
            int flag = userMapper.freezeAccount(username);
            communityMapper.freezeAll(username);
            if (flag == 1){
                res.put("state", 1);
            }else{
                res.put("state", 0);
            }
        }catch (Exception e){
            log.error(e.toString());
            res.put("state", 0);
        }
        return res;
    }

    // 恢复禁用
    @RequestMapping(value = "/release", method = RequestMethod.POST)
    public Map<String, Object> releaseAccount(String username){
        Map<String, Object> res = new HashMap<>();
        try {
            int flag = userMapper.releaseAccount(username);
            communityMapper.releaseAll(username);
            if (flag == 1){
                res.put("state", 1);
            }else{
                res.put("state", 0);
            }
        }catch (Exception e){
            log.error(e.toString());
            res.put("state", 0);
        }
        return res;
    }
}
