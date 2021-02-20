package com.suho.web.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suho.web.domain.MemberVO;


@Repository
public class MemberDaoImpl implements MemberDao {
	
	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public void create(MemberVO vo){
		sqlSessionTemplate.insert("create", vo);
	}

	@Override
	public List<MemberVO> listAll(){
		return sqlSessionTemplate.selectList("list");
	}

	
	
	
}
