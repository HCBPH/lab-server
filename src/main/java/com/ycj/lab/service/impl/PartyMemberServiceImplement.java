package com.ycj.lab.service.impl;

import com.ycj.lab.entity.PartyMembers;
import com.ycj.lab.mapper.PartyMemberMapper;
import com.ycj.lab.service.PartyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class PartyMemberServiceImplement implements PartyMemberService {

    @Autowired
    PartyMemberMapper partyMemberMapper;
    @Override
    public List<Integer> findOnePartyMember(int pId) {
        return partyMemberMapper.findOnePartyMember(pId);
    }

    @Override
    public int countOnePartyMember(int pId) {
        return partyMemberMapper.countOnePartyMember(pId);
    }

    @Override
    public int joinToParty(int uId, int pId) {
        return partyMemberMapper.joinToParty(uId,pId);
    }

    @Override
    public int joinToPartyCreator(int uId, int pId) {
        return partyMemberMapper.joinToPartyCreator(uId,pId);
    }

    @Override
    public int leaveParty(int uId, int pId) {
        return partyMemberMapper.leaveParty(uId,pId);
    }

    @Override
    public List<PartyMembers> findOnePartyMemberDetail(int pId) {
        return partyMemberMapper.findOnePartyMemberDetail(pId);
    }

    @Override
    public int updateUserType(int uId, int pId, int type) {
        return partyMemberMapper.updateUserType(uId, pId, type);
    }

    @Override
    public Integer findPartyMemberDup(int uId, int pId) {
        return partyMemberMapper.findPartyMemberDup(uId,pId);
    }

    @Override
    public int insertPartyMemberDup(int uId, int pId) {
        return partyMemberMapper.insertPartyMemberDup(uId,pId);
    }
}
