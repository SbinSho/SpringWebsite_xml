package com.suho.web.service;

import java.util.List;

import com.suho.web.domain.MemberVO;

public interface BoardService {
	
	// 회원 목록 조회
	public List<MemberVO> listAll() throws Exception;

}
