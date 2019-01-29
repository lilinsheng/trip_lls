package cn.wolfcode.trip.base.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class QiniuUtil {

    public static final String QI_PATH = "http://pjkle2jx9.bkt.clouddn.com/";

    public static String qiniuyunImage(MultipartFile file){


        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "p6z9apfNH1FE-krIbZqoLraYWFtv_dFJkZiFi55v";
        String secretKey = "tCvxf4DTWcpYq5FZxqQx11eINWGMZp_4T3Eoa3nE";
        String bucket = "trip";
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = null;
            try {
                response = uploadManager.put(file.getBytes(), key, upToken);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return null;
    }

    /**
     * 获取文件拓展名
     * @param file
     * @return
     */
    public static String getExtName(MultipartFile file){
        String orgFileName = file.getOriginalFilename();
        String ext= "." + FilenameUtils.getExtension(orgFileName);
        return ext;
    }
}
