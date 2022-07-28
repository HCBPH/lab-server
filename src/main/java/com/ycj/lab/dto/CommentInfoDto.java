package com.ycj.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentInfoDto {
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
    private String profile;
    private String name;
}
