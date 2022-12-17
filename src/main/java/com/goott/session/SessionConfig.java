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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Log4j
public class SessionConfig implements HttpSessionListener {
    // 세션 생성시 (세션 아이디, 세션) 맵에 저장
    // tomcat은 jsessionId
    private static final Map<String, HttpSession> SESSIONMAP = new ConcurrentHashMap<>();

    public synchronized static void checkLoginId(String login_id) {
        for (String key : SESSIONMAP.keySet()) { // 반복 돌면서 저장된 세션의 어트리뷰트 체크(login_id)
            HttpSession tempSession = SESSIONMAP.get(key);
            if (tempSession != null && tempSession.getAttribute("login_id") != null && tempSession.getAttribute("login_id").toString().equals(login_id)) {
				log.info("세션맵에 저장된 세션이 있다.. : " + tempSession.getAttribute("login_id").toString());
				tempSession.invalidate();
				SESSIONMAP.remove(key);
				log.info("중복 로그인 세션 제거 : " +  login_id);
            }
        }
    }

    // 세션 생성시
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // 세션 생성시 디비에 정보 저장
        HttpSession session = se.getSession(); // 세션 생성
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()); // 서블릿 컨텍스트 리턴
        SessionService sessionService = (SessionService) context.getBean("sessionService"); // servlet-context 에 등록한 seesionService 빈 호출

        // request를 파라미터에 넣지 않고도 사용할수 있도록 설정
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        SessionVO sessionVO = new SessionVO();
        sessionVO.setBrowser(request.getHeader("User-Agent")); // 방문자 접속 브라우저
        sessionVO.setIp(request.getRemoteAddr()); // 방문자 접속 아이피

        sessionService.setSessionInfo(sessionVO); // 방문자 정보 DB 에 저장

        // 세션 생성시 세션테이블에 저장
        String sessionId = session.getId(); // 세션 아이디
        SESSIONMAP.put(sessionId, session); // 맵에 저장


    }

    // 세션 소멸시
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
		if(SESSIONMAP.get(se.getSession().getId()) != null){ // 맵에서 해당 세션 제거
			SESSIONMAP.get(se.getSession().getId()).invalidate(); // 세션 소멸
			SESSIONMAP.remove(se.getSession().getId()); // 맵에서 해당 세션 제거
		}


    }

}
