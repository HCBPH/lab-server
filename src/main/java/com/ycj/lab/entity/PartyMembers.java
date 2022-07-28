package com.ycj.lab.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PartyMembers {
    private int id;
    private int pId;
    private int uId;
    private int type;
    private int state;
}
