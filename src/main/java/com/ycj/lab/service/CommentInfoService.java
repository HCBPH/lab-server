package com.ycj.lab.service;

import com.ycj.lab.dto.CommentInfoDto;
import com.ycj.lab.entity.CommentInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentInfoService {
    CommentInfo queryCommentById(@Param("id") long id);
    List<CommentInfoDto> queryCommentListByPageTime(@Param("sid") long sid, @Param("page") int page, @Param("size") int size);
    List<CommentInfoDto> queryCommentListByPageAscend(@Param("sid") long sid, @Param("page") int page, @Param("size") int size);
    List<CommentInfoDto> queryCommentListByScoreDescend(@Param("sid") long sid, @Param("page") int page, @Param("size") int size);
    int insertComment(CommentInfo commentInfo);
    int updateCommentById(CommentInfo commentInfo);
    int updateStateById(@Param("id") long id);
    int updatePictureByid(@Param("id") long id,@Param("picture") String picture);
}
