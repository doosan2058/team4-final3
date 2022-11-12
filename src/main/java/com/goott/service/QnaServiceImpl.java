package com.goott.service;

import com.goott.domain.PageQna;
import com.goott.domain.QnaVO;
import com.goott.mapper.QnaMapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

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


}
