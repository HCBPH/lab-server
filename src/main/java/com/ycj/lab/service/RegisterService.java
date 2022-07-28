package com.ycj.lab.service;

/**
 * @author 53059
 * @date 2021/5/31 15:49
 */
public interface RegisterService {
    public boolean register(String username, String password, String email, String tel);

    public int checkExist(String username, String phone, String email);
}
