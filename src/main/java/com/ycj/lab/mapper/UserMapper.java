package com.ycj.lab.mapper;

import com.ycj.lab.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author 53059
 * @date 2021/5/29 22:19
 */
@Mapper
public interface UserMapper {

    /**
     * User
     */
    // 返回所有用户信息
    @Select("SELECT * FROM USER where state=1")
    @Result(property = "create_time", column = "create_time", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
    @Result(property = "last_login_time", column = "last_login_time", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
    public ArrayList<User> findAll();

    // 查看用户信息
    @Select("SELECT * FROM USER WHERE username=#{username} and state=1")
    @Result(property = "create_time", column = "create_time", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
    @Result(property = "last_login_time", column = "last_login_time", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
    public User findUser(String username);

    // 添加新用户
    @Insert("INSERT INTO USER(username, password, name, email, tel, last_login_time) VALUES" +
            "(#{username},#{password}, #{username}, #{email}, #{tel}, CURRENT_TIMESTAMP)")
    public void addUser(String username, String password, String email, String tel);

    // 删除用户
    @Delete("DELETE FROM USER WHERE uname=#{uname}")
    public void del(String uname);

    // 更新用户信息
    @Update("UPDATE USER SET sex=#{sex},birthday=#{birthday}, name=#{name} WHERE username=#{username}")
    public int updatePersonalInfo(String username, String sex, String birthday, String name);

    // 更新用户一个属性
    @Update("UPDATE USER SET ${key}=#{value} WHERE username=#{username}")
    public int updateSingle(String username, String key, String value);

    // 修改密码
    @Update("UPDATE USER SET password=#{passwd} WHERE username=#{uname}")
    public int updatePassword(String uname, String passwd);

    // 校对密码
    @Select("SELECT password FROM USER WHERE username=#{username}")
    public String getPassword(String username);

    // 用户唯一
    @Select("SELECT COUNT(id) FROM USER WHERE username=#{username} or tel=#{tel} or email=#{email}")
    public int checkExist(String username, String tel, String email);

    // 更新登录时间
    @Update("UPDATE USER SET last_login_time=CURRENT_TIMESTAMP WHERE username=#{username}")
    public int updateLoginTime(String username);

    // 获取host
    @Select("SELECT host FROM USER WHERE username=#{username}")
    public int getHost(String username);

    // 获取用户昵称
    @Select("SELECT name FROM USER WHERE username=#{username}")
    public String getNickname(String username);

    // 禁用账户
    @Update("UPDATE USER SET state=0 WHERE username=#{username}")
    public int freezeAccount(String username);

    // 恢复禁用
    @Update("UPDATE USER SET state=1 WHERE username=#{username}")
    public int releaseAccount(String username);

    // 查看用户状态
    @Select("SELECT state FROM USER WHERE username=#{username}")
    public Integer getState(String username);

    //根据uid查询用户
    @Select("SELECT username,name,profile FROM USER WHERE id=#{uId}")
    User getUserDetail(int uId);

}
