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
		
		// 기존의 로그인 정보 제거
		if( session.getAttribute("loginUser") != null) {
			session.removeAttribute("loginUser");
		}
		
		logger.info("LoginInterceptor preHandle 탈출 ");
		
		return true;
	}

	// postHandle() : 컨트롤러가 수행되고 화면이 보여지기 직전에 수행되는 메소드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		logger.info("LoginInterceptor postHandle 진입 ");
		
		// 현재 요청의 세션 가져오기
		HttpSession session = request.getSession();
		// Controller 에서 사용된 데이터 전부 ModelMap에 담음
		ModelMap map = modelAndView.getModelMap();
		
		// Controller에서 가져온 model에서 auth 키 값에 해당하는 데이터 가져오기
		LoginDTO loginDTO = (LoginDTO) map.get("loginDTO");
		// Controller에서 가져온 model에서 valud_check 키 값에 해당하는 데이터 가져오기
		Object valid_check = map.get("valid_check");
		Object db_result = map.get("result");
		
		
		// vaild_check 값이 null일 경우 Contorller에서 객체의 유효성 검사는 통과함을 의미한다.
		// auth 값이 null일 경우 유효성은 통과했으나, DB에서 ID가 조회되지 않을 경우
		if( valid_check != null || db_result != null) {
			
			modelAndView.setViewName("/member/login");
			return;
		}
		
		
		
		// 세션에 저장할 객체 사용
		AuthInfo auth = new AuthInfo(loginDTO);
		
		session.setAttribute("loginUser", auth);
		
		Cookie loginCookie = new Cookie("loginCookie", auth.getId());
		loginCookie.setPath("/");
		
		if( loginDTO.isReid() == true) {
			loginCookie.setMaxAge(7 * 24 * 60 * 60);
		} else {
			loginCookie.setMaxAge(0);
		}
		
		
		response.addCookie(loginCookie);
		
		modelAndView.setViewName("redirect:/");

		logger.info("LoginInterceptor postHandle 탈출 ");

		
		
	}
	
	
	

}
