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
import javax.inject.Inject;
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

    @Inject
    AmazonS3 amazonS3;

    public String uploadS3Img(MultipartFile multipartFile, String saveType) {
        // 파일 루트 초기화(파일종류/업로드날짜/랜덤이름)
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String todayPath = sdf.format(today);
        String multipartFileExtention = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf('.'));
        String multipartFileName = saveType + "/" + todayPath + "/" + UUID.randomUUID().toString() + multipartFileExtention;

        ObjectMetadata objectMetadata = new ObjectMetadata(); // 업로드 파일 객체 메타데이터 설정

        try {
            objectMetadata.setContentLength(multipartFile.getInputStream().available()); // 메타데이터 크기 설정
            objectMetadata.setContentType(multipartFile.getContentType()); // 메타데이터 타입 설정
            amazonS3.putObject(bucketname, multipartFileName, multipartFile.getInputStream(), objectMetadata);
            return amazonS3.getUrl(bucketname, multipartFileName).toString();
        } catch (IOException e) {
            return "파일을 s3 에 업로드중 에러가 발생하였습니다.";
        }



    }

    public String uploadS3ThumbnailImg(MultipartFile multipartFile) { // 메인 이미지용 업로드(썸네일 제작후 같이 업로드)

        // 1. 원본 파일로 썸네일 파일 생성 (BufferedImage)
        // 2. 생성된 썸네일 파일을 바이트 배열로 변환 및 스트림 연결
        // 3. 생성된 스트림이용하여 AWS S3에 업로드(putObject)

        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String todayPath = sdf.format(today);
        String multipartFileExtention = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf('.'));
        String multipartFileName = "thumbnailImg/" + todayPath + "/" + "s_" + UUID.randomUUID().toString() + multipartFileExtention;

        BufferedImage bufferImage = null;
        try {
            bufferImage = ImageIO.read(multipartFile.getInputStream()); // 이미지 읽기
            BufferedImage thumbnailImage = Thumbnails.of(bufferImage).size(100, 200).asBufferedImage(); // 썸네일 이미지 제작

            ByteArrayOutputStream thumbOutput = new ByteArrayOutputStream();
            String imageType = multipartFile.getContentType();
            // 바이트배열아웃풋스트림에 썸네일 이미지 생성 및 연결
            ImageIO.write(thumbnailImage, imageType.substring(imageType.indexOf("/")+1), thumbOutput);

            byte[] thumbBytes = thumbOutput.toByteArray(); // 바이트 배열로 변환
            InputStream thumbInput = new ByteArrayInputStream(thumbBytes); // 인풋스트림 연결

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

        amazonS3.deleteObject(bucketname, this.getS3FileName(fileName));
    }

    public String getS3FileName(String fileUrl){ // 파일 주소에서 부켓 이름 삭제
        int startIndex = fileUrl.indexOf('/',8);
        String fileName = fileUrl.substring(startIndex + 1);
        return fileName;
    }
}
