package com.suho.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suho.web.domain.MemberVO;
import com.suho.web.service.BoardService;

@Controller
@RequestMapping("/board/**")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	
	@RequestMapping("/home")
	public String boardHome(Model model) throws Exception {
		
		List<MemberVO> list = boardService.listAll();
		
		model.addAttribute("list", list);
		
		return "/board/home";
	}
	
}
