package com.suho.web.service;

import java.util.List;

import com.suho.web.domain.MemberVO;

public interface MemberService {

	public abstract void create(MemberVO vo);
	
	public abstract List<MemberVO> listAll();
	
	
}
