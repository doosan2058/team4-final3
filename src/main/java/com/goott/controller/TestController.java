package com.goott.controller;

import com.goott.service.S3Service;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Log4j
@Controller
public class TestController {
    @Autowired
    S3Service s3Service;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testUpload(){
        return "/test";
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String testUploadPost(@RequestParam MultipartFile file) throws IOException {
        String saveType = "productImg";
        s3Service.uploadS3Img(file, saveType);

        return "/test";
    }

    @RequestMapping(value = "/test/delete", method = RequestMethod.GET)
    public String testDelete(@RequestParam String fileName){
        s3Service.deleteS3Img(fileName);
        return "/test";
    }

    @RequestMapping(value = "/test/getFileName", method = RequestMethod.GET)
    public String testGetFileName(@RequestParam String fileUrl){
        log.info(s3Service.getS3FileName(fileUrl));
        return "/test";
    }
}
