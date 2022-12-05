package com.goott.service;

import com.goott.domain.AnswerVO;
import com.goott.domain.PageQna;
import com.goott.domain.QnaVO;
import com.goott.mapper.QnaMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class QnaServiceImpl implements QnaService {

    @Inject
    QnaMapper qnaMapper;
    @Inject
    S3Service s3Service;

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
        if (!qna_picture_url.isEmpty()) {
            String resultText = saveQnaImg(qna_picture_url);
            if (resultText.equals("파일을 s3 에 업로드중 에러가 발생하였습니다.")) {
                return "업로드 파일 저장중 오류가 발생하였습니다. 관리자에게 문의해 주세요.";
            } else {
                qnaVO.setQna_picture_url(resultText);
                qnaMapper.insert(qnaVO);
                return "등록 하였습니다.";
            }
        } else {
            qnaVO.setQna_picture_url("not url");
            qnaMapper.insert(qnaVO);
            return "등록 하였습니다.";
        }
    }

    @Override
    public String saveQnaImg(MultipartFile qna_picture_url) {
        return s3Service.uploadS3Img(qna_picture_url, "qnaImg");
    }

    @Override
    public List<QnaVO> selectListAdmin(PageQna pageQna) {
        return qnaMapper.selectListAdmin(pageQna);
    }

    @Override
    public int countQnaAdmin(String qna_title, String qna_category, String qna_admin_answer) {
        return qnaMapper.countQnaAdmin(qna_title, qna_category, qna_admin_answer);
    }

    @Override
    public void update(QnaVO qnaVO) {
        qnaMapper.update(qnaVO);
    }

    @Override
    public void delete(int qna_id) {
        qnaMapper.delete(qna_id);
    }

    @Override
    public void insertAnswer(AnswerVO answerVO) {
        qnaMapper.insertAnswer(answerVO);
    }

    @Override
    public void updateAnswer(AnswerVO answerVO) {
        qnaMapper.updateAnswer(answerVO);
    }

    @Override
    public void deleteAnswer(int answer_id) {
        qnaMapper.deleteAnswer(answer_id);
    }

    @Override
    public void updateQnaAnswerY(int qna_id) {
        qnaMapper.updateQnaAnswerY(qna_id);
    }

    @Override
    public void updateQnaAnswerN(int qna_id) {
        qnaMapper.updateQnaAnswerN(qna_id);
    }

    @Transactional
    @Override
    public void registerAnswer(AnswerVO answerVO) {
        this.insertAnswer(answerVO);
        this.updateQnaAnswerY(answerVO.getQna_id());
    }

    @Transactional
    @Override
    public void deleteAnswerAdmin(AnswerVO answerVO) {
        this.deleteAnswer(answerVO.getAnswer_id());
        this.updateQnaAnswerN(answerVO.getQna_id());
    }

    @Override
    public String selectExImgUrl(int qna_id) {
        return qnaMapper.selectExImgUrl(qna_id);
    }

    @Override
    public void deleteExImgUrl(int qna_id) {
        String exImgUrl = this.selectExImgUrl(qna_id);

        if(!exImgUrl.equals("not url"))
            s3Service.deleteS3Img(exImgUrl);
    }

    @Override
    public void updateQna(QnaVO qnaVO, MultipartFile file) {

        if(!file.isEmpty()){
            String resultUrl = this.saveQnaImg(file);
            qnaVO.setQna_picture_url(resultUrl);
            this.deleteExImgUrl(qnaVO.getQna_id());
        }

        else{
            String resultUrl = this.selectExImgUrl(qnaVO.getQna_id());
            qnaVO.setQna_picture_url(resultUrl);
        }

        this.update(qnaVO);

    }

    @Override
    public AnswerVO getAnswer(int qna_id) {
        return qnaMapper.selectAnswer(qna_id);
    }


}
