package com.ycj.lab.mapper;

import com.ycj.lab.entity.CommentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CommentInfoMapper {
    CommentInfo queryCommentById(@Param("id") long id);
    String queryExistenceById(@Param("id") long id);
    List<CommentInfo> queryCommentListByPageTime(@Param("sid") long sid, @Param("page") int page, @Param("size") int size);
    List<CommentInfo> queryCommentListByPageAscend(@Param("sid") long sid, @Param("page") int page, @Param("size") int size);
    List<CommentInfo> queryCommentListByScoreDescend(@Param("sid") long sid, @Param("page") int page, @Param("size") int size);
    List<CommentInfo> querySubCommentListByTopId(@Param("topId") long topId);
    int insertComment(CommentInfo commentInfo);
    int updateCommentById(CommentInfo commentInfo);
    int updateStateById(@Param("id") long id);
    int updatePictureByid(@Param("id") long id,@Param("picture") String picture);

}
