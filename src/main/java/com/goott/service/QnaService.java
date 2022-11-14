package com.goott.service;

import com.goott.domain.AnswerVO;
import com.goott.domain.PageQna;
import com.goott.domain.QnaVO;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 관리자용 질문글 목록 가져오기
     *
     * @param pageQna
     * @return
     */
    public List<QnaVO> selectListAdmin(PageQna pageQna);

    /**
     * 관리자용 질문글 총 개수
     * @param qna_title
     * @param qna_category
     * @param qna_admin_answer
     * @return
     */
    public int countQnaAdmin(@Param("qna_title") String qna_title,
                        @Param("qna_category") String qna_category,
                        @Param("qna_admin_answer") String qna_admin_answer);

    /**
     * 질문글 수정
     * @param qnaVO
     */
    public void update(QnaVO qnaVO);

    /**
     * 질문글 삭제
     * @param qna_id
     */
    public void delete(int qna_id);

    /**
     * 답변글 등록
     * @param answerVO
     */
    public void insertAnswer(AnswerVO answerVO);

    /**
     * 답변글 수정
     * @param answerVO
     */
    public void updateAnswer(AnswerVO answerVO);

    /**
     * 답변글 삭제
     * @param answer_id
     */
    public void deleteAnswer(int answer_id);

    /**
     * 답변 여부 y
     * @param qna_id
     */
    public void updateQnaAnswerY(int qna_id);

    /**
     * 답변 여부 n
     * @param qna_id
     */
    public void updateQnaAnswerN(int qna_id);

    /**
     * 답변글 등록
     * @param answerVO
     */
    public void registerAnswer(AnswerVO answerVO);

    /**
     * 답변글 삭제 , 답변 여부 업데이트
     * @param answerVO
     */
    public void deleteAnswerAdmin(AnswerVO answerVO);

    /**
     * 이전 질문글 사진 주소 가져오기
     * @param qna_id
     * @return
     */
    public String selectExImgUrl(int qna_id);

    /**
     * 이전 질문글 사진 삭제
     * @param qna_id
     */
    public void deleteExImgUrl(int qna_id);

    /**
     * 질문글 수정 통합
     * @param qnaVO
     * @param file
     */
    public void updateQna(QnaVO qnaVO, MultipartFile file);

    /**
     * 답변글 가져오기
     * @param qna_id
     * @return
     */
    public AnswerVO getAnswer(int qna_id);


}
