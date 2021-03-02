package com.suho.web.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.suho.web.dao.MemberDao;
import com.suho.web.domain.MemberVO;
import com.suho.web.dto.LoginDTO;
import com.suho.web.dto.MemberIdDTO;
import com.suho.web.dto.MemberPassDTO;
import com.suho.web.util.AuthInfo;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDao mDao;
	// 로그확인을 위한 구문
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public void create(MemberVO vo) throws Exception{
		
		// 사용자에게 입력받은 vo 에서 패스워드 추출
		String rawPassword = vo.getUserpwd();
		
		// 암호화된 패스워드로 변경
		vo.setUserpwd(passwordEncoder.encode(rawPassword));
		
		
		logger.info("비밀번호 암호화 처리 완료");
		logger.info("암호화된 비밀번호 : "+ vo.getUserpwd());
		
		mDao.create(vo);
		
	}

	
	@Override
	public List<MemberVO> listAll() throws Exception{
		return mDao.listAll();
	}


	@Override
	public int idCheck(String userid) throws Exception {
		return mDao.idCheck(userid);
	}


	@Override
	public boolean loginCheck(LoginDTO loginDTO) throws Exception {
		
		logger.info("loginCheck Service 진입");
		
		// 반환할 값
		boolean check = false;
		
		// 사용자에게 입력 받은 비밀번호
		String rawPassword = loginDTO.getPwd();
		
		// DB에서 현재 입력된 id에 해당하는 정보를 가져와서 MemeberVO 객체에 담음
		MemberVO memberVO = mDao.loginCheck(loginDTO); 
		
		if(memberVO != null) {
			// DB에서 가져온 인코딩된 비밀번호
			String encodedPassword = memberVO.getUserpwd();
			if(passwordEncoder.matches(rawPassword, encodedPassword)) {
				check = true;
			}
			
		}
		
		return check;
		
	}

	// 회원정보 조회
	@Override
	public MemberVO select(String id) throws Exception {
		return mDao.select(id);
	}

	// 회원 ID 변경
	@Override
	public int edit_id(MemberIdDTO memberIdDTO) throws Exception {
		
		MemberVO memberVO = mDao.select(memberIdDTO.getCh_userid());
		
		if(memberVO == null) {
			return mDao.edit_id(memberIdDTO);
		}
		
		return 0;
		
	}


	// 회원 비밀번호 변경
	@Override
	public int edit_pass(MemberPassDTO memberPassDTO) throws Exception {
		
		int result = 0;
		MemberVO memberVO = mDao.select(memberPassDTO.getUserid());
		
		if(passwordEncoder.matches(memberPassDTO.getUserpwd(), memberVO.getUserpwd())){
			
			String encodingPass = passwordEncoder.encode(memberPassDTO.getCh_userpwd());
			memberPassDTO.setCh_userpwd(encodingPass);
			
			result = mDao.edit_pass(memberPassDTO);
		}
		
		return result;
	}

	

	
	
	
	
	
	
}
