package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.util.ALiYunUtil;
import cn.wolfcode.trip.base.util.QiniuUtil;
import cn.wolfcode.trip.base.util.UploadUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 图片出上传
 */
@RestController
@RequestMapping("/images")
public class ImageController {

    @PostMapping
    @ResponseBody
    public Map upload(MultipartFile file){
        Map<String,Object> map = new HashMap<>();
        try {
            String url = ALiYunUtil.upload(file);
            map.put("status",1);
            map.put("url",url);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",0);
            map.put("msg","上传图片失败");
        }
        return map;
    }
}
