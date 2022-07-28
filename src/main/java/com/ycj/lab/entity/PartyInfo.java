package com.ycj.lab.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PartyInfo {
    private int pId;
    private Long gId;
    private int creatorId;
    private String title;
    private String place;
    private int rId;
    private String location;
    private String createTime;
    private int state;
    private int stage;
}
