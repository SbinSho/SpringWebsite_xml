package com.suho.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suho.web.dao.BoardDao;
import com.suho.web.domain.Criteria;
import com.suho.web.domain.MemberVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao bDao;
	
	
	// 회원 목록 조회
	@Override
	public List<MemberVO> listAll() throws Exception {
		
		return bDao.listAll();
		
	}


	@Override
	public int BoardCount() throws Exception {
		return bDao.BoardCount();
	}


	@Override
	public List<MemberVO> selectBoardList(Criteria cri) throws Exception {
		return bDao.selectBoardList(cri);
	}


	@Override
	public MemberVO select(String userid) throws Exception {
		return bDao.select(userid);
	}

	
	
	
}
