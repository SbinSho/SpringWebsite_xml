package com.suho.web.interceptor;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.suho.web.dto.LoginDTO;
import com.suho.web.util.AuthInfo;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	// preHandle() : 컨트롤러 보다 먼저 수행되는 메서드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		logger.info("LoginInterceptor preHandle 진입 ");
		
		HttpSession session = request.getSession();
		
		// 세션에 저장된 유저가 없을 시에만 진입허용
		if( session.getAttribute("loginUser") == null) {
			return true;
		}
		
		response.sendRedirect("/");
		return false;
		
	}

	// postHandle() : 컨트롤러가 수행되고 화면이 보여지기 직전에 수행되는 메소드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		logger.info("LoginInterceptor postHandle 진입 ");
		
		// 현재 요청 방식이 GET 방식이면 메소드 종료
		if(request.getMethod().equals("GET")) {
			return;
		}
		
		// 현재 요청의 세션 가져오기
		// Controller 에서 사용된 데이터 전부 ModelMap에 담음
		HttpSession session = request.getSession();
		ModelMap map = modelAndView.getModelMap();

		LoginDTO loginDTO = (LoginDTO) map.get("loginDTO");
		
		Object valid_check = map.get("valid_check");
		Object db_check = map.get("db_check");
		
		// vaild_check 값이 null일 경우 Contorller에서 객체의 유효성 검사는 통과함
		// db_check 값이 null일 경우 유효성은 통과했으나, DB에서 ID가 조회되지 않을 경우
		if( valid_check != null || db_check != null) {
			
			modelAndView.setViewName("/member/login");
			return;
			
		}
		
		// 세션에 저장할 객체 사용
		AuthInfo auth = new AuthInfo(loginDTO);
		
		// 세션에 현재 사용자 정보 저장
		session.setAttribute("loginUser", auth);
		
		Cookie loginCookie = new Cookie("loginCookie", auth.getId());
		loginCookie.setPath("/");
		
		if( loginDTO.isReid() == true) {
			loginCookie.setMaxAge(7 * 24 * 60 * 60);
		} else {
			loginCookie.setMaxAge(0);
		}
		
		
		response.addCookie(loginCookie);
		response.sendRedirect("/");
		
		logger.info("LoginInterceptor postHandle 탈출 ");

		
		
	}
	
	
	

}
