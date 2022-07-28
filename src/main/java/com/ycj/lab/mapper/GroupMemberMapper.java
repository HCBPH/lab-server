package com.ycj.lab.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GroupMemberMapper {
    //展示该用户参加的所有群聊
    //uid->gid
    List<Long> findGroupId(int uId);
    Integer findGroupMemberDup(Long gId,int uId);
    //加入群聊
    int insertMember(Long gId,int uId);
    int insertMemberDup(Long gId,int uId);
    //删除
    int deleteMember(Long gId,int uId);

    //join group(invited)
    int insertMemberInvited(Long gId,int uId);

    //查询群组成员
    List<Integer> findGroupMember(Long gId);

}
