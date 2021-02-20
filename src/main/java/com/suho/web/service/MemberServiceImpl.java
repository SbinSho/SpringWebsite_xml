package com.suho.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suho.web.dao.MemberDao;
import com.suho.web.domain.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDao mDao;

	
	@Override
	public void create(MemberVO vo){
		mDao.create(vo);
	}

	
	@Override
	public List<MemberVO> listAll(){
		return mDao.listAll();
	}

	
	
	
}
