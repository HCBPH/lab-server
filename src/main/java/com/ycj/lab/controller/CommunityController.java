package com.ycj.lab.controller;

import com.ycj.lab.entity.CommunityInfo;
import com.ycj.lab.entity.User;
import com.ycj.lab.mapper.CommunityMapper;
import com.ycj.lab.service.CommunityService;
import com.ycj.lab.until.ImageUntil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.ycj.lab.common.Const.PREFIX;

/**
 * @author 53059
 * @date 2021/6/1 10:22
 */
@Slf4j
@RestController
@RequestMapping("/community")
public class CommunityController {
    
    @Autowired
    CommunityService communityService;

    @Autowired
    CommunityMapper communityMapper;

    // 上传社区消息
    @RequestMapping(value = "/text", method = RequestMethod.POST)
    public Map<String, Object> updateCommunityInfo(
            @RequestParam String username, @RequestParam String text
    ) {
        Map<String, Object> map = new HashMap<>();
        if (communityService.updateText(username, "empty", text, "1")) {
            map.put("submitstate", 1);
            map.put("cid", communityService.getCid());
            return map;
        }
        map.put("submitstate", 0);
        return map;
    }

    // 上传社区图片
    @RequestMapping(value = "/img", method = RequestMethod.POST)
    public Map<String, Object> updateCommunityImage(@RequestParam int cid, @RequestParam("file") MultipartFile file) throws IOException {

        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String t = format.format(System.currentTimeMillis());
        String prefix = PREFIX + "community/img/";
        File f = new File(prefix);
        if (!f.isDirectory()) {
            f.mkdirs();
        }
        FileInputStream inputStream = (FileInputStream) file.getInputStream();
        String type = "jpg";
        String img = t + "." + type;
        String path = prefix + img;
        String imagesDb = communityService.getImage(cid);

        if (ImageUntil.saveImage(inputStream, path)) {
            inputStream.close();
            if (imagesDb.equals("empty")) {
                communityService.updateImage(cid, img);
            } else {
                communityService.updateImage(cid, imagesDb + "&" + img);
            }
            log.info("社区图片更新");
            map.put("uploadstate", 1);
            map.put("img", img);
            return map;
        }
        inputStream.close();
        map.put("uploadstate", 0);
        return map;
    }

    // 根据tag获取所有、关注、个人动态
    @RequestMapping(value = "/get_community", method = RequestMethod.POST)
    public ArrayList<CommunityInfo> getAllCommunity(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        int page = Integer.parseInt(request.getParameter("page"));
        int size = Integer.parseInt(request.getParameter("size"));
        String tag = request.getParameter("tag");
        // username是当前用户的username
        String username = request.getParameter("username");

        switch (tag) {
            case "0":
                return communityService.getAllCommunity(username, page, size);
            case "-1":
                return communityService.getFollowedCommunity(username, page, size);
            case "1":
                return communityService.getPersonalCommunity(username, page, size);
            default:
                return null;
        }
    }

    // 获取一条动态（根据CID）
    @RequestMapping(value = "/get_one_community", method = RequestMethod.POST)
    public CommunityInfo getOneCommunity(@RequestParam int cid) {
        CommunityInfo res = communityService.getOneCommunity(cid);
        res.setCreate_time(new Date(res.getCreate_time().getTime()-8*3600*1000));
        res.setUpdate_time(new Date(res.getUpdate_time().getTime()-8*3600*1000));
        return res;
    }

    // 获取所有动态
    @RequestMapping(value = "/get_all_community", method = RequestMethod.POST)
    public ArrayList<CommunityInfo> getAllCommunity(@RequestParam String username, @RequestParam int page, @RequestParam int size) {
        ArrayList<CommunityInfo> allCommunity = communityService.getAllCommunity(username, page, size);
        allCommunity.forEach(x->{x.setCreate_time(new Date(x.getCreate_time().getTime()-8*3600*1000));
            x.setUpdate_time(new Date(x.getUpdate_time().getTime()-8*3600*1000));});
        return allCommunity;
    }

    // 获取个人所有动态
    @RequestMapping(value = "/get_personal_community", method = RequestMethod.POST)
    public ArrayList<CommunityInfo> getPersonalCommunity(@RequestParam String username, @RequestParam int page, @RequestParam int size) {
        ArrayList<CommunityInfo> personalCommunity = communityService.getPersonalCommunity(username, page, size);
        personalCommunity.forEach(x->{x.setCreate_time(new Date(x.getCreate_time().getTime()-8*3600*1000));
            x.setUpdate_time(new Date(x.getUpdate_time().getTime()-8*3600*1000));});
        return personalCommunity;
    }

