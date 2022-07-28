package com.ycj.lab.until;

import com.ycj.lab.entity.CommunityInfo;
import com.ycj.lab.mapper.CommunityMapper;

import java.util.ArrayList;

/**
 * @author 53059
 * @date 2021/6/2 23:46
 */
public class CommunityInfoUntil {
    // 为CommunityInfo添加粉丝数量
    public static ArrayList<CommunityInfo> setFollow(ArrayList<CommunityInfo> list, CommunityMapper mapper) {
        for (CommunityInfo iter : list) {
            int count = mapper.getFollowerCount(iter.getUsername());
            iter.setFollow(count);
        }
        return list;
    }

    // 为CommunityInfo设置是否关注
    public static ArrayList<CommunityInfo> setIsFollow(String follower, ArrayList<CommunityInfo> list, CommunityMapper mapper) {
        for (CommunityInfo iter : list) {
            int tag = 0;
            String res = mapper.checkFollowed(follower, iter.getUsername());
            if (res != null){
                tag = Integer.parseInt(res);
            }
            iter.setFollow(tag);
        }
        return list;
    }

    // 为CommunityInfo设置关注
    public static ArrayList<CommunityInfo> setFollowed(ArrayList<CommunityInfo> list, CommunityMapper mapper) {
        for (CommunityInfo iter : list) {
            int count = mapper.getFollowerCount(iter.getUsername());
            iter.setFollow(1);
        }
        return list;
    }
}
