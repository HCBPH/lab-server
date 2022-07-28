package com.ycj.lab.mapper;

import com.ycj.lab.entity.CommunityInfo;
import com.ycj.lab.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author 53059
 * @date 2021/6/1 10:12
 */
@Mapper
public interface CommunityMapper {
    /**
     * Community
     */
    // 更新一条动态的文字
    @Insert("INSERT INTO COMMUNITY_INFO(username, images, text, update_time, tag)" +
            "VALUES(#{username}, #{images}, #{text}, CURRENT_TIMESTAMP, #{tag})")
    public void addInfo(String username, String images, String text, String tag);

    // 获取最新动态的id
    @Select("SELECT MAX(id) FROM COMMUNITY_INFO")
    public int getCid();

    // 更新一条动态的图片
    @Update("UPDATE COMMUNITY_INFO SET images=#{images} WHERE id=#{id}")
    public void updateImage(int id, String images);

    // 获取一条动态的图片信息
    @Select("SELECT images FROM COMMUNITY_INFO WHERE id=#{id}")
    public String getImage(int id);

    // 获取一条动态
    @Select("SELECT * FROM COMMUNITY_INFO JOIN USER ON COMMUNITY_INFO.username=USER.username WHERE COMMUNITY_INFO.id=#{cid} AND USER.state=1")
    @Result(property = "create_time", column = "create_time", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
    @Result(property = "update_time", column = "update_time", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
    public CommunityInfo getOneCommunity(int cid);

    // 获取所有人的动态（分页）
    @Select("SELECT * FROM COMMUNITY_INFO JOIN USER ON COMMUNITY_INFO.username=USER.username " +
            "WHERE USER.state=1 " +
            "order by COMMUNITY_INFO.id desc limit ${size*(page-1)},${size}")
    @Result(property = "create_time", column = "create_time", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
    @Result(property = "update_time", column = "update_time", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
    public ArrayList<CommunityInfo> getAllCommunity(int page, int size);

    // 获取一个用户的所有动态(分页)
    @Select("SELECT * FROM COMMUNITY_INFO JOIN USER ON COMMUNITY_INFO.username=USER.username " +
            "WHERE COMMUNITY_INFO.username=#{username} " +
            "AND USER.state=1 " +
            "order by COMMUNITY_INFO.id desc limit ${size*(page-1)},${size}")
    @Result(property = "create_time", column = "create_time", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
    @Result(property = "update_time", column = "update_time", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
    public ArrayList<CommunityInfo> getPersonalCommunity(String username, int page, int size);

    // 获取用户关注的人的动态（分页）
    @Select("SELECT * FROM COMMUNITY_INFO JOIN USER ON COMMUNITY_INFO.username=USER.username " +
            "WHERE COMMUNITY_INFO.username in " +
            "(SELECT followed FROM FOLLOW_INFO WHERE follower=#{username} and state=1) " +
            "AND USER.state=1 " +
            "order by COMMUNITY_INFO.id desc limit ${size*(page-1)},${size}")
    @Result(property = "create_time", column = "create_time", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
    @Result(property = "update_time", column = "update_time", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
    public ArrayList<CommunityInfo> getFollowedCommunity(String username, int page, int size);

    // 获取用户的动态数量
    @Select("SELECT COUNT(id) FROM COMMUNITY_INFO WHERE username=#{username} " +
            "AND state=1")
    public int getUserCommunityCount(String username);

    // 禁用一条动态
    @Update("UPDATE COMMUNITY_INFO SET state=0 WHERE id=${id}")
    public int freezeOne(int id);

    // 禁用用户所有动态
    @Update("UPDATE COMMUNITY_INFO SET state=0 WHERE username=#{username}")
    public int freezeAll(String username);

    // 恢复一条动态
    @Update("UPDATE COMMUNITY_INFO SET state=1 WHERE id=${id}")
    public int releaseOne(int id);

    // 恢复用户动态
    @Update("UPDATE COMMUNITY_INFO SET state=1 WHERE username=#{username}")
    public int releaseAll(String username);


    /**
     * 关注
     * */
    // 获取用户的粉丝数量
    @Select("SELECT COUNT(follower) FROM FOLLOW_INFO WHERE followed=#{username}" +
            " AND state=1")
    public int getFollowerCount(String username);

    // 获取用户的关注数量
    @Select("SELECT COUNT(followed) FROM FOLLOW_INFO WHERE follower=#{username} " +
            "AND state=1")
    public int getFollowedCount(String username);

    // 获取用户粉丝列表(分页)
    @Select("SELECT username,name,profile FROM USER where username in (select follower from FOLLOW_INFO where followed=#{username} and state=1) limit ${(page-1)*10},10")
    public ArrayList<User> getFollowerListPage(String username, int page);

    // 获取用户关注列表
    @Select("SELECT username,name,profile FROM USER where username in (select followed from FOLLOW_INFO where follower=#{username} and state=1)")
    public ArrayList<User> getFollowedList(String username);

    // 获取用户关注列表(分页)
    @Select("SELECT username,name,profile FROM USER where username in (select followed from FOLLOW_INFO where follower=#{username} and state=1) " +
            "limit ${(page-1)*10},10")
    public ArrayList<User> getFollowedListPage(String username, int page);

    // 关注用户
    @Insert("INSERT INTO FOLLOW_INFO(followed,follower) VALUES(#{followed},#{follower})")
    public void follow(String follower, String followed);

    // 更新关注信息
    @Update("UPDATE FOLLOW_INFO SET state=#{state} WHERE follower=#{follower} and followed=#{followed}")
    public void updateFollowed(String follower, String followed, int state);

    // 查询是否关注，返回state，null是没关注
    @Select("SELECT state FROM FOLLOW_INFO WHERE follower=#{follower} and followed=#{followed}")
    public String checkFollowed(String follower, String followed);
}
