package com.suho.web.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suho.web.domain.Criteria;
import com.suho.web.domain.MemberVO;
import com.suho.web.domain.PageMaker;
import com.suho.web.service.BoardService;

@Controller
@RequestMapping("/board/**")
public class BoardController { // 시간단축을 위해 개별의 board 테이블 대신 member 테이블의 데이터 사용
							   // 실습을 위해 BoardController로 이름을 지음
	@Autowired
	private BoardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	// member 테이블 전체 데이터 조회
	@RequestMapping("/listAll")
	public String boardHome(Criteria cri, Model model) throws Exception {

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(boardService.BoardCount());
		
		List<MemberVO> list = boardService.selectBoardList(cri);
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);
		
		return "/board/listAll";
		
	}
	
}
