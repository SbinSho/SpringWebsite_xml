package com.suho.web.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.suho.web.domain.MemberVO;
import com.suho.web.dto.LoginDTO;
import com.suho.web.dto.MemberIdDTO;
import com.suho.web.dto.MemberPassDTO;
import com.suho.web.util.AuthInfo;

public interface MemberService {

	// 회원가입
	public void create(MemberVO vo) throws Exception;
	
	// 아이디 중복 확인
	public int idCheck(String userid) throws Exception;
	
	// 로그인 처리
	public boolean loginCheck(LoginDTO loginCommand) throws Exception;
	
	// 회원정보 조회
	public MemberVO select(String id) throws Exception;
	
	// 회원 ID 변경
	public int edit_id( MemberIdDTO memberIdDTO) throws Exception;
	
	// 회원 비밀번호 변경
	public int edit_pass( MemberPassDTO memberPassDTO ) throws Exception;
}
