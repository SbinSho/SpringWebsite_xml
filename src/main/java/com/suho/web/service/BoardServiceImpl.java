package com.suho.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suho.web.dao.BoardDao;
import com.suho.web.domain.MemberVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao bDao;
	
	
	@Override
	public List<MemberVO> listAll() throws Exception {
		
		return bDao.listAll();
		
	}

	
	
	
}
