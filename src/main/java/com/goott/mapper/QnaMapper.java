package com.goott.mapper;

import com.goott.domain.AnswerVO;
import com.goott.domain.PageQna;
import com.goott.domain.QnaVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QnaMapper {
    /**
     * 검색 조건 별 총 질문 게시글 수
     *
     * @param qnaSearchText 검색어
     * @param qna_category 카테고리
     * @return 게시글 수
     */
    public int countQna(@Param("qnaSearchText") String qnaSearchText, @Param("qna_category") String qna_category);

    /**
     * 검색된 질문 게시글 list
     *
     * @param pageQna
     * @return 게시글 list
     */
    public List<QnaVO> selectList(PageQna pageQna);

    /**
     * 질문 게시글 상세
     *
     * @param qna_id
     * @return QnaVO
     */
    public QnaVO select(int qna_id);

    /**
     * 질문 게시글 등록
     *
     * @param qnaVO
     */
    public void insert(QnaVO qnaVO);

    /**
     * 관리자용 질문글 목록 가져오기
     *
     * @param pageQna
     * @return
     */
    public List<QnaVO> selectListAdmin(PageQna pageQna);

    /**
     * 관리자용 질문글 총 개수
     *
     * @param qnaSearchText 검색어
     * @param qna_category 질문글 카테고리
     * @param qna_admin_answer 관리자 답변 여부
     * @return
     */
    public int countQnaAdmin(@Param("qnaSearchText") String qnaSearchText,
                             @Param("qna_category") String qna_category,
                             @Param("qna_admin_answer") String qna_admin_answer);

    /**
     * 질문글 수정
     *
     * @param qnaVO
     */
    public void update(QnaVO qnaVO);

    /**
     * 질문글 삭제
     *
     * @param qna_id
     */
    public void delete(int qna_id);

    /**
     * 답변글 등록
     *
     * @param answerVO
     */
    public void insertAnswer(AnswerVO answerVO);

    /**
     * 답변글 수정
     *
     * @param answerVO
     */
    public void updateAnswer(AnswerVO answerVO);

    /**
     * 답변글 삭제
     *
     * @param answer_id
     */
    public void deleteAnswer(int answer_id);

    /**
     * 답변 여부 y
     *
     * @param qna_id
     */
    public void updateQnaAnswerY(int qna_id);

    /**
     * 답변 여부 n
     *
     * @param qna_id
     */
    public void updateQnaAnswerN(int qna_id);

    /**
     * 질문글 사진 주소 가져오기
     *
     * @param qna_id
     * @return
     */
    public String selectExImgUrl(int qna_id);

    /**
     * 답변글 가져오기
     *
     * @param qna_id
     * @return
     */
    public AnswerVO selectAnswer(int qna_id);
}
