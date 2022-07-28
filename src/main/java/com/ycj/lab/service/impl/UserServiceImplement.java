package com.ycj.lab.service.impl;

import com.ycj.lab.entity.User;
import com.ycj.lab.mapper.UserMapper;
import com.ycj.lab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 53059
 * @date 2021/5/29 22:20
 */
@Service
@Transactional
public class UserServiceImplement implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findUser(String username) {
        return userMapper.findUser(username);
    }

    @Override
    public boolean updatePersonalInfo(String username, String sex, String birthday, String name) {

        int res;
        try {
            res = userMapper.updatePersonalInfo(username, sex, birthday, name);
            if (res == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateSingle(String username, String key, String value) {
        int res;
        try {
            res = userMapper.updateSingle(username, key, value);
            if (res == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updatePassword(String uname, String passwd) {
        int res;

        try {
            res = userMapper.updatePassword(uname, passwd);
            return res != 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getHost(String username) {
        try {
            int res = userMapper.getHost(username);
            return res;
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public User getUserDetail(int uId) {
        return userMapper.getUserDetail(uId);
    }


}
