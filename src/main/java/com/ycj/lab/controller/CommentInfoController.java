package com.ycj.lab.controller;

import com.ycj.lab.dto.CommentInfoDto;
import com.ycj.lab.entity.CommentInfo;
import com.ycj.lab.result.Result;
import com.ycj.lab.service.CommentInfoService;
import com.ycj.lab.until.ImageUntil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ycj.lab.common.Const.PREFIX;

@Slf4j
@RestController
@RequestMapping("/evaluation")
public class CommentInfoController {
    @Autowired
    CommentInfoService commentInfoService;
    @RequestMapping(value = "/find_one_evaluation", method = RequestMethod.POST)
    public Result findOneEvaluation(@RequestParam long id){
        try {
            CommentInfo commentInfo = commentInfoService.queryCommentById(id);
            return Result.success(commentInfo);
        }catch (Exception e){
            return Result.error(e);
        }
    }

    @RequestMapping(value = "/find_evaluation", method = RequestMethod.POST)
    public Result findEvaluation(@RequestParam long sid,@RequestParam int page,@RequestParam int size){
        page = (page - 1) * size;
        try {
            List<CommentInfoDto> list = commentInfoService.queryCommentListByPageTime(sid,page,size);
            return Result.success(list);
        }catch (Exception e){
            log.info(String.valueOf(e));
            return Result.error(e);
        }
    }

    @RequestMapping(value = "/find_evaluation_by_stars", method = RequestMethod.POST)
    public Result findEvaluationByStars(@RequestParam long sid,@RequestParam int page,@RequestParam int size){
        page = (page - 1) * size;
        try {
            List<CommentInfoDto> list = commentInfoService.queryCommentListByPageAscend(sid,page,size);
            return Result.success(list);
        }catch (Exception e){
            return Result.error(e);
        }
    }

    @RequestMapping(value = "/find_evaluation_by_stars_desc", method = RequestMethod.POST)
    public Result findEvaluationByStarsDesc(@RequestParam long sid,@RequestParam int page,@RequestParam int size){
        page = (page - 1) * size;
        try {
            List<CommentInfoDto> list = commentInfoService.queryCommentListByScoreDescend(sid,page,size);
            return Result.success(list);
        }catch (Exception e){
            return Result.error(e);
        }
    }

    @RequestMapping(value = "/create_evaluation", method = RequestMethod.POST)
    public Result createEvalution(@RequestBody CommentInfo commentInfo){
        try {
            int res = commentInfoService.insertComment(commentInfo);
            if (res == 1) {
                return Result.success(commentInfo.getId());
            } else {
                return Result.error("Creation failed");
            }
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @RequestMapping(value = "/edit_evaluation", method = RequestMethod.POST)
    public Result editEvaluation(@RequestBody CommentInfo commentInfo){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        commentInfo.setUpdateTime(dateFormat.format(date));
        try {
            int res = commentInfoService.updateCommentById(commentInfo);
            if (res == 1) {
                return Result.success("Operation successful");
            } else {
                return Result.error("Operation failed");
            }
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @RequestMapping(value = "/delete_evaluation", method = RequestMethod.POST)
    public Result deleteEvalution(@RequestParam long id){
        try {
            int res = commentInfoService.updateStateById(id);
            log.info(String.valueOf(res));
            if (res == 1) {
                return Result.success("Update state successful");
            } else {
                return Result.error("Update state failed");
            }
        }catch (Exception e){
            return Result.error("Update state failed");
        }
    }

    @RequestMapping(value = "/upload_evaluation_picture", method = RequestMethod.POST)
    public Result uploadEvaluationPicture(@RequestParam long id,  @RequestParam MultipartFile file) throws IOException {
        Map<String, Object> map = new HashMap<>();

        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss");
        String t = format.format(System.currentTimeMillis());
        String prefix = PREFIX + "evaluation/";
        File f = new File(prefix);
        if (!f.isDirectory()) {
            f.mkdirs();
        }
        FileInputStream inputStream = (FileInputStream) file.getInputStream();
        final String type = "jpg";
        String img = t + "." + type;
        String path = prefix + img;
        if (ImageUntil.saveImage(inputStream, path)) {
            inputStream.close();
            int res = commentInfoService.updatePictureByid(id,img);
            if (1 == res) {
                map.put("uploadState", 1);
                map.put("picture", img);
                return Result.success(map);
            } else {
                return Result.error("No permission");
            }
        }
        inputStream.close();
        map.put("uploadState", 0);
        return Result.error(map);
    }
}
