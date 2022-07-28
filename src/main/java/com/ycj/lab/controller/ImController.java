package com.ycj.lab.controller;

import com.ycj.lab.until.ImageUntil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static com.ycj.lab.common.Const.PREFIX;

/**
 * @author 53059
 * @date 2021/7/10 0:09
 */
@RestController
@RequestMapping("/im")
public class ImController {

    @RequestMapping(value = "/img", method = RequestMethod.POST)
    public Map<String, Object> pushImage(@RequestParam("file") MultipartFile file) throws IOException {

        Map<String, Object> map = new HashMap<>();

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String t = format.format(System.currentTimeMillis());
        String prefix = PREFIX + "im/img/";
        File f = new File(prefix);
        if (!f.isDirectory()) {
            f.mkdirs();
        }
        FileInputStream inputStream = (FileInputStream) file.getInputStream();
        String type = "jpg";
        String img = t + "." + type;
        String path = prefix + img;

        if (ImageUntil.saveImage(inputStream, path)) {
            inputStream.close();
            map.put("state", 1);
            map.put("img", img);
            return map;
        }
        inputStream.close();
        map.put("state", 0);
        return map;
    }
}
