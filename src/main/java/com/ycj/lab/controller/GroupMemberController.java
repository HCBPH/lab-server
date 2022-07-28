package com.ycj.lab.controller;

import com.ycj.lab.dto.UserDetailDto;
import com.ycj.lab.entity.GroupEntity;
import com.ycj.lab.entity.User;
import com.ycj.lab.result.Result;
import com.ycj.lab.service.GroupMemberService;
import com.ycj.lab.service.GroupService;
import com.ycj.lab.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/group")
public class GroupMemberController {
    @Autowired
    GroupService groupService;
    @Autowired
    GroupMemberService groupMemberService;
    @Autowired
    UserService userService;


    //查询用户创建和参加的组
    @RequestMapping(value = "/show_group_list", method = RequestMethod.POST)
    public Result findUserGroup(int uId){
//        logger.info(String.valueOf(uId));
        List<GroupEntity> resultList = new ArrayList<>();
        if (String.valueOf(uId) != null){
            List<Long> listId = groupMemberService.findGroupId(uId);
//            log.info(String.valueOf(listId));
//            log.info(listId.toString());
            for (int i = 0; i < listId.size(); i++) {
//                log.info(String.valueOf(listId.get(i)));
//                log.info(String.valueOf(groupService.findOneGroup(listId.get(i))));
                GroupEntity e = groupService.findOneGroup(Long.valueOf(listId.get(i)));
                if(e == null){
                    continue;
                }
                resultList.add(e);
            }
//            List<GroupEntity> creatorList = groupService.findUserGroup(uId);
//            for (GroupEntity groupEntity:creatorList){
//                resultList.add(groupEntity);
//            }
            return Result.success(resultList);
        }else {
            return Result.error("usernameId is null");
        }
    }

    //join group
    @RequestMapping(value = "/join_group", method = RequestMethod.POST)
    public Result insertMember(Long gId,int uId){
        try {
            Integer isDup = groupMemberService.findGroupMemberDup(gId,uId);
            if(isDup != null){
                groupMemberService.insertMemberDup(gId,uId);
            }else {
                groupMemberService.insertMember(gId,uId);
            }
            return Result.success("Join group successfully");
        }catch (Exception e){
            log.error(String.valueOf(e));
            return Result.success("Join group failed");
        }
    }

    //deleteMember
    @RequestMapping(value = "/leave_group", method = RequestMethod.POST)
    public Result deleteMember(Long gId,int uId){
        try {
            groupMemberService.deleteMember(gId,uId);
            return Result.success("Leave group successfully");
        }catch (Exception e){
            log.error(String.valueOf(e));
            return Result.error("Leave group failed");
        }
    }

    //查询群组成员
    @RequestMapping(value = "/query_members", method = RequestMethod.POST)
    public Result findGroupMember(@RequestParam("gId") Long gId){
        List<UserDetailDto> resultList = new ArrayList<>();
        if (gId != null){
            List<Integer> memberIdList = groupMemberService.findGroupMember(gId);
            log.info(String.valueOf(memberIdList));
            for (int id : memberIdList){
                User user = userService.getUserDetail(id);
                UserDetailDto userDetailDto = new UserDetailDto(id,0, user.getUsername(), user.getName(), user.getProfile());
                resultList.add(userDetailDto);
            }
            return Result.success(resultList);
        }else {
            return Result.error();
        }
    }
}
