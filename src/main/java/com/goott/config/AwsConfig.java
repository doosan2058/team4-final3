package com.goott.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// AWS S3 설정 클래스
public class AwsConfig {
    @Value("${s3.accesskey}")
    private String accesskey; // S3 접근 키

    @Value("${s3.secretkey}")
    private String secretkey; // S3 비밀번호

    @Value("${s3.bucketname}")
    private String bucketname; // S3 bucket 이름

    // AmazonS3 객체
    @Bean
    public AmazonS3 amazonS3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accesskey, secretkey);
        return AmazonS3ClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

}