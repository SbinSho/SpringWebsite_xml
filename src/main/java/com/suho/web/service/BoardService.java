package com.suho.web.service;

import java.util.List;
import java.util.Map;

import com.suho.web.domain.Criteria;
import com.suho.web.domain.MemberVO;

public interface BoardService {
	
	// 회원 목록 조회
	public List<MemberVO> listAll() throws Exception;
	
	// 게시판 게시글수 조회
	public int BoardCount() throws Exception;
	
	// 게시글 LIMIT 조회
	public List<MemberVO> selectBoardList(Criteria cri) throws Exception;

	// 회원 조회
	public MemberVO select(String userid) throws Exception;
	
}
