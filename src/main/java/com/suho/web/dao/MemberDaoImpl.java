package com.suho.web.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suho.web.domain.MemberVO;
import com.suho.web.dto.LoginDTO;
import com.suho.web.dto.MemberIdDTO;


@Repository
public class MemberDaoImpl implements MemberDao {
	
	private static final String NAMESPACE = "com.suho.mapper.MemberMapper";
	
	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) throws Exception {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public void create(MemberVO vo) throws Exception {
		sqlSessionTemplate.insert(NAMESPACE + ".create", vo);
	}

	@Override
	public List<MemberVO> listAll() throws Exception{
		return sqlSessionTemplate.selectList(NAMESPACE + ".list");
	}

	@Override
	public int idCheck(String userid) throws Exception {
		return sqlSessionTemplate.selectOne(NAMESPACE + ".idCheck", userid);
	}

	@Override
	public MemberVO loginCheck(LoginDTO loginCommand) throws Exception {
		return sqlSessionTemplate.selectOne(NAMESPACE + ".loginCheck", loginCommand);
	}

	@Override
	public MemberVO select( String id ) throws Exception {
		return sqlSessionTemplate.selectOne(NAMESPACE + ".select", id);
	}

	@Override
	public int eidt_id(MemberIdDTO memberIdDTO) throws Exception {
		return sqlSessionTemplate.update(NAMESPACE + ".idUpDate" , memberIdDTO);
	}
	
	
	
}
