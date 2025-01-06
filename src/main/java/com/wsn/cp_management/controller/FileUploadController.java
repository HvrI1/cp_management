package com.wsn.cp_management.controller;

import com.wsn.cp_management.pojo.Result;
import com.wsn.cp_management.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        //把文件内容传递到本地磁盘中
        String originalFilename = file.getOriginalFilename();
        //保证名字是唯一的，防止文件覆盖
        String filename= UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        //file.transferTo(new File("C:\\Users\\Hangoverd\\Desktop\\04_综合案例资料\\file\\"+filename));
        String url = AliOssUtil.uploadFile(filename, file.getInputStream());
        return Result.success(url);
    }

}
