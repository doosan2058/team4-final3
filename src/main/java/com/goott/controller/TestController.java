package com.goott.controller;

import com.goott.service.S3Upload;
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
    S3Upload s3Upload;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testUpload(){
        return "/test";
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String testUploadPost(@RequestParam MultipartFile file) throws IOException {
        log.info("=================test==================");
        log.info(file);
        String url = s3Upload.uploadTest(file);
        log.info(url);
        return "/test";
    }
}
