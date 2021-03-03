package com.suho.web.dao;

import java.util.List;

import com.suho.web.domain.MemberVO;

public interface BoardDao {
	
	// 회원목록 조회
	public List<MemberVO> listAll() throws Exception; 

}
