package com.ycj.lab.service;

import com.ycj.lab.entity.GroupEntity;

import java.util.List;

public interface GroupService {
    //根据gid查找组信息
    GroupEntity findOneGroup(Long gId);
    //查找用户创建的群聊
    List<GroupEntity> findUserGroup(int creatorId);
    //创建群聊
    int createGroup(Long gId,int uId,String gName,int type);
    //解散群聊
    int deleteGroup(Long gId,int creatorId);
    //根据群名查重
    int checkDuplicate(String gName);
    //更改群组头像
    int modifyGroupProfile(Long gId,int creatorId,String profile);
}
