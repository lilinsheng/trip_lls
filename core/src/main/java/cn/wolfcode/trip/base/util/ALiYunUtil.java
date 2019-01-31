package cn.wolfcode.trip.base.util;

import com.aliyun.oss.OSSClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

public class ALiYunUtil {
    // Endpoint以杭州为例，其它Region请按实际情况填写。
    static String endpoint = "oss-cn-shenzhen.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    static String accessKeyId = "LTAICHEMfN1lpfUD";
    static String accessKeySecret = "qNwXX9f0sp1a9V4t7WDCSMToYRP2HY";
    static String bucketName = "lls-trip";



    public static String upload(MultipartFile file) {
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        // 创建存储空间。
        String objectName = UUID.randomUUID().toString();
        try {
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 关闭OSSClient。
        ossClient.shutdown();
        return "http://lls-trip.oss-cn-shenzhen.aliyuncs.com/"+objectName;
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
