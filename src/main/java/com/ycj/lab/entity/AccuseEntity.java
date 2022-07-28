package com.ycj.lab.entity;

/**
 * @author 53059
 * @date 2021/7/8 14:51
 */
public class AccuseEntity {
    private int id;
    private String time;
    private String type;
    private String accuser;
    private String accused;
    private String nickname;
    private int cid;
    private String reason;
    private int state;
    private String feedback;

//    public AccuseEntity(int id, String time, String type, String accuser, String accused, String nickname, int cid, String reason, int state, String feedback) {
//        this.id = id;
//        this.time = time;
//        this.type = type;
//        this.accuser = accuser;
//        this.accused = accused;
//        this.nickname = nickname;
//        this.cid = cid;
//        this.reason = reason;
//        this.state = state;
//        this.feedback = feedback;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccuser() {
        return accuser;
    }

    public void setAccuser(String accuser) {
        this.accuser = accuser;
    }

    public String getAccused() {
        return accused;
    }

    public void setAccused(String accused) {
        this.accused = accused;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
