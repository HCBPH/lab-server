package com.ycj.lab.service.impl;

import com.ycj.lab.entity.GroupEntity;
import com.ycj.lab.mapper.GroupMapper;
import com.ycj.lab.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImplement implements GroupService {

    @Autowired
    GroupMapper groupMapper;
    @Override
    public GroupEntity findOneGroup(Long gId) {
        return groupMapper.findOneGroup(gId);
    }

    @Override
    public List<GroupEntity> findUserGroup(int creatorId) {
        return groupMapper.findUserGroup(creatorId);
    }

    @Override
    public int createGroup(Long gId, int uId, String gName, int type) {
        return groupMapper.createGroup(gId,uId,gName,type);
    }

    @Override
    public int deleteGroup(Long gId, int creatorId) {
        return groupMapper.deleteGroup(gId,creatorId);
    }

    @Override
    public int checkDuplicate(String gName) {
        return groupMapper.checkDuplicate(gName);
    }

    @Override
    public int modifyGroupProfile(Long gId, int creatorId, String profile) {
        return groupMapper.modifyGroupProfile(gId,creatorId,profile);
    }
}
