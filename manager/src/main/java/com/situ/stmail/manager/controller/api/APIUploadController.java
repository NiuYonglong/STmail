package com.situ.stmail.manager.controller.api;

import com.situ.stmail.manager.entity.Result;
import com.situ.stmail.manager.util.UploadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class APIUploadController {
    @Value("${upload.path}")
    //使用spring的el表达式，读取配置文件中的值。
    private String path;

    @PostMapping
    public Result upload(MultipartFile file){
        String fileName = UploadUtil.save(file,path);
        if(fileName!=null) return Result.success(fileName);
        else return Result.error("上传失败");

    }

}
