package com.ycj.lab.controller;

import com.ycj.lab.entity.GroupEntity;
import com.ycj.lab.result.Result;
import com.ycj.lab.service.GroupMemberService;
import com.ycj.lab.service.GroupService;
import com.ycj.lab.until.ImageUntil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static com.ycj.lab.common.Const.PREFIX;

@Slf4j
@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    GroupService groupService;
    @Autowired
    GroupMemberService groupMemberService;

    @RequestMapping(value = "/find_one_group", method = RequestMethod.POST)
    public Result findOneGroup(@RequestParam("gId") Long gId){
        try {
            GroupEntity groupEntity = groupService.findOneGroup(gId);
            return Result.success(groupEntity);
        }catch (Exception e) {
//            Result.error(e);
            log.error(String.valueOf(e));
            return Result.error("error in db");
        }
    }
    //create a group
    @RequestMapping(value = "/create_group", method = RequestMethod.POST)
    public Result createGroup(int uId,String gName){
        Long gId = System.currentTimeMillis();
        int type = 0;
        try {
            int result = groupService.checkDuplicate(gName);
            log.warn(""+result);
            if (result==0){
                groupService.createGroup(gId,uId,gName,type);
                groupMemberService.insertMember(gId, uId);
                return Result.success("Group created successfully");
            }else {
                return Result.success("Group name is duplicate");
            }
        }catch (Exception e){
//            Result.error(e);
            log.error(String.valueOf(e));
            return Result.error("Group created failed");
        }
    }
    @RequestMapping(value = "/release_group", method = RequestMethod.POST)
    public Result deleteGroup(Long gId,int creatorId){
        try{
            int result = groupService.deleteGroup(gId,creatorId);
            log.info(String.valueOf(result));
            if (1==result){
                return Result.success("Group disbanded successfully");
            }else {
                return Result.error("CreatorId non-existent");
            }

        }catch (Exception e){
            log.error(String.valueOf(e));
            return Result.error("Group disbanded failed");
        }
    }

    @RequestMapping(value = "/modify_group_profile", method = RequestMethod.POST)
    public Result modifyGroupProfile(@RequestParam Long gId,
                                     @RequestParam int creatorId,
                                     @RequestParam("file") MultipartFile file) throws IOException {
        Map<String, Object> map = new HashMap<>();

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String t = format.format(System.currentTimeMillis());
        String prefix = PREFIX + "profile/";
        File f = new File(prefix);
        if (!f.isDirectory()) {
            f.mkdirs();
        }
        FileInputStream inputStream = (FileInputStream) file.getInputStream();
        final String type = "jpg";
        String img = t + "." + type;
        String path = prefix + img;
        if (ImageUntil.saveImage(inputStream, path)) {
            inputStream.close();
            int result = groupService.modifyGroupProfile(gId,creatorId,img);
            if (1==result){
                map.put("uploadstate", 1);
                map.put("profile", img);
                return Result.success(map);
            }else {
                return Result.error("No permission");
            }
        }
        inputStream.close();
        map.put("uploadstate", 0);
        return Result.error(map);
    }
}
