package com.suho.web.dao;

import java.util.List;

import com.suho.web.domain.MemberVO;

public interface MemberDao {

	public void create( MemberVO vo );
	
	public List<MemberVO> listAll();
	
	
}
