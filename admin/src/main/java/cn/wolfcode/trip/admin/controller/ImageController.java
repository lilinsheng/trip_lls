package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.util.QiniuUtil;
import cn.wolfcode.trip.base.util.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/images")
public class ImageController {

    @RequestMapping
    @ResponseBody
    public Map upload(MultipartFile upload){
        Map<String,Object> map = new HashMap<>();
        try {
            String url = QiniuUtil.QI_PATH+QiniuUtil.qiniuyunImage(upload);
            map.put("uploaded",1);
            map.put("url",url);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("uploaded",0);
            map.put("msg","上传图片失败");
            Map<String,Object> err = new HashMap<>();
            err.put("message","图片上传失败");
            map.put("error",err);
        }
        return map;
    }
}
