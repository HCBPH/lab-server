package com.ycj.lab.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupEntity {
    private Long gId;
    private String gName;
    private int creatorId;
    private int state;
    private int type;
    private String groupProfile;
}
