package com.suho.web.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suho.web.domain.MemberVO;
import com.suho.web.service.MemberService;
@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	// 로그확인을 위한 구문
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	// 로그인 페이지 이동
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGET() {
		return "member/login";
	}
	
	// 로그인 처리
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPOST() {
		return "member/login";
	}
	
	// 회원가입 페이지 이동
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Model model) {
		model.addAttribute("memberVO", new MemberVO());
		
		return "member/join";
	}
	
	// 회원가입 처리
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@Valid MemberVO memberVO, BindingResult bindingResult) throws Exception {
		
		if(bindingResult.hasErrors()) {
			return "/member/join";
		} else {
			
			logger.info("/join post 요청");
			logger.info("전달 데이터 : " + memberVO);
			
			memberService.create(memberVO);
			
			logger.info("서비스 처리 완료(회원 등록완료)");
			
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
	
	
}
