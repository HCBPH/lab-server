package com.ycj.lab.mapper;

import com.ycj.lab.entity.AccuseEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

/**
 * @author 53059
 * @date 2021/7/8 14:13
 */
@Mapper
public interface AccuseMapper {

    // 获取最新的id
    @Select("SELECT id FROM ACCUSE_INFO ORDER BY id DESC LIMIT 1")
    int getLastId();

    // 提交投诉（Android）
    @Insert("INSERT INTO ACCUSE_INFO(type,accuser,accused,cid,reason) VALUES(" +
            "#{type}, #{accuser}, #{accused}, #{cid}, #{reason})")
    int pushAccuse(String type, String accuser, String accused, int cid, String reason);

    // 获取用户投诉--分页
    @Select("SELECT * FROM ACCUSE_INFO WHERE accuser=#{username} ORDER BY id DESC LIMIT ${(page-1)*size}, ${size}")
    ArrayList<AccuseEntity> pullPageAccuse(String username, int size, int page);

    // 获取所有投诉--分页
    @Select("SELECT * FROM ACCUSE_INFO ORDER BY id DESC LIMIT ${(page-1)*size}, ${size}")
    ArrayList<AccuseEntity> pullAllAccuse(int size, int page);

    // 获取一条投诉--id
    @Select("SELECT * FROM ACCUSE_INFO WHERE id=${id}")
    ArrayList<AccuseEntity> pullOneAccuse(int id);

    // 更新处理状态
    @Update("UPDATE ACCUSE_INFO SET state=${state} WHERE id=${id}")
    int updateState(int id, int state);

    // 提交处理结果（Web）
    @Update("UPDATE ACCUSE_INFO SET feedback=#{feedback} WHERE id=${id}")
    int updateFeedback(int id, String feedback);
}
