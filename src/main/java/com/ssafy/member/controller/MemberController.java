package com.ssafy.member.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;

@Controller
@RequestMapping("/user")
public class MemberController {

	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	// 회원가입 페이지 이동
	@GetMapping("/join")
	public String join() {
		return "user/join";
	}
	
	// 찐 회원가입
	@PostMapping("/join")
	public String join(MemberDto memberDto) throws Exception { // Exception 던지면 ExceptionController가 잡음
//		logger.info(msg);
//		logger.warning(msg);
		
		logger.debug("join memberDto : {}", memberDto);
		memberService.joinMember(memberDto);
		return "redirect:/user/login";
	}
	
	// 로그인 페이지 이동
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("userid") String userId, @RequestParam("userpwd") String userPwd, HttpSession session) throws Exception {
		MemberDto memberDto = memberService.loginMember(userId, userPwd);
		if(memberDto != null) {
			session.setAttribute("userinfo", memberDto);
			return "redirect:/";
		}
		
		return "user/login";
	}
	
//	// Exception 처리 방법2 
//	// @ExceptionHandler : Controller에서만 사용 가능
//	@ExceptionHandler(Exception.class)
//	public Object err(Exception e) {
//		// 어쩌구 저쩌구
//	}
}
