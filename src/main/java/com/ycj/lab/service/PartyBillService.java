package com.ycj.lab.service;

import com.ycj.lab.entity.PartyBill;

import java.util.List;

public interface PartyBillService {
    //设置账单
    int setBillEvent(int pId,String event,double money,String remark,int creatorId);
    //查询一活动账单
    List<PartyBill> showBill(int pId);
    //查询一个活动的订单总额
    double sumOnePartyBillMoney(int pId);

}
