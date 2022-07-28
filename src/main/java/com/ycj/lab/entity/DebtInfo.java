package com.ycj.lab.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DebtInfo {
    private long id;
    private int bid;
    private int creditor;
    private int debitor;
    private float money;
    private String remark;
    private String createTime;
    private String updateTime;
}
