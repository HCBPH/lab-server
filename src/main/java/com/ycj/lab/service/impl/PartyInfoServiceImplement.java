package com.ycj.lab.service.impl;

import com.ycj.lab.entity.PartyInfo;
import com.ycj.lab.mapper.PartyInfoMapper;
import com.ycj.lab.service.PartyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyInfoServiceImplement implements PartyInfoService {

    @Autowired
    PartyInfoMapper partyInfoMapper;
    @Override
    public PartyInfo findOneGroup(int pId) {
        return partyInfoMapper.findOneParty(pId);
    }

    @Override
    public List<PartyInfo> findPartyByGroupId(Long gId) {
        return partyInfoMapper.findPartyByGroupId(gId);
    }

    @Override
    public int createPartyByGroup(PartyInfo partyInfo) {
        return partyInfoMapper.createPartyByGroup(partyInfo);
    }

//    @Override
//    public int createPartyByGroup(Long gId, int creatorId, String title, String place, int rId, String location, String date, int type) {
//        return partyInfoMapper.createPartyByGroup(gId,creatorId,title,place,rId,location,date,type);
//    }

    @Override
    public int bindPartyToGroup(int pId, Long gId) {
        return partyInfoMapper.bindPartyToGroup(pId,gId);
    }

    @Override
    public int releaseParty(int pId, int creatorId) {
        return partyInfoMapper.releaseParty(pId,creatorId);
    }

    @Override
    public int changePartyStage(int pId, int creatorId,int stage) {
        return partyInfoMapper.changePartyStage(pId,creatorId,stage);
    }

    @Override
    public int editParty(int pId, String column, String value) {
        return partyInfoMapper.editParty(pId,column,value);
    }
}
