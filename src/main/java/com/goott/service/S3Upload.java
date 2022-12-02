package com.goott.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.IOException;
import java.util.UUID;

@Service
public class S3Upload {

    @Value("${s3.bucketname}")
    private String bucketname;

    @Autowired
    AmazonS3 amazonS3;

    public String uploadTest(MultipartFile multipartFile) throws IOException{
        String s3FileName = UUID.randomUUID().toString() + "-" + multipartFile.getOriginalFilename();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getInputStream().available());
        objectMetadata.setContentType(multipartFile.getContentType());
        amazonS3.putObject(bucketname, s3FileName, multipartFile.getInputStream(), objectMetadata);

        return amazonS3.getUrl(bucketname, s3FileName).toString();
    }
}
