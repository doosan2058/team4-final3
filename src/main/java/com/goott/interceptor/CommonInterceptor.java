package com.goott.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;
@Log4j
public class CommonInterceptor implements HandlerInterceptor{

	
	/**
	 * 테스트용으로 shop 페이지 로그인 되있지 않으면 로그인 페이지로 이동
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		log.info("인터셉터 ==============================================================");
		
		//세션 생성
		HttpSession session = request.getSession();
		//세션 로그인 체크(로그인아이디가 있는지)
		
		//로그인 아이디가 없다면
		if(session.getAttribute("login_id") == null) {
//			log.info("세션 로그인 여부 체크중 =======================================");
			//로그인 페이지 리다이렉트 전 보려던 페이지 주소 세션에 저장
//			String prior_uri  = request.getRequestURI();
//			
//			
//			if(request.getQueryString() != null) {
//				String query_string = request.getQueryString();
//				session.setAttribute("query_string", query_string);
//
//			}
//			session.setAttribute("prior_uri", prior_uri);
//			log.info("접속 시도 하였던 주소 : " + prior_uri);
			
			//없으면 로그인 페이지로 리다이렉트 후 리턴 false
			//로그인 페이지로 리다이렉트
//			log.info("리다이렉트 보내는 중 =======================================");
			session.setAttribute("msg", "로그인이 필요한 페이지 입니다. 로그인 페이지로 이동합니다.");
			session.setAttribute("url", "/login");
			response.sendRedirect("/alert");
			return false;
		}
		
		
		//있다면 리턴 트루
		else {
//			log.info("로그인 중 , 아이디 : " + session.getAttribute("login_id").toString());
			return true;
			
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
