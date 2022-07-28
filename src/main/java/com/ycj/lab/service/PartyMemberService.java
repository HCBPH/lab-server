package com.ycj.lab.service;

import com.ycj.lab.entity.PartyMembers;

import java.util.List;

public interface PartyMemberService {
    //查询活动成员
    List<Integer> findOnePartyMember(int pId);
    //查询活动成员人数
    int countOnePartyMember(int pId);
    //加入活动
    int joinToParty(int uId,int pId);
    //活动创建者加入members
    int joinToPartyCreator(int uId,int pId);
    //离开活动
    int leaveParty(int uId,int pId);
    //根据pid 查找用户id和状态
    List<PartyMembers> findOnePartyMemberDetail(int pId);
    //更新用户状态
    int updateUserType(int uId,int pId,int type);
    //用户再次加入组 set state = 1
    Integer findPartyMemberDup(int uId,int pId);
    int insertPartyMemberDup(int uId,int pId);
}
