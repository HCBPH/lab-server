package com.ycj.lab.mapper;

import com.ycj.lab.entity.PartyBill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PartyBillMapper {
    //insert
    int setBillEvent(int pId,String event,double money,String remark,int creatorId);
    //select
    List<PartyBill> showBill(int pId);
    //查询一个活动的订单总额
    double sumOnePartyBillMoney(int pId);
}
