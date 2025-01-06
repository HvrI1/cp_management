package com.wsn.cp_management.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

import java.io.InputStream;
//定义了一个AliOssUtil类，其中包含一个uploadFile方法，用于文件的上传。
public class AliOssUtil {

    //OSS服务的bucket名称。
    private static final String BUCKT_NAME = "cp-management";
    public static String uploadFile(String objectName, InputStream in) throws Exception {
        //使用OSSClientBuilder创建一个OSSClient实例，参数为OSS服务的 endpoint、accessKeyID和accessKeySecret。
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT,OSS_ACCESS_KEY_ID,OSS_ACCESS_KEY_SECRET);
        //在uploadFile方法中，首先定义了一个字符串url，用于存储文件上传后的URL。
        String url="";
        try {
            //创建一个PutObjectRequest对象，将要上传的文件作为输入流in传递给该对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKT_NAME, objectName, in);
            //使用OSSClient的putObject方法上传文件，并获取上传结果。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            //将上传结果的URL存储在url变量中，并返回。
            url="https://"+BUCKT_NAME+"."+ENDPOINT.substring(ENDPOINT.lastIndexOf("/")+1)+"/"+objectName;
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
        } finally {
            //在finally块中，如果ossClient不为空，则关闭OSSClient连接。
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return url;
    }
}
