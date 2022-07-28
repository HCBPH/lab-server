package com.ycj.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyDetailInfo {
    private int pId;
    private Long gId;
    private int creatorId;
    private String title;
    private String place;
    private int rid;
    private String location;
    private int countMember;
    private double money;
    private String createTime;
    private int stage;
}
