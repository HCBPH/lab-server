package com.ycj.lab.service.impl;

import com.ycj.lab.entity.PartyBill;
import com.ycj.lab.mapper.PartyBillMapper;
import com.ycj.lab.service.PartyBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyBillServiceImplement implements PartyBillService {
    @Autowired
    PartyBillMapper partyBillMapper;
    @Override
    public int setBillEvent(int pId, String event, double money, String remark,int creatorId) {
        return partyBillMapper.setBillEvent(pId,event,money,remark,creatorId);
    }

    @Override
    public List<PartyBill> showBill(int pId) {
        return partyBillMapper.showBill(pId);
    }

    @Override
    public double sumOnePartyBillMoney(int pId) {
        return partyBillMapper.sumOnePartyBillMoney(pId);
    }

}
