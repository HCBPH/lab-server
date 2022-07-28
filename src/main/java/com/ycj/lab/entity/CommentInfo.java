package com.ycj.lab.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentInfo {
    private long id;
    private long storeId;
    private int uid;
    private long parentId;
    private long topId;
    private String content;
    private String picture;
    private long replyUid;
    private String replyNickname;
    private float score;
    private float cost;
    private String createTime;
    private String updateTime;
}
