package com.ycj.lab.service;

/**
 * @author 53059
 * @date 2021/5/31 15:06
 */
public interface LoginService {
    public boolean login(String username, String password);

    public boolean updateLoginTime(String username);

}
