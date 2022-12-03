package com.goott.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Log4j
@Service
public class S3Service {

    @Value("${s3.bucketname}")
    private String bucketname;

    @Autowired
    AmazonS3 amazonS3;

    public String uploadS3Img(MultipartFile multipartFile, String saveType) throws IOException{
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String todayPath = sdf.format(today);
        String multipartFileExtention = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf('.'));
        String multipartFileName = saveType + "/" + todayPath + "/" + UUID.randomUUID().toString() + multipartFileExtention;

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getInputStream().available());
        objectMetadata.setContentType(multipartFile.getContentType());
        amazonS3.putObject(bucketname, multipartFileName, multipartFile.getInputStream(), objectMetadata);
        log.info(amazonS3.getUrl(bucketname, multipartFileName).toString());
        return amazonS3.getUrl(bucketname, multipartFileName).toString();
    }

    public void deleteS3Img(String fileName){
        amazonS3.deleteObject(bucketname, fileName);
    }

    public String getS3FileName(String fileUrl){
        int startIndex = fileUrl.indexOf('/',8);
        String fileName = fileUrl.substring(startIndex + 1);
        return fileName;
    }
}
