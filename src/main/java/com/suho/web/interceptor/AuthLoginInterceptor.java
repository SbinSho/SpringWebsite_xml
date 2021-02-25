package com.suho.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(AuthLoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		logger.info("AuthLoginInterceptor preHandle 진입 ");
		
		HttpSession session = request.getSession();
		
		if( session.getAttribute("loginUser") == null) {
			
			response.sendRedirect("/");
			logger.info("비정상적인 접근");
			return false;
		}
		
		logger.info("AuthInterceptor preHandle 탈출 ");
		
		return true;
	}

}
