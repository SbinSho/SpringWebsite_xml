package com.suho.web.dao;

import java.util.List;

import com.suho.web.domain.MemberVO;
import com.suho.web.dto.LoginDTO;

public interface MemberDao {

	// 회원가입
	public void create( MemberVO vo ) throws Exception;
	
	// 모든 회원 조회
	public List<MemberVO> listAll() throws Exception;
	
	// 아이디 중복 확인
	public int idCheck(String userid) throws Exception;
	
	// 로그인 처리
	public MemberVO loginCheck(LoginDTO loginCommand) throws Exception;
	
}
