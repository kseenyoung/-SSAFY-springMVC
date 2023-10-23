package com.ssafy.member.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.member.model.MemberDto;

@Service
public interface MemberService {

	int idCheck(String userId) throws Exception;
	int joinMember(MemberDto memberDto) throws Exception;
	MemberDto loginMember(String userId, String userPwd) throws Exception;
	
}
