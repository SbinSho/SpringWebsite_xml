package com.suho.web.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suho.web.domain.MemberVO;
import com.suho.web.dto.LoginDTO;


@Repository
public class MemberDaoImpl implements MemberDao {
	
	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) throws Exception {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public void create(MemberVO vo) throws Exception {
		sqlSessionTemplate.insert("create", vo);
	}

	@Override
	public List<MemberVO> listAll() throws Exception{
		return sqlSessionTemplate.selectList("list");
	}

	@Override
	public int idCheck(String userid) throws Exception {
		return sqlSessionTemplate.selectOne("idCheck", userid);
	}

	@Override
	public MemberVO loginCheck(LoginDTO loginCommand) throws Exception {
		return sqlSessionTemplate.selectOne("loginCheck", loginCommand);
	}
	
	
	
}
