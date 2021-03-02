package com.suho.web.dao;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suho.web.domain.MemberVO;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	private static final String NAMESPACE = "com.suho.mapper.BoardMapper";
	
	
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Autowired
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	
	@Override
	public List<MemberVO> listAll() throws Exception {
		
		List<MemberVO> list = sqlSessionTemplate.selectList( NAMESPACE + ".listAll" ); 
		
		return list;
	}
	
	
}
