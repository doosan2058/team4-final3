package com.goott.mapper;

import com.goott.domain.PageQna;
import com.goott.domain.QnaVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QnaMapper {
    /**
     * 검색 조건 별 총 질문 게시글 수
     * @param qnaSearchText
     * @param qna_category
     * @return 게시글 수
     */
    public int countQna(@Param("qnaSearchText") String qnaSearchText, @Param("qna_category") String qna_category);

    /**
     * 검색된 질문 게시글 list
     * @param pageQna
     * @return 게시글 list
     */
    public List<QnaVO> selectList(PageQna pageQna);

    /**
     * 질문 게시글 상세
     * @param qna_id
     * @return QnaVO
     */
    public QnaVO select(int qna_id);

    /**
     * 질문 게시글 등록
     * @param qnaVO
     */
    public void insert(QnaVO qnaVO);
}
