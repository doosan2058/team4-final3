package com.goott.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

    public String uploadS3Img(MultipartFile multipartFile, String saveType) {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String todayPath = sdf.format(today);
        String multipartFileExtention = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf('.'));
        String multipartFileName = saveType + "/" + todayPath + "/" + UUID.randomUUID().toString() + multipartFileExtention;

        ObjectMetadata objectMetadata = new ObjectMetadata();

        try {
            objectMetadata.setContentLength(multipartFile.getInputStream().available());
            objectMetadata.setContentType(multipartFile.getContentType());
            amazonS3.putObject(bucketname, multipartFileName, multipartFile.getInputStream(), objectMetadata);
            return amazonS3.getUrl(bucketname, multipartFileName).toString();
        } catch (IOException e) {
            return "파일을 s3 에 업로드중 에러가 발생하였습니다.";
        }



    }

    public String uploadS3ThumbnailImg(MultipartFile multipartFile) {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String todayPath = sdf.format(today);
        String multipartFileExtention = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf('.'));
        String multipartFileName = "thumbnailImg/" + todayPath + "/" + "s_" + UUID.randomUUID().toString() + multipartFileExtention;

        BufferedImage bufferImage = null;
        try {
            bufferImage = ImageIO.read(multipartFile.getInputStream());
            BufferedImage thumbnailImage = Thumbnails.of(bufferImage).size(100, 200).asBufferedImage();

            ByteArrayOutputStream thumbOutput = new ByteArrayOutputStream();
            String imageType = multipartFile.getContentType();
            ImageIO.write(thumbnailImage, imageType.substring(imageType.indexOf("/")+1), thumbOutput);

            byte[] thumbBytes = thumbOutput.toByteArray();
            InputStream thumbInput = new ByteArrayInputStream(thumbBytes);

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(thumbBytes.length);
            objectMetadata.setContentType(multipartFile.getContentType());
            amazonS3.putObject(bucketname, multipartFileName, thumbInput, objectMetadata);

            thumbInput.close();
            thumbOutput.close();

            return amazonS3.getUrl(bucketname, multipartFileName).toString();
        } catch (IOException e) {
            return "썸네일 파일을 s3 에 업로드중 에러가 발생하였습니다.";
        }

    }

    public void deleteS3Img(String fileName){
        log.info(this.getS3FileName(fileName));
        amazonS3.deleteObject(bucketname, this.getS3FileName(fileName));
    }

    public String getS3FileName(String fileUrl){
        int startIndex = fileUrl.indexOf('/',8);
        String fileName = fileUrl.substring(startIndex + 1);
        return fileName;
    }
}
