package com.ycj.lab.controller;

import com.ycj.lab.entity.RecommendEntity;
import com.ycj.lab.entity.TopicEntity;
import com.ycj.lab.mapper.PublishMapper;
import com.ycj.lab.until.ImageUntil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.ycj.lab.common.Const.PREFIX;

/**
 * @author 53059
 * @date 2021/7/7 9:02
 */

@Slf4j
@RestController
@RequestMapping("/publish")
public class PublishController {

    @Autowired
    PublishMapper mapper;


    /**
    * Web端接口
    * */
    // 发布本期推荐
    @RequestMapping(value = "/push_recommend", method = RequestMethod.POST)
    public Map<String, Object> pushRecommends(@RequestParam String publisher, @RequestParam String title,
                                                 @RequestParam String brief, @RequestParam String content,
                                                 @RequestParam String tag){
        Map<String, Object> res = new HashMap<>();
        RecommendEntity entity = new RecommendEntity(publisher, title, brief, content, tag);
        try {
//            mapper.addRecommend(publisher, title, brief, content, tag);
            mapper.addRecommend(entity);
            int id = mapper.getRecommendId();
            res.put("state", 1);
            res.put("id", id);
        }catch (Exception e){
            log.error(e.toString());
            res.put("state", 0);
        }
        return res;
    }

    // 发布话题讨论
    @RequestMapping(value = "/push_topic", method = RequestMethod.POST)
    public Map<String, Object> pushTopic(@RequestParam String publisher, @RequestParam String title,
                                            @RequestParam String brief, @RequestParam String content,
                                            @RequestParam String tag){
        Map<String, Object> res = new HashMap<>();
        TopicEntity entity = new TopicEntity(publisher, title, brief, content, tag);
        try {
//            mapper.addRecommend(publisher, title, brief, content, tag);
            mapper.addTopic(entity);
            int cid = mapper.getTopicId();
            res.put("state", 1);
            res.put("cid", cid);
        }catch (Exception e){
            log.error(e.toString());
            res.put("state", 0);
        }
        return res;
    }

//    // 更新推荐的图片
//    @RequestMapping(value = "/update_recommend", method = RequestMethod.POST)
//    public Map<String, Object> updateRecommend(@RequestParam int cid, @RequestParam String column, @RequestParam String data){
//        Map<String, Object> res = new HashMap<>();
//        try {
//            int flag = mapper.updateRecommend(cid, column, data);
//            if(flag == 1){
//                res.put("state", 1);
//            }else{
//                res.put("state", 0);
//            }
//        }catch (Exception e){
//            log.error(e.toString());
//            res.put("state", 0);
//        }
//        return res;
//    }
//
//    // 更新话题的图片
//    @RequestMapping(value = "/update_topic", method = RequestMethod.POST)
//    public Map<String, Object> updateTopic(@RequestParam int cid, @RequestParam String column, @RequestParam String data){
//        Map<String, Object> res = new HashMap<>();
//        try {
//            int flag = mapper.updateTopic(cid, column, data);
//            if(flag == 1){
//                res.put("state", 1);
//            }else{
//                res.put("state", 0);
//            }
//        }catch (Exception e){
//            log.error(e.toString());
//            res.put("state", 0);
//        }
//        return res;
//    }

    // 更新推荐图片
    @RequestMapping(value = "/recommend_img", method = RequestMethod.POST)
    public Map<String, Object> updateRecommendImg(@RequestParam int cid, @RequestParam String name, @RequestParam("file") MultipartFile file) throws IOException {

        Map<String, Object> map = new HashMap<>();

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String t = format.format(System.currentTimeMillis());
        String prefix = PREFIX + "templates/";
        File f = new File(prefix);
        if (!f.isDirectory()) {
            f.mkdirs();
        }
        FileInputStream inputStream = (FileInputStream) file.getInputStream();
        String type = "jpg";
        String img = t + "." + type;
        String path = prefix + img;
        try{
            String imgFromDb = mapper.getRecommendImg(cid, name);
            log.info(imgFromDb);
            if (ImageUntil.saveImage(inputStream, path)) {
                inputStream.close();
                if (name.equals("picture") && !imgFromDb.equals("empty")){
                    mapper.updateRecommend(cid, name,imgFromDb + "&" + img);
                }else{
                    mapper.updateRecommend(cid, name, img);
                }
                log.info("public图片更新");
                map.put("state", 1);
                map.put("img", img);
                return map;
            }
            inputStream.close();
            map.put("state", 0);
            return map;
        }catch (Exception e){
            log.error(e.toString());
            inputStream.close();
            map.put("state", 0);
            return map;
        }finally {
            inputStream.close();
        }
    }

    // 更新话题图片
    @RequestMapping(value = "/topic_img", method = RequestMethod.POST)
    public Map<String, Object> updateTopicImg(@RequestParam int cid, @RequestParam String name, @RequestParam("file") MultipartFile file) throws IOException {

        Map<String, Object> map = new HashMap<>();

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String t = format.format(System.currentTimeMillis());
        String prefix = PREFIX + "templates/";
        File f = new File(prefix);
        if (!f.isDirectory()) {
            f.mkdirs();
        }
        FileInputStream inputStream = (FileInputStream) file.getInputStream();
        String type = "jpg";
        String img = t + "." + type;
        String path = prefix + img;
        try{
            String imgFromDb = mapper.getTopicImg(cid, name);
            log.info(imgFromDb);
            if (ImageUntil.saveImage(inputStream, path)) {
                inputStream.close();
                if (name.equals("picture") && !imgFromDb.equals("empty")){
                    mapper.updateTopic(cid, name,imgFromDb + "&" + img);
                }else{
                    mapper.updateTopic(cid, name, img);
                }
                log.info("public图片更新");
                map.put("state", 1);
                map.put("img", img);
                return map;
            }
            inputStream.close();
            map.put("state", 0);
            return map;
        }catch (Exception e){
            log.error(e.toString());
            inputStream.close();
            map.put("state", 0);
            return map;
        }finally {
            inputStream.close();
        }
    }



    /**
     * 移动端接口
     * */
    // 获取本期推荐
    @RequestMapping(value = "/pull_recommend")
    public ArrayList<RecommendEntity>  pullRecommend(@RequestParam int size, @RequestParam int page){
        ArrayList<RecommendEntity> res = new ArrayList<>();
        res = mapper.getRecommend(size, page);
        return res;
    }

    // 获取话题讨论
    @RequestMapping(value = "/pull_topic")
    public ArrayList<TopicEntity> pullTopic(@RequestParam int size, @RequestParam int page){
        ArrayList<TopicEntity> res = new ArrayList<>();
        res = mapper.getTopic(size, page);
        return res;
    }

    // 根据cid获取推荐
    @RequestMapping(value = "/pull_one_recommend")
    public RecommendEntity pullOneRecommend(@RequestParam int id){
        RecommendEntity res = mapper.getOneRecommend(id);
        return res;
    }

}
