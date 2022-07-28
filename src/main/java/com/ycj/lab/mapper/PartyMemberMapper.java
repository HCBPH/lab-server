package com.ycj.lab.mapper;

import com.ycj.lab.entity.PartyMembers;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PartyMemberMapper {
    //select
    //查询活动成员
    List<Integer> findOnePartyMember(int pId);
    List<PartyMembers> findOnePartyMemberDetail(int pId);

    //查询活动成员人数
    int countOnePartyMember(int pId);

    //insert  type
    //加入活动
    int joinToParty(int uId,int pId);

    int joinToPartyCreator(int uId,int pId);
    //update
    //离开活动
    int leaveParty(int uId,int pId);
    //更改用户状态
    int updateUserType(int uId,int pId,int type);
    //用户再次加入组 set state = 1
    Integer findPartyMemberDup(int uId,int pId);
    int insertPartyMemberDup(int uId,int pId);


}
