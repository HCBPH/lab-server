package com.ycj.lab.service.impl;

import com.ycj.lab.mapper.UserMapper;
import com.ycj.lab.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 53059
 * @date 2021/5/31 15:50
 */
@Service
@Transactional
public class RegisterServiceImplement implements RegisterService {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean register(String username, String password, String email, String tel) {
        try {
            userMapper.addUser(username, password, email, tel);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int checkExist(String username, String phone, String email) {
        int res = userMapper.checkExist(username, phone, email);
        return res;
    }
}
