package com.ycj.lab.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CommunityInfo {
    public int id;
    public String username;
    public String images;
    public String text;
    public Date create_time;
    public Date update_time;
    public String tag;
    public int state;
    public String name;
    public String profile;
    // 是否关注
    public int follow;

//	public CommunityInfo() {
//		this.id = id;
//		this.username = username;
//		this.images = images;
//		this.text = text;
//		this.create_time = create_time;
//		this.update_time = update_time;
//		this.tag = tag;
//		this.state = state;
//		this.name = name;
//		this.profile = profile;
//		this.follow = follow;
//	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return images;
    }

    public void setImage(String images) {
        this.images = images;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getFollow() {
        return follow;
    }

    public void setFollow(int follow) {
        this.follow = follow;
    }
}
