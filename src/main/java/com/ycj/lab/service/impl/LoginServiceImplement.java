package com.ycj.lab.service.impl;

import com.ycj.lab.mapper.UserMapper;
import com.ycj.lab.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 53059
 * @date 2021/5/31 15:16
 */
@Service
@Transactional
public class LoginServiceImplement implements LoginService {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean login(String username, String password) {
        String passwd = userMapper.getPassword(username);
        if(passwd != null){
            if (passwd.equals(password) && userMapper.getState(username)==1) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean updateLoginTime(String username) {
        int res;
        try {
            res = userMapper.updateLoginTime(username);
            return res != 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
