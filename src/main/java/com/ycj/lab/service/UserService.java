package com.ycj.lab.service;

import com.ycj.lab.entity.User;

/**
 * @author 53059
 * @date 2021/5/29 22:19
 */
public interface UserService {
    public User findUser(String username);

    public boolean updatePersonalInfo(String username, String sex, String birthday, String name);

    public boolean updateSingle(String username, String key, String value);

    public boolean updatePassword(String uname, String passwd);

    public int getHost(String username);

    User getUserDetail(int uId);
}
