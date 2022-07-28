package com.ycj.lab.controller;

import com.ycj.lab.entity.StoreInfo;
import com.ycj.lab.result.Result;
import com.ycj.lab.service.StoreInfoService;
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
@RequestMapping("/store")
public class StoreInfoController {
    @Autowired
    StoreInfoService storeInfoService;

    @RequestMapping(value = "/find_one_store", method = RequestMethod.POST)
    public Result findOneStore(@RequestParam long sid) {
        try {
            StoreInfo storeInfo = storeInfoService.queryStoreById(sid);
            if (storeInfo != null) {
                return Result.success(storeInfo);
            } else {
                return Result.error("Result is null");
            }
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @RequestMapping(value = "/find_stores", method = RequestMethod.POST)
    public Result findStoreByPage(@RequestParam int page, @RequestParam int size) {
        page = (page - 1) * size;
        try {
            List<StoreInfo> list = storeInfoService.queryStoreListByPage(page, size);
            if (!list.isEmpty()) {
                return Result.success(list);
            } else {
                return Result.error("ResultList is null");
            }
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @RequestMapping(value = "/find_stores_by_tag", method = RequestMethod.POST)
    public Result findStoreByTag(@RequestParam int page, @RequestParam int size,
                                 @RequestParam String style, @RequestParam String type) {
        page = (page - 1) * size;
        try {
            List<StoreInfo> list = storeInfoService.queryStoreListByTag(page, size, style, type);
            if (!list.isEmpty()) {
                return Result.success(list);
            } else {
                return Result.error("ResultListByTag is null");
            }
        }catch (Exception e){
            return Result.error(e);
        }

    }

    @RequestMapping(value = "/create_store", method = RequestMethod.POST)
    public Result createStore(@RequestBody StoreInfo storeInfo) {
        try {
            int res = storeInfoService.insertStore(storeInfo);
//        log.info(String.valueOf(res));
            if (res == 1) {
                return Result.success(storeInfo.getId());
            } else {
                return Result.error("Creation failed");
            }
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @RequestMapping(value = "/edit_store", method = RequestMethod.POST)
    public Result editStore(@RequestBody StoreInfo storeInfo) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        storeInfo.setUpdateTime(dateFormat.format(date));
        try {
            int res = storeInfoService.updateStore(storeInfo);
            if (res == 1) {
                return Result.success("Operation successful");
            } else {
                return Result.error("Operation failed");
            }
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @RequestMapping(value = "/upload_store_picture", method = RequestMethod.POST)
    public Result uploadStorePicture(@RequestParam long id, @RequestParam String imgType,
                                     @RequestParam MultipartFile file) throws IOException {
        Map<String, Object> map = new HashMap<>();

        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss");
        String t = format.format(System.currentTimeMillis());
        String prefix = PREFIX + "store/";
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
            int res = storeInfoService.updateImgById(id, imgType, img);
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

    @RequestMapping(value = "/update_store_state", method = RequestMethod.POST)
    public Result updateStoreState(@RequestParam long id,@RequestParam int state){
//        log.info(String.valueOf(state==1));
        if(state != 0 && state != 1 && state != 2){
            return Result.error("State code is error");
        }
        try {
            storeInfoService.updateStoreStateById(id,state);
            return Result.success("Update state successful");
        }catch (Exception e){
            return Result.error("Update state failed");
        }
    }
}
