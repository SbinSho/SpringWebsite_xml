package com.suho.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// 로그인이 되어있지 않으면 접근할수 없는 부분에 인터셉터 적용
public class AuthLoginInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(AuthLoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		// 현재 세션을 가져옴
		HttpSession session = request.getSession();
		
		// 현재 세션에 저장되어있는 loginUser가 존재하지 않으면
		// 로그인이 되어있지 않는 상태임으로 메인 홈페이지로 이동
		if( session.getAttribute("loginUser") == null) {
			
			response.sendRedirect("/");
			// 현재는 console만 찍지만 파일로 log를 남기는 작업도 할 예정
			logger.info("비정상적인 접근");
			return false;
		}
		
		return true;
	}
}
