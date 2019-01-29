package cn.wolfcode.trip.admin.test;

import com.aliyun.oss.OSSClient;
import java.io.File;

public class AliyunTest {
    public static void main(String[] args) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAICHEMfN1lpfUD";
        String accessKeySecret = "qNwXX9f0sp1a9V4t7WDCSMToYRP2HY";

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        // 上传文件。<yourLocalFile>由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt。
        ossClient.putObject("lls-trip", "<yourObjectName>", new File("D:\\trip\\upload\\7e854d1d-a7be-4360-8943-8771d18ee0e3.jpg"));
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
