package com.ycj.lab.service;

import com.ycj.lab.entity.PartyInfo;

import java.util.List;

public interface PartyInfoService {
    //查询一个组
    PartyInfo findOneGroup(int pId);
    //查找某群聊的活动列表
    List<PartyInfo> findPartyByGroupId(Long gId);
    //通过群聊创建活动
//    int createPartyByGroup(Long gId,int creatorId,String title,String place,int rId,String location,String date,int type);
    int createPartyByGroup(PartyInfo partyInfo);
    //将活动绑定群聊
    int bindPartyToGroup(int pId, Long gId);
    //解散活动
    int releaseParty(int pId,int creatorId);
    //更改活动阶段
    int changePartyStage(int pId,int creatorId,int stage);
    //修改活动内容
    int editParty(int pId, String column,String value);
}
