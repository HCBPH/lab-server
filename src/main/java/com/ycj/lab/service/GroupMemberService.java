package com.ycj.lab.service;

import java.util.List;

public interface GroupMemberService {
    List<Long> findGroupId(int uId);
    int insertMember(Long gId,int uId);
    int deleteMember(Long gId,int uId);
    public List<Integer> findGroupMember(Long gId);

    //用户再次加入时set state = 1
    Integer findGroupMemberDup(Long gId,int uId);
    int insertMemberDup(Long gId,int uId);
}
