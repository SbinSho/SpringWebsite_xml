package com.suho.web.service;

import java.util.List;

import com.suho.web.domain.MemberVO;

public interface BoardService {
	
	public List<MemberVO> listAll() throws Exception;

}
