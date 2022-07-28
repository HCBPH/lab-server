package com.ycj.lab.mapper;

import com.ycj.lab.entity.PartyInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PartyInfoMapper {
    //select
    PartyInfo findOneParty(int pId);
    List<PartyInfo> findPartyByGroupId(Long gId);

    //insert
    //插入活动
//    int createPartyByGroup(Long gId,int creatorId,String title,String place,int rId,String location,String date,int type);
    int createPartyByGroup(@Param("partyInfo") PartyInfo partyInfo);

    //update
    //绑定活动和群组
    int bindPartyToGroup(int pId, Long gId);
    //authentication
    //解散活动
    int releaseParty(int pId,int creatorId);
    //修改活动状态
    int changePartyStage(int pId,int creatorId,int stage);
    //修改活动内容
    int editParty(int pId, String column,String value);

}
