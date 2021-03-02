package com.suho.web.dao;

import java.util.List;

import com.suho.web.domain.MemberVO;

public interface BoardDao {
	
	public List<MemberVO> listAll() throws Exception; 

}
