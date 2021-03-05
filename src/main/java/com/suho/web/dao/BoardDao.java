package com.suho.web.dao;

import java.util.List;
import java.util.Map;

import com.suho.web.domain.Criteria;
import com.suho.web.domain.MemberVO;

public interface BoardDao {
	
	// 회원목록 조회
	public List<MemberVO> listAll() throws Exception; 
	
	// 게시글수 조회
	public int BoardCount() throws Exception;

	// 게시글 LIMIT 조회
	public List<MemberVO> selectBoardList(Criteria cri) throws Exception;
	
}
