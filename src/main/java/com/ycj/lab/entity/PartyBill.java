package com.ycj.lab.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PartyBill {
    private int bId;
    private int pId;
    private String event;
    private double money;
    private int type;
    private String createTime;
    private String remark;
    private int state;
    private int creatorId;
}