    // 获取关注的所有动态
    @RequestMapping(value = "/get_followed_community", method = RequestMethod.POST)
    public ArrayList<CommunityInfo> getFollowedCommunity(@RequestParam String username, @RequestParam int page, @RequestParam int size) {
//        log.info(page + "|" + size);
        ArrayList<CommunityInfo> followedCommunity = communityService.getFollowedCommunity(username, page, size);
        followedCommunity.forEach(x->{x.setCreate_time(new Date(x.getCreate_time().getTime()-8*3600*1000));
            x.setUpdate_time(new Date(x.getUpdate_time().getTime()-8*3600*1000));});
        return followedCommunity;
    }

    // 获取一个用户的动态数量
    @RequestMapping(value = "/get_community_count", method = RequestMethod.POST)
    public Map<String, Object> getUserCommunityCount(@RequestParam String follower, @RequestParam String followed) {
        Map<String, Object> map = new HashMap<>();
        int count = communityService.getUserCommunityCount(followed);
        boolean follow = communityService.checkFollowed(follower, followed);
        if (count >= 0) {
            map.put("info", count);
        } else {
            map.put("info", -1);
        }
        if (follow) {
            map.put("follow", 1);
        } else {
            map.put("follow", 0);
        }
        return map;
    }

    // 获取一个用户的粉丝列表（分页）
    @RequestMapping(value = "/get_follower_list", method = RequestMethod.POST)
    public ArrayList<User> getFollowerList(@RequestParam String username, @RequestParam int page) {
        return communityService.getFollowerListPage(username, page);
    }


    // 获取一个用户的关注列表（分页）
    @RequestMapping(value = "/get_followed_list", method = RequestMethod.POST)
    public ArrayList<User> getFollowedList(@RequestParam String username, @RequestParam int page) {
        return communityService.getFollowedListPage(username, page);
    }

    // 获取关注数量和粉丝数量
    @RequestMapping(value = "/get_both_count", method = RequestMethod.POST)
    public Map<String, Object> getBothCount(@RequestParam String username) {
        Map<String, Object> map = new HashMap<>();
        int follower_count = communityService.getFollowerCount(username);
        int followed_count = communityService.getFollowedCount(username);
        map.put("followed", followed_count);
        map.put("follower", follower_count);
        return map;
    }

    // 关注用户
    @RequestMapping(value = "/follow", method = RequestMethod.POST)
    public Map<String, Object> follow(@RequestParam String flag, @RequestParam String follower, @RequestParam String followed) {
        Map<String, Object> map = new HashMap<>();
        boolean res;
        if (flag.equals("follow")) {
            res = communityService.follow(follower, followed);
            if (res) {
                map.put("updatestate", 1);
            } else {
                map.put("updatestate", 0);
            }
            return map;
        } else if (flag.equals("cancel")) {
            res = communityService.clearFollowed(follower, followed);
            if (res) {
                map.put("updatestate", 1);
            } else {
                map.put("updatestate", 0);
            }
            return map;
        } else {
            log.info("follow处理类型错误");
            map.put("updatestate", 0);
            return map;
        }
    }

    // 获取用户个人页面顶部（动态数量和是否关注）
    @RequestMapping(value = "/get_personal_head", method = RequestMethod.POST)
    public Map<String, Object> getPersonalHead(@RequestParam String follower, @RequestParam String followed) {
        Map<String, Object> map = new HashMap<>();
        int count = communityService.getUserCommunityCount(followed);
        map.put("info", count);
        if (communityService.checkFollowed(follower, followed)) {
            map.put("follow", 1);
        } else {
            map.put("follow", 0);
        }
        return map;
    }

    // 禁用一条动态
    @RequestMapping(value = "/freeze", method = RequestMethod.POST)
    public Map<String, Object> freezeOne(int id){
        Map<String, Object> res = new HashMap<>();
        try {
            int flag = communityMapper.freezeOne(id);
            if(flag == 1){
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

    // 恢复一条动态
    @RequestMapping(value = "/release", method = RequestMethod.POST)
    public Map<String, Object> releaseOne(int id){
        Map<String, Object> res = new HashMap<>();
        try {
            int flag = communityMapper.releaseOne(id);
            if(flag == 1){
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
