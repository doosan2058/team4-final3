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
     *
     * @param qnaSearchText 검색어
     * @param qna_category  카테고리
     * @return 게시글 수
     */
    public int totalQnaCount(String qnaSearchText, String qna_category);

    /**
     * 검색된 게시글 list
     *
     * @param pageQna 질문글 게시판 페이지 엔티티
     * @return 게시글 list
     */
    public List<QnaVO> getQnaList(PageQna pageQna);

    /**
     * qna 상세
     *
     * @param qna_id 질문글 번호
     * @return QnaVO 질문글 엔티티
     */
    public QnaVO getQna(int qna_id);

    /**
     * 회원 질문글 등록
     *
     * @param qnaVO           질문글 엔티티
     * @param qna_picture_url 작성자 프로필 이미지 주소
     * @return
     */
    public String registerQna(QnaVO qnaVO, MultipartFile qna_picture_url);

    /**
     * 질문글 사진 등록
     *
     * @param qna_picture_url 질문글 첨부파일(이미지)
     * @return 처리 결과
     */
    public String saveQnaImg(MultipartFile qna_picture_url);

    /**
     * 관리자용 질문글 목록 가져오기
     *
     * @param pageQna 질문글 게시판 페이지 엔티티
     * @return 질문글 목록(10개)
     */
    public List<QnaVO> selectListAdmin(PageQna pageQna);

    /**
     * 관리자용 질문글 총 개수
     *
     * @param qna_title        제목
     * @param qna_category     카테고리
     * @param qna_admin_answer 답변 여부
     * @return 조건에 맞는 질문글 총 개수
     */
    public int countQnaAdmin(@Param("qna_title") String qna_title,
                             @Param("qna_category") String qna_category,
                             @Param("qna_admin_answer") String qna_admin_answer);

    /**
     * 질문글 수정
     *
     * @param qnaVO 질문글 엔티티
     */
    public void update(QnaVO qnaVO);

    /**
     * 질문글 삭제
     *
     * @param qna_id 질문글 아이디
     */
    public void delete(int qna_id);

    /**
     * 답변글 등록
     *
     * @param answerVO 답변글 엔티티
     */
    public void insertAnswer(AnswerVO answerVO);

    /**
     * 답변글 수정
     *
     * @param answerVO 답변글 엔티티
     */
    public void updateAnswer(AnswerVO answerVO);

    /**
     * 답변글 삭제
     *
     * @param answer_id 답변글 번호
     */
    public void deleteAnswer(int answer_id);

    /**
     * 답변 여부 y 업데이트
     *
     * @param qna_id 질문글 번호
     */
    public void updateQnaAnswerY(int qna_id);

    /**
     * 답변 여부 n 업데이트
     *
     * @param qna_id 질문글 번호
     */
    public void updateQnaAnswerN(int qna_id);

    /**
     * 답변글 등록 처리 (답변 등록 및 원본 글 답변 여부 업데이트)
     *
     * @param answerVO 답변글 엔티티
     */
    public void registerAnswer(AnswerVO answerVO);

    /**
     * 답변글 삭제 및 답변 여부 n 으로 업데이트
     *
     * @param answerVO 답변 엔티티
     */
    public void deleteAnswerAdmin(AnswerVO answerVO);

    /**
     * 이전 질문글 첨부파일 주소 가져오기
     *
     * @param qna_id 질문글 번호
     * @return 질문글 첨부파일 주소
     */
    public String selectExImgUrl(int qna_id);

    /**
     * 이전 질문글 첨부파일 삭제
     *
     * @param qna_id
     */
    public void deleteExImgUrl(int qna_id);

    /**
     * 질문글 수정 통합(질문글 업데이트 + 새로운 첨부파일 업데이트)
     *
     * @param qnaVO 질문글 엔티티
     * @param file  첨부파일
     */
    public void updateQna(QnaVO qnaVO, MultipartFile file);

    /**
     * 답변글 가져오기
     *
     * @param qna_id 질문글 번호
     * @return 해당 답변글
     */
    public AnswerVO getAnswer(int qna_id);


}
