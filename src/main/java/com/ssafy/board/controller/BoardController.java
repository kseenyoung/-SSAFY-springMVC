package com.ssafy.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.board.model.service.BoardServiceImpl;
import com.ssafy.member.model.MemberDto;
import com.ssafy.util.PageNavigation;

@Controller
@RequestMapping("/article")
public class BoardController {

	private final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	private final BoardService boardService;

	public BoardController(BoardService boardService) {
		super();
		this.boardService = boardService;
	}
	
	@GetMapping("/write")
	public String write() {
		// login check는?? => Intercepter 로 해결 가능하다
		return "board/write";
	}
	
	@PostMapping("/write")
	public String write(BoardDto boardDto, HttpSession session, RedirectAttributes redirectAttributes, RedirectAttributes ra) throws Exception {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		boardDto.setUserId(memberDto.getUserId());
		System.out.println(boardDto);
		logger.debug("write boardDto : {}", boardDto);
		boardService.writeArticle(boardDto);
		// 정상 처리 됨
		// 방법1
//		return "redirect:/article/list?pgno=1&key=&word=";
		// 방법 2 => Map에 해당 내용들이 들어감
		ra.addAttribute("pgno", 1);
		ra.addAttribute("key", "");
		ra.addAttribute("word", "");
		return "redirect:/article/list";
	}
	
	@GetMapping("/list")
	public ModelAndView list(@RequestParam Map<String, String> map) throws Exception {  // 해당 Map은 Model 또는 Parameter 역할을 한다.
		List<BoardDto> list = boardService.listArticle(map);
		PageNavigation pageNavigation = boardService.makePageNavigation(map);
		ModelAndView mav = new ModelAndView();
		mav.addObject("articles", list);
		mav.addObject("navigation", pageNavigation);
		mav.addObject("pgno", map.get("pgno"));
		mav.addObject("key", map.get("key"));
		mav.addObject("word", map.get("word"));
		mav.setViewName("board/list");
		return mav;
	}
	
	
}
