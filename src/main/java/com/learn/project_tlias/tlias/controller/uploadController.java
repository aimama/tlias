package com.learn.project_tlias.tlias.controller;

import com.learn.project_tlias.tlias.Utils.AliOSSUtils;
import com.learn.project_tlias.tlias.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class uploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传，文件名字："+image.getOriginalFilename());

        return Result.success(aliOSSUtils.upload(image));
    }
}
