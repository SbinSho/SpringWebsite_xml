package com.suho.web.controller;


import javax.servlet.http.Cookie;
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
import com.suho.web.dto.MemberIdDTO;
import com.suho.web.dto.MemberPassDTO;
import com.suho.web.service.MemberService;
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
		
		// 사용자에게 입력받은 데이터를 전달할 loginDTO
		LoginDTO loginDTO = new LoginDTO();
		
		// 브라우저에 저장된 cookie 체크
		if(cookie != null) {
			// 브라우저에 저장된 cookie가 존재하면 cookie의 내용을 가져와서 loginDTO id값으로 지정
			loginDTO.setId(cookie.getValue());
			// 쿠키값을 유지
			loginDTO.setReid(true);
		}
		
		// /member/login.jsp(view) 에서 form:form 구문에 들어갈 객체인 loginDTO 전달
		model.addAttribute("loginDTO", loginDTO);
		
		
	}
	
	// 로그인 처리
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(
			@Valid LoginDTO loginDTO, 
			BindingResult bindingResult,
			Model model) throws Exception{
		
		
		// 사용자에게 입력받은 객체가 유효성 검사가 실패할때
		if(bindingResult.hasErrors()) {
			
			logger.info("loginDTO 객체 유효성 검사 실패");
			// interceptor의 post핸들러에 전달할 목적인 데이터
			model.addAttribute("valid_check", "fail");
			
			return;
		}
		
		
		// db에서 조회된 아이디 패스워드와 현재 입력 받은 로그인 정보가 일치하는지 체크
		boolean db_check = memberService.loginCheck(loginDTO);
		
		// ID가 DB에서 조회되지 않을 경우 view에 전달할 result값을 model에 저장
		if(!db_check) {
			
			// interceptor의 post핸들러에 전달할 목적인 데이터
			model.addAttribute("db_check", "fail");
			// 아이디 또는 비밀번호가 일치하지 않을시에 view 페이지에 전달할 값
			model.addAttribute("result", "error");
			return;
			
		}
	}
	
	//로그아웃 처리
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, Model model) {
		
		// 세션에 저장되어 login 유저 제거
		session.removeAttribute("loginUser");
		// 세션에 저장되어 모든 데이터 제거
		session.invalidate();
		
		return "redirect:/";
	}
	
	
	// 회원가입 페이지 이동
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Model model) {
		
		// /member/join.jsp(view) 페이지에서 사용할 MemberVO 객체 전달
		model.addAttribute("memberVO", new MemberVO());
		
		return "member/join";
		
	}
	
	// 회원가입 처리
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@Valid MemberVO memberVO, 
			BindingResult bindingResult,
			RedirectAttributes rttr) throws Exception {

		// 객체의 유효성 검증
		// front에서 javascirpt를 사용하여 객체를 1차적으로 검증했지만 실습 목적으로 자바 객체검증을 이용함
		if(bindingResult.hasErrors()) {
			
			logger.info("MemberVO 객체 유효성 검사 실패");
			// 객체 유효성 검증 실패시 재입력을 위해 join.jsp로 이동
			return "/member/join";
			
		} else { // 객체 유효성 검증 통과시
			
			// id중복 체크는 front에서 처리함
			memberService.create(memberVO);
			
			logger.info("서비스 처리 완료(회원 등록완료)");
			
			// 회원가입 후 메인페이지로 돌아가서 사용할 일회성 데이터 사용을 위한 구문
			rttr.addFlashAttribute("join_result", "join_success");
			
			return "redirect:/";
			
		}
		
	}
	
	// 아이디 중복 확인
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	@ResponseBody
	public int idCehck(@RequestParam("id") String userid) throws Exception {
		
		logger.info("/idCheck 요청");
		
		// 아이디 중복시 result값은 1임 ID는 기본키(primary key)이기 때문에 중복된 값이 발생할수 없음
		int result = memberService.idCheck(userid);
		
		logger.info("/idCheck 요청 처리 완료!");
		
		return result;
	}
	
	// 회원정보 수정페이지 이동
	@RequestMapping(value = "/edit/{userid}", method = RequestMethod.GET)
	public String edit(@PathVariable("userid") String userid, Model model) throws Exception {
		
		// 회원가입 페이지에서 사용할 사용자 정보를 DB에서 가져옴
		MemberVO memberVO = memberService.select(userid);
		
		// /member/edit 에서 사용할 memberVO 객체를 model에 담아서 전달
		model.addAttribute("memberVO", memberVO);
		
		return "/member/edit";
	}
	
	
	// 아이디 수정페이지 이동
	@RequestMapping(value = "/edit/chid/{userid}", method = RequestMethod.GET)
	public String editChId(@PathVariable("userid") String userid, Model model) throws Exception {
		
		// 사용자에게 입력 받기 위한 객체 선언
		MemberIdDTO memberIdDTO = new MemberIdDTO();
		// 현재 요청한 userid 데이터 담음
		memberIdDTO.setUserid(userid);
		
		// view페이지에 데이터 전달
		model.addAttribute("memberIdDTO", memberIdDTO);
		
		return "/member/chid";
	}
	
	// 아이디 수정
	@RequestMapping(value = "/edit/chid/{userid}", method = RequestMethod.POST)
	public String editChId(	MemberIdDTO memberIdDTO, Model model ) throws Exception {
		
		// update가 완료되면 result값은 1이 반한됨 반환되는 값을 확인하기 위한 구문
		int result = memberService.edit_id(memberIdDTO);
	
		// front에서 중복된 아이디 체크 확인 후 전달해서 result가 0이 나올수는 없지만 예기치 못한 상황에서 
		// 검증되지 않은 데이터 값이 전달될때 체크하기 위한 구문
		if( result == 0) {
			
			// editOK.jsp에서 사용할 데이터
			model.addAttribute("result", "error");
			
			return "/member/chid";
		}
		
		
		logger.info("회원정보 수정 완료 " );
		
		// editOK.jsp 에서 사용할 데이터
		model.addAttribute("result", "OK");
		model.addAttribute("edit","아이디");
		
		// 수정 성공시 로그아웃을 위한 페이지 따로 작성함
		// ediOK.jsp는 성공시 redirect로 /member/logout 요청을 보냄
		// 사용자에게 로그아웃 후 다시 재로그인을 육안으로 확인을 편하게 하기 위해 만듬 ( javascript의 alert를 사용하기위해 )
		// 더좋은 방법 찾게 되면 바꿀 예정
		return "/member/editOK";
	}
	
	// 비밀번호 수정페이지 이동
	@RequestMapping(value = "/edit/chpass/{userid}", method = RequestMethod.GET)
	public String editChPass(@PathVariable("userid") String userid, Model model) throws Exception {
		
		// 아이디 변경을 위한 DTO 객체 선언 및 userid 데이터 입력
		MemberPassDTO memberPassDTO = new MemberPassDTO();
		memberPassDTO.setUserid(userid);
		
		// view 페이지에 전달할 DTO 객체 model에 담음
		model.addAttribute("memberPassDTO", memberPassDTO);
		
		return "/member/chpass";
	}
	
	// 비밀번호 수정
	@RequestMapping(value = "/edit/chpass/{userid}", method = RequestMethod.POST)
	public String editChPass( @PathVariable("userid") String userid, MemberPassDTO memberPassDTO, Model model) throws Exception {
		
		
		// 위의 아이디 변경과 작업이 같음 다만 차이는 ID 변경 같은 경우는 중복된 값이 입력 될순없지만
		// 패스워드는 중복확인을 따로 거치지 않기 떄문에 유효성에 맞는 데이터만 들어오면 DB에 입력 가능
		// 현재는 front에서 정규식 검증을 하지만, 필요하면 hibernate를 사용하여 valid를 이용한 객체 정규식 검증도 가능할듯
		int result = memberService.edit_pass(memberPassDTO);
		
		if(result == 1) {
			logger.info("비밀번호 수정 완료");
			
			model.addAttribute("result", "OK");
			model.addAttribute("edit", "비밀번호");
			
			return "/member/editOK";
			
		}
		
		model.addAttribute("result", "error");
		
		return "/member/chpass";
	}
	
}
