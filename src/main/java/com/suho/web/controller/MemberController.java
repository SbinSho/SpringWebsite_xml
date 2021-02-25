package com.suho.web.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.suho.web.domain.MemberVO;
import com.suho.web.dto.LoginDTO;
import com.suho.web.service.MemberService;
import com.suho.web.util.AuthInfo;
@Controller
@RequestMapping("/member/**")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	// 로그확인을 위한 구문
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	// 로그인 페이지 이동
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login(Model model, 
			@CookieValue( value = "loginCookie", required = false) Cookie cookie,
			HttpSession session) {
		
		logger.info("MemberController login GET 진입");
		
		
		LoginDTO loginDTO = new LoginDTO();
		
		// 브라우저에 저장된 cookie 체크
		if(cookie != null) {
			loginDTO.setId(cookie.getValue());
			loginDTO.setReid(true);
		}
		
		model.addAttribute("loginDTO", loginDTO);
		
		logger.info("MemberController login GET 탈출");
		
	}
	
	// 로그인 처리
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(
			@Valid LoginDTO loginDTO, 
			BindingResult bindingResult,
			Model model) throws Exception{
		
		logger.info("MemberController login POST 진입");
		
		// 사용자에게 입력받은 객체가 유효성 검사가 실패할때
		if(bindingResult.hasErrors()) {
			
			logger.info("loginDTO 객체 유효성 검사 실패");
			model.addAttribute("valid_check", "fail");
			return;
		}
		
		
		// db에서 조회된 아이디 패스워드와 현재 입력 받은 로그인 정보가 일치하는지 체크
		boolean db_check = memberService.loginCheck(loginDTO);
		
		// ID가 DB에서 조회되지 않을 경우 view에 전달할 result값을 model에 저장
		if(!db_check) {
			
			model.addAttribute("db_check", "fail");
			return;
			
		}
		
		logger.info("MemberController login POST 탈출");
		
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, Model model) {
		
		// 세션에 있는 모든 데이터 제거
		session.removeAttribute("loginUser");
		session.invalidate();
		
		return "redirect:/";
	}
	
	
	// 회원가입 페이지 이동
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Model model) {
		
		model.addAttribute("memberVO", new MemberVO());
		return "member/join";
		
	}
	
	// 회원가입 처리
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@Valid MemberVO memberVO, 
			BindingResult bindingResult,
			RedirectAttributes rttr) throws Exception {

		
		logger.info("/join post 요청");
		
		if(bindingResult.hasErrors()) {
			
			logger.info("MemberVO 객체 유효성 검사 실패");
			return "/member/join";
			
		} else {
			
			memberService.create(memberVO);
			
			logger.info("서비스 처리 완료(회원 등록완료)");
			
			rttr.addFlashAttribute("join_result", "join_success");
			return "redirect:/";
			
		}
		
	}
	
	// 아이디 중복 확인
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	@ResponseBody
	public int idCehck(@RequestParam("id") String userid) throws Exception {
		
		logger.info("/idCheck 요청");
		
		int result = memberService.idCheck(userid);
		
		logger.info("/idCheck 요청 처리 완료!");
		
		return result;
	}
	
	// 회원정보 수정페이지 이동
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(HttpSession session, Model model) throws Exception {
		
		AuthInfo auth = (AuthInfo)session.getAttribute("loginUser");
		String id = auth.getId();
		
		MemberVO memberVO = memberService.edit(id);
		
		model.addAttribute("memberVO", memberVO);
		
		return "/member/edit";
	}
	
	// 비밀번호 수정페이지 이동
	@RequestMapping(value = "/edit/chpass", method = RequestMethod.GET)
	public String editChPass() throws Exception {
		
		return "/member/chpass";
	}
	
	// 아이디 수정페이지 이동
	@RequestMapping(value = "/edit/chid/{userid}", method = RequestMethod.GET)
	public String editChId(@PathVariable("userid") String userid, Model model) throws Exception {

		model.addAttribute("userid", userid);
		
		return "/member/chid";
	}
	
}
