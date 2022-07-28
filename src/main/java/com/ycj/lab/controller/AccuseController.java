package com.ycj.lab.controller;

import com.ycj.lab.entity.AccuseEntity;
import com.ycj.lab.mapper.AccuseMapper;
import com.ycj.lab.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 53059
 * @date 2021/7/8 14:14
 */
@Slf4j
@RestController
@RequestMapping("/accuse")
public class AccuseController {

    @Autowired
    AccuseMapper mapper;

    @Autowired
    UserMapper userMapper;

    // 提交投诉（Android）
    @RequestMapping(value = "/push", method = RequestMethod.POST)
    Map<String, Object> pushAccuse(@RequestParam String type, @RequestParam String accuser,
                                   @RequestParam String accused, @RequestParam int cid, @RequestParam String reason){
        Map<String, Object> res = new HashMap<>();
        try{
            int flag = mapper.pushAccuse(type, accuser, accused, cid, reason);
            int id = mapper.getLastId();
            if (flag == 1){
                res.put("state", 1);
                res.put("id", id);
            }else{
                res.put("state", 0);
            }
        }catch (Exception e){
            log.error(e.toString());
            res.put("state", 0);
        }
        return res;
    }

    // 获取用户投诉--分页
    @RequestMapping(value = "/pull_page", method = RequestMethod.POST)
    ArrayList<AccuseEntity> pullPageAccuse(@RequestParam String username, @RequestParam int size, @RequestParam int page){
        ArrayList<AccuseEntity> res = new ArrayList<>();
        try{
            res = mapper.pullPageAccuse(username, size, page);
            String nickname;
            for (AccuseEntity entity : res) {
                String accused = entity.getAccused();
                nickname = userMapper.getNickname(accused);
                if (nickname == null || nickname.length()==0){
                    entity.setNickname(accused);
                }else{
                    entity.setNickname(nickname);
                }
            }
        }catch (Exception e){
            log.error(e.toString());
        }

        return res;
    }

    // 获取所有投诉--分页
    @RequestMapping(value = "/pull_all", method = RequestMethod.POST)
    ArrayList<AccuseEntity> pullAllAccuse(@RequestParam int size, @RequestParam int page){
        ArrayList<AccuseEntity> res = new ArrayList<>();
        res = mapper.pullAllAccuse(size, page);
        String nickname;
        for (AccuseEntity entity : res) {
            String accused = entity.getAccused();
            nickname = userMapper.getNickname(accused);
            if (nickname == null || nickname.length()==0){
                entity.setNickname(accused);
            }else{
                entity.setNickname(nickname);
            }
        }
        return res;
    }

    // 获取一条投诉--id
    @RequestMapping(value = "/pull_one", method = RequestMethod.POST)
    ArrayList<AccuseEntity> pullOneAccuse(int id){
        ArrayList<AccuseEntity> res = new ArrayList<>();
        try{
            res = mapper.pullOneAccuse(id);
            String nickname;
            for (AccuseEntity entity : res) {
                nickname = userMapper.getNickname(entity.getAccused());
                if (nickname == null || nickname.length()==0){
                    entity.setNickname(entity.getAccused());
                }
                entity.setNickname(nickname);
            }
        }catch (Exception e){
            log.error(e.toString());
        }
        return res;
    }

    // 提交处理结果（Web）
    @RequestMapping("/feedback")
    Map<String, Object> updateFeedback(int id, int state, String feedback){
        Map<String, Object> res = new HashMap<>();
        try {
            int f1 = mapper.updateState(id, state);
            int f2 = mapper.updateFeedback(id, feedback);
            if(f1 == 1 && f2 ==1){
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
