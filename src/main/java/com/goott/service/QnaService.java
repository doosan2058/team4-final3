package com.goott.service;

import com.goott.domain.PageQna;
import com.goott.domain.QnaVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QnaService {
    /**
     * 검색 조건 별 총 게시글 수
     * @param qnaSearchText
     * @param qna_category
     * @return 게시글 수
     */
    public int totalQnaCount(String qnaSearchText, String qna_category);

    /**
     * 검색된 게시글 list
     * @param pageQna
     * @return 게시글 list
     */
    public List<QnaVO> getQnaList(PageQna pageQna);

    /**
     * qna 상세
     * @param qna_id
     * @return QnaVO
     */
    public QnaVO getQna(int qna_id);

    /**
     * 회원 질문글 등록
     * @param qnaVO
     * @param qna_picture_url
     * @return
     */
    public String registerQna(QnaVO qnaVO, MultipartFile qna_picture_url);

    /**
     * 질문글 사진 등록
     * @param qna_picture_url
     * @return
     */
    public String saveQnaImg(MultipartFile qna_picture_url);

}
