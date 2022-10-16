package com.goott.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.goott.domain.MemberVO;
import com.goott.mapper.AdminMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class AdminServiceImpl implements AdminService {
	@Inject
	AdminMapper adminMapper;
	@Inject
	MailSendService mailSendService;
	//회원 리스트
	@Override
	public List<MemberVO> getMemberAllList() {
		
		return adminMapper.selectAllMemberList();
	}
	//회원 상세
	@Override
	public MemberVO getMemberInfo(String member_id) {
		
		return adminMapper.selectMemberInfo(member_id);
	}
	@Override
	public List<Map<String, Object>> getFreeList(String member_id) {
		
		return adminMapper.selectFreeList(member_id);
	}
	@Override
	public List<Map<String, Object>> getCampingList(String member_id) {
	
		return adminMapper.selectCampingList(member_id);
	}
	@Override
	public List<Map<String, Object>> getQnaList(String member_id) {
	
		return adminMapper.selectQnaList(member_id);
	}
	@Override
	public List<Map<String, Object>> getOrderList(String member_id) {
		
		return adminMapper.selectOrderList(member_id);
	}
	@Override
	public List<String> getEmailList() {
		
		return adminMapper.selectEmailList();
	}
	@Override
	public void sendPromotionEmail(List<String> toTemp, String subjectTemp, String textTemp) {
		Iterator<String> itor = toTemp.iterator();
		
		while(itor.hasNext()) {
			String email = itor.next().toString();
			mailSendService.promotionMail(email, subjectTemp, textTemp);
		}
		
	}
	@Override
	public void setPromotionEmail(String subject, String content) {
		List<String> list = this.getEmailList();
		sendPromotionEmail(list, subject, content);
		
	}

}
