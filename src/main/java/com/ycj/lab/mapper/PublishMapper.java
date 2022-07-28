package com.ycj.lab.mapper;

import com.ycj.lab.entity.RecommendEntity;
import com.ycj.lab.entity.TopicEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

/**
 * @author 53059
 * @date 2021/7/7 14:29
 */
@Mapper
public interface PublishMapper {

    // 插入本期推荐
    int addRecommend(RecommendEntity entity);

    // 插入话题讨论
    int addTopic(TopicEntity entity);

    // 更新本期推荐的一列数据(传入cid、列名和对应数据)
    @Update("UPDATE RECOMMEND_INFO set ${column}=#{data} WHERE id=${cid}")
    int updateRecommend(int cid, String column, String data);

    // 更新话题的一列数据
    @Update("UPDATE TOPIC_INFO set ${column}=#{data} WHERE id=${cid}")
    int updateTopic(int cid, String column, String data);

    // 获取推荐的数据(实现分页，拉去最新的几条)
    @Select("SELECT * FROM RECOMMEND_INFO ORDER BY id DESC LIMIT ${(page-1)*size}, ${size}")
    ArrayList<RecommendEntity> getRecommend(int size, int page);

    // 获取话题的数据
    @Select("SELECT * FROM TOPIC_INFO ORDER BY id DESC LIMIT ${(page-1)*size}, ${size}")
    ArrayList<TopicEntity> getTopic(int size, int page);

    // 获取一条推荐
    // 获取推荐的数据(实现分页，拉去最新的几条)
    @Select("SELECT * FROM RECOMMEND_INFO WHERE id=#{id}")
    RecommendEntity getOneRecommend(int id);

    // 获取最后一条推荐id
    @Select("SELECT id FROM RECOMMEND_INFO ORDER BY id DESC LIMIT 1")
    int getRecommendId();

    // 获取最后一条话题id
    @Select("SELECT id FROM TOPIC_INFO ORDER BY id DESC LIMIT 1")
    int getTopicId();

    // 获取推荐的图片信息（根据cid和列名）
    @Select("SELECT ${img} FROM RECOMMEND_INFO WHERE id=${cid}")
    String getRecommendImg(int cid, String img);

    // 获取话题的图片信息（根据cid和列名）
    @Select("SELECT ${img} FROM TOPIC_INFO WHERE id=${cid}")
    String getTopicImg(int cid, String img);
}
