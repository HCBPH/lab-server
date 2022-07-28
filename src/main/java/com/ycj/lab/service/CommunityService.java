package com.ycj.lab.service;

import com.ycj.lab.entity.CommunityInfo;
import com.ycj.lab.entity.User;

import java.util.ArrayList;

/**
 * @author 53059
 * @date 2021/6/1 10:14
 */
public interface CommunityService {
    public boolean updateText(String username, String images, String text, String tag);

    public int getCid();

    public boolean updateImage(int cid, String images);

    public String getImage(int id);

    public CommunityInfo getOneCommunity(int cid);

    public ArrayList<CommunityInfo> getAllCommunity(String follower, int page, int size);

    public ArrayList<CommunityInfo> getPersonalCommunity(String username, int page, int size);

    public ArrayList<CommunityInfo> getFollowedCommunity(String username, int page, int size);

    public int getUserCommunityCount(String username);

    public ArrayList<User> getFollowerListPage(String username, int page);

    public ArrayList<User> getFollowedListPage(String username, int page);

    public int getFollowerCount(String username);

    public int getFollowedCount(String username);

    public boolean follow(String follower, String followed);

    public boolean clearFollowed(String follower, String followed);

    public boolean checkFollowed(String follower, String followed);

}
