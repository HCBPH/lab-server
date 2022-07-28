package com.ycj.lab.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreInfo {
    private long id;
    private String name;
    private String cover;
    private String background;
    private String introduce;
    private String detail;
    private String picture;
    private float score;
    private float consume;
    private String location;
    private String address;
    private String businessHours;
    private String tel;
    private String style;
    private String type;
    private String createTime;
    private String updateTime;
    private int state;

}
