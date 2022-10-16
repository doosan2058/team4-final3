package com.goott.service;

import java.util.List;
import java.util.Map;

import com.goott.domain.PagingVO;
import com.goott.domain.QnAVO;

public interface QnAService {
	
//	public List<QnAVO> list();

	public void Question(QnAVO QnA);

	public Object QnA_detail(Map<String, Object> map);

	public void QnA_update(QnAVO qnA);

	public void delete(QnAVO QnA);
	// 게시물 총 갯수
	public int countQnABoard();

	// 페이징 처리 게시글 조회
	public List<QnAVO> selectQnABoard(PagingVO vo, String checkcategory, String QnASearch);

	public int countQnABoardCategory(String checkcategory, String QnASearch);

	

	public void Question_update(QnAVO qnA);

	public void Question_admin(QnAVO qnA);

	public void Question_admin_answer(QnAVO qnA);

	// 페이징 처리 게시글 조회
	// 관리자
//	public List<QnAVO> selectQnAadminBoard(PagingVO vo, String checkcategory, String QnASearch, String checkadmin);
//
//	public int countQnAadminBoardCategory(String checkcategory, String QnASearch, String checkadmin);
	
	// 페이징 처리 게시글 조회
	// 관리자
	public List<QnAVO> selectQnAadminBoard(PagingVO vo, String checkadmin, String QnASearch);

	public int countQnAadminBoardCategory(String checkadmin, String QnASearch);

	
	public void Question_admin_update(QnAVO qnA);




	

	
	
}
