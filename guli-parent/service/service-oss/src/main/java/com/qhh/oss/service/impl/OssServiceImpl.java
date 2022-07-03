package com.qhh.oss.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.qhh.oss.service.OssService;
import com.qhh.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author: QiuHH
 * @CreateTime: 2022-07-03  10:55
 * @Description: TODO
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = ConstantPropertiesUtils.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantPropertiesUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
        // 填写Bucket名称，例如examplebucket。
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
//        获取文件名称
        String objectName = UUID.randomUUID().toString().replaceAll("-", "") + file.getOriginalFilename();

//        文件按日期分类
        String datePath = new DateTime().toString("yyyy/MM/dd");
        objectName = datePath + "/" + objectName;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
//            获取文件输入流
            InputStream inputStream = file.getInputStream();
            // 创建PutObject请求。
            ossClient.putObject(bucketName, objectName, inputStream);
            return "https://" + bucketName + "." + endpoint + "/" + objectName;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return null;
    }
}
