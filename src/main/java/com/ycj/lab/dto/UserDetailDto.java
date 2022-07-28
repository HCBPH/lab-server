package com.ycj.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDetailDto {
    private int uid;
    private int type;
    private String username;
    private String name;
    private String profile;
}
