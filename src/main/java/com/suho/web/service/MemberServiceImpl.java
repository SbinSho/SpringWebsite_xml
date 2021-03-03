package com.suho.web.service;


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

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDao mDao;
	
	// 로그확인을 위한 구문
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	// 사용자 비밀번호 암호화 위한 구문 spring.security... 의 BCryptPasswordEncoder를 사용
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public void create(MemberVO vo) throws Exception{
		
		// 사용자에게 입력받은 vo 에서 비밀번호 추출
		String rawPassword = vo.getUserpwd();
		
		// 암호화된 비밀번호로 변경
		vo.setUserpwd(passwordEncoder.encode(rawPassword));
		
		
		logger.info("비밀번호 암호화 처리 완료");
		logger.info("암호화된 비밀번호 : "+ vo.getUserpwd());
		
		mDao.create(vo);
		
	}
	
	// 아이디 중복 확인
	@Override
	public int idCheck(String userid) throws Exception {
		return mDao.idCheck(userid);
	}

	
	// 로그인 확인을 위한 구문
	@Override
	public boolean loginCheck(LoginDTO loginDTO) throws Exception {
		
		// 반환할 값
		boolean check = false;
		
		// 사용자에게 입력 받은 비밀번호
		String rawPassword = loginDTO.getPwd();
		
		// DB에서 현재 입력된 id에 해당하는 정보를 가져와서 MemeberVO 객체에 담음
		MemberVO memberVO = mDao.loginCheck(loginDTO); 
		
		if(memberVO != null) {
			// DB에서 가져온 인코딩된 비밀번호
			String encodedPassword = memberVO.getUserpwd();
			// DB에서 가져온 비밀번호와 사용자에게 입력받은 비밀번호가 일치하는지 체크
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
		
		// front에서 예외상황으로 중복된 아이디가 넘어왔을때 DB에서 한번더 체크하기 위한 구문
		MemberVO memberVO = mDao.select(memberIdDTO.getCh_userid());
		
		// 입력된 아이디가 DB에 존재하지 않을시
		if(memberVO == null) {
			// 회원 아이디를 수정
			return mDao.edit_id(memberIdDTO);
		}
		
		return 0;
		
	}


	// 회원 비밀번호 변경
	@Override
	public int edit_pass(MemberPassDTO memberPassDTO) throws Exception {
		
		// controller에서 처리결과를 확인하기 위한 구만
		int result = 0;
		
		// DB에서 현재 입력된 아이디의 비밀번호를 가져옴
		MemberVO memberVO = mDao.select(memberPassDTO.getUserid());
		
		// DB에서 입력된 비밀번호와 사용자에게 입력된 비밀번호 일치여부 확인
		if(passwordEncoder.matches(memberPassDTO.getUserpwd(), memberVO.getUserpwd())){
			
			// 현재 사용자에게 입력받은 비밀번호를 암호화처리
			String encodingPass = passwordEncoder.encode(memberPassDTO.getCh_userpwd());
			// 암호화 처리된 비밀번호로 비밀번호 변경
			memberPassDTO.setCh_userpwd(encodingPass);
			
			// DB에서 처리된 result 값을 반환
			result = mDao.edit_pass(memberPassDTO);
		}
		
		return result;
	}

	

	
	
	
	
	
	
}
