package com.ycj.lab.service.impl;

import com.ycj.lab.mapper.GroupMemberMapper;
import com.ycj.lab.service.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GroupMemberServiceImplement implements GroupMemberService {
    @Autowired
    GroupMemberMapper groupMemberMapper;

    @Override
    public List<Long> findGroupId(int uId) {
        return groupMemberMapper.findGroupId(uId);
    }

    @Override
    public int insertMember(Long gId, int uId) {
        return groupMemberMapper.insertMember(gId,uId);
    }

    @Override
    public int deleteMember(Long gId, int uId) {
        return groupMemberMapper.deleteMember(gId,uId);
    }

    @Override
    public List<Integer> findGroupMember(Long gId) {
        return groupMemberMapper.findGroupMember(gId);
    }

    @Override
    public Integer findGroupMemberDup(Long gId, int uId) {
        return groupMemberMapper.findGroupMemberDup(gId,uId);
    }

    @Override
    public int insertMemberDup(Long gId, int uId) {
        return groupMemberMapper.insertMemberDup(gId,uId);
    }
}

