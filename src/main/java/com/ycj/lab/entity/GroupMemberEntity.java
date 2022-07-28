package com.ycj.lab.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupMemberEntity {
    private int id;
    private long gId;
    private int uId;
    private int state;
}
