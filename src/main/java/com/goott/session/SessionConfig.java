package com.goott.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.goott.domain.SessionVO;
import com.goott.service.SessionService;

import lombok.extern.log4j.Log4j;

@Log4j
public class SessionConfig implements HttpSessionListener {
	
	// 세션 생성시
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession(); // 세션 생성
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()); // 서블릿 컨텍스트 리턴
		SessionService sessionService = (SessionService) context.getBean("sessionService"); // seesionService 빈 호출
		
		// request를 파라미터에 넣지 않고도 사용할수 있도록 설정
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		
		SessionVO sessionVO = new SessionVO();
		sessionVO.setBrowser(request.getHeader("User-Agent")); // 방문자 접속 브라우저
		sessionVO.setIp(request.getRemoteAddr()); // 방문자 접속 아이피

		sessionService.setSessionInfo(sessionVO); // 방문자 정보 DB 에 저장
		
		
	}
	// 세션 소멸시
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		
		
	}

}
