package com.goott.service;

import com.goott.domain.PageQna;
import com.goott.domain.QnaVO;
import com.goott.mapper.QnaMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class QnaServiceImpl implements QnaService {

    @Inject
    QnaMapper qnaMapper;

    @Override
    public int totalQnaCount(String qnaSearchText, String qna_category) {
        return qnaMapper.countQna(qnaSearchText, qna_category);
    }

    @Override
    public List<QnaVO> getQnaList(PageQna pageQna) {
        return qnaMapper.selectList(pageQna);
    }

    @Override
    public QnaVO getQna(int qna_id) {
        return qnaMapper.select(qna_id);
    }

    @Override
    public String registerQna(QnaVO qnaVO, MultipartFile qna_picture_url) {
        // 파일 업로드 여부 확인
        if(!qna_picture_url.isEmpty()){
            String resultText = saveQnaImg(qna_picture_url);
            if(resultText.equals("업로드 파일 저장중 오류가 발생하였습니다. 관리자에게 문의해 주세요.")){
                return resultText;
            }
            else{
                qnaVO.setQna_picture_url(resultText);
                qnaMapper.insert(qnaVO);
                return "등록 하였습니다.";
            }
        }
       else{
            qnaVO.setQna_picture_url("not url");
            qnaMapper.insert(qnaVO);
            return "등록 하였습니다.";
        }
    }

    @Override
    public String saveQnaImg(MultipartFile qna_picture_url) {
        // 질문글 이미지 파일 기본 저장 주소
        String filePath = "C:/uploadtest/qnaImg/";
        filePath = filePath.replace("/", File.separator);

        // 오늘 날짜 폴더 경로
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String todayPath = sdf.format(today);
        todayPath = todayPath.replace("/", File.separator);

        // 파일 확장자
        String imgFileExtention = qna_picture_url.getOriginalFilename().substring(qna_picture_url.getOriginalFilename().lastIndexOf('.'));
        // 랜덤 문자열
        String uuid = UUID.randomUUID().toString();
        // 랜덤 이름
        String randFileName = uuid + imgFileExtention;

        // 폴더 생성
        File folder = new File(filePath + todayPath);
        folder.mkdirs();

        // 저장할 이미지 파일 생성
        File saveImgFile = new File(filePath + todayPath, randFileName);
        // 유저 업로드한 파일 복사
        try {
            qna_picture_url.transferTo(saveImgFile);
            return "/qnaImg/" + todayPath + "/" + randFileName;
        } catch (IOException e) {
           e.printStackTrace();
           return "업로드 파일 저장중 오류가 발생하였습니다. 관리자에게 문의해 주세요.";
        }

    }


}
