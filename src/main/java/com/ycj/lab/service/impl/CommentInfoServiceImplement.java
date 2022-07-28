package com.ycj.lab.service.impl;

import com.ycj.lab.dto.CommentInfoDto;
import com.ycj.lab.entity.CommentInfo;
import com.ycj.lab.entity.User;
import com.ycj.lab.mapper.CommentInfoMapper;
import com.ycj.lab.mapper.UserMapper;
import com.ycj.lab.service.CommentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentInfoServiceImplement implements CommentInfoService {
    @Autowired
    CommentInfoMapper commentInfoMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public CommentInfo queryCommentById(long id) {
        return commentInfoMapper.queryCommentById(id);
    }

    @Override
    public List<CommentInfoDto> queryCommentListByPageTime(long sid, int page, int size) {
        List<CommentInfo> list = commentInfoMapper.queryCommentListByPageTime(sid, page, size);
        List<CommentInfoDto> dtoList = getCommentInfoDto(list);
        return dtoList;
    }

    @Override
    public List<CommentInfoDto> queryCommentListByPageAscend(long sid, int page, int size) {
        List<CommentInfo> list = commentInfoMapper.queryCommentListByPageAscend(sid, page, size);
        List<CommentInfoDto> dtoList = getCommentInfoDto(list);
        return dtoList;
    }

    @Override
    public List<CommentInfoDto> queryCommentListByScoreDescend(long sid, int page, int size) {
        List<CommentInfo> list = commentInfoMapper.queryCommentListByScoreDescend(sid, page, size);
        List<CommentInfoDto> dtoList = getCommentInfoDto(list);
        return dtoList;
    }

    @Override
    public int insertComment(CommentInfo commentInfo) {
        return commentInfoMapper.insertComment(commentInfo);
    }

    @Override
    public int updateCommentById(CommentInfo commentInfo) {
        return commentInfoMapper.updateCommentById(commentInfo);
    }

    @Override
    public int updateStateById(long id) {
        return commentInfoMapper.updateStateById(id);
    }

    @Override
    public int updatePictureByid(long id, String picture) {
        String res = commentInfoMapper.queryExistenceById(id);
//        System.out.println(res);
        if (res.equals("empty") || res.isEmpty()) {
//            System.out.println("Service emp");
            return commentInfoMapper.updatePictureByid(id,picture);
        }else {
//            System.out.println("Service nemp");
            picture = res + "&" + picture;
            return commentInfoMapper.updatePictureByid(id,picture);
        }
    }

    public List<CommentInfoDto> getCommentInfoDto(List<CommentInfo> list){
        List<CommentInfoDto> dtoList = new ArrayList<>();
        List<CommentInfo> list1 = new ArrayList<>();
        for (CommentInfo info : list){
            list1.add(info);
        }
        for (CommentInfo info1 : list){
            List<CommentInfo> commentInfoList = commentInfoMapper.querySubCommentListByTopId(info1.getId());
            for (CommentInfo info2 : commentInfoList) {
                list1.add(info2);
            }
        }
        for (CommentInfo info : list1){
            User user = userMapper.getUserDetail(info.getUid());
            CommentInfoDto commentInfoDto = new CommentInfoDto(info.getId(),info.getStoreId(),info.getUid(),
                    info.getParentId(),info.getTopId(),info.getContent(),info.getPicture(),info.getReplyUid(),
                    info.getReplyNickname(),info.getScore(), info.getCost(),info.getCreateTime(),
                    info.getUpdateTime(),user.getProfile(),user.getName());
            dtoList.add(commentInfoDto);
        }
        return dtoList;
    }
}
