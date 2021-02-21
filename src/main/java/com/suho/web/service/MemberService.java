package com.suho.web.service;

import java.util.List;

import com.suho.web.domain.MemberVO;

public interface MemberService {

	// 회원가입
	public void create(MemberVO vo) throws Exception;
	
	// 모든 회원 조회
	public List<MemberVO> listAll() throws Exception;
	
	// 아이디 중복 확인
	public int idCheck(String userid) throws Exception;
	
	
}
