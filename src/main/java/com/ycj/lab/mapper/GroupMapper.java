package com.ycj.lab.mapper;

import com.ycj.lab.entity.GroupEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GroupMapper {
    //根据gid查找组信息
    GroupEntity findOneGroup(Long gId);
    //创建群聊
    int createGroup(Long gId,int uId,String gName,int type);
    //解散群聊
    int deleteGroup(Long gId,int creatorId);
    //查重
    int checkDuplicate(String gName);
    //更新群组头像
    int modifyGroupProfile(Long gId,int creatorId,String profile);
    //uid(creatorId)->gname
    List<GroupEntity> findUserGroup(int creatorId);


}
