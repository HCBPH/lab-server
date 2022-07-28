package com.ycj.lab.service.impl;

import com.ycj.lab.entity.CommunityInfo;
import com.ycj.lab.entity.User;
import com.ycj.lab.mapper.CommunityMapper;
import com.ycj.lab.service.CommunityService;
import com.ycj.lab.until.CommunityInfoUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @author 53059
 * @date 2021/6/1 10:13
 */
@Service
@Transactional
public class CommunityServiceImplement implements CommunityService {

    @Autowired
    CommunityMapper communityMapper;

    @Override
    public boolean updateText(String username, String images, String text, String tag) {
        try {
            communityMapper.addInfo(username, images, text, tag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getCid() {
        return communityMapper.getCid();
    }

    @Override
    public boolean updateImage(int cid, String images) {
        try {
            communityMapper.updateImage(cid, images);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    @Override
    public String getImage(int id) {
        return communityMapper.getImage(id);
    }

    @Override
    public CommunityInfo getOneCommunity(int cid) {
        return communityMapper.getOneCommunity(cid);
    }

    @Override
    public ArrayList<CommunityInfo> getAllCommunity(String follower, int page, int size) {
        ArrayList<CommunityInfo> list = communityMapper.getAllCommunity(page, size);
        return CommunityInfoUntil.setIsFollow(follower, list, communityMapper);
    }

    @Override
    public ArrayList<CommunityInfo> getPersonalCommunity(String username, int page, int size) {
        ArrayList<CommunityInfo> list = communityMapper.getPersonalCommunity(username, page, size);
        return list;
//        return CommunityInfoUntil.setFollow(list, communityMapper);
    }

    @Override
    public ArrayList<CommunityInfo> getFollowedCommunity(String username, int page, int size) {
        ArrayList<CommunityInfo> list = communityMapper.getFollowedCommunity(username, page, size);
        return CommunityInfoUntil.setFollowed(list, communityMapper);
    }

    @Override
    public int getUserCommunityCount(String username) {
        return communityMapper.getUserCommunityCount(username);
    }

    @Override
    public ArrayList<User> getFollowerListPage(String username, int page) {
        return communityMapper.getFollowerListPage(username, page);
    }

    @Override
    public ArrayList<User> getFollowedListPage(String username, int page) {
        return communityMapper.getFollowedListPage(username, page);
    }

    @Override
    public int getFollowerCount(String username) {
        return communityMapper.getFollowerCount(username);
    }

    @Override
    public int getFollowedCount(String username) {
        return communityMapper.getFollowedCount(username);
    }

    @Override
    public boolean follow(String follower, String followed) {
        try {
            String state = communityMapper.checkFollowed(follower, followed);
            if (state == null) {
                communityMapper.follow(follower, followed);
                return true;
            } else if (state.equals("0")) {
                communityMapper.updateFollowed(follower, followed, 1);
                return true;
            } else if (state.equals("1")) {
                System.out.println("异常：重复关注");
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean clearFollowed(String follower, String followed) {
        try {
            communityMapper.updateFollowed(follower, followed, 0);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkFollowed(String follower, String followed) {
        String check = communityMapper.checkFollowed(follower, followed);
        if (check == null) {
            System.out.println("没有关注");
            return false;
        } else return check.equals("1");
    }


}
