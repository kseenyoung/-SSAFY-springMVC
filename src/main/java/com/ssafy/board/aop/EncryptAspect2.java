package com.ssafy.board.aop;

import java.sql.SQLException;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.ssafy.member.model.MemberDto;

@Component
@Aspect
public class EncryptAspect2 {

	private Logger logger = LoggerFactory.getLogger(EncryptAspect2.class);

	// public int joinMember(MemberDto memberDto)
	@Before(value = "execution(* com.ssafy.member.model.dao.MemberDaoImpl.joinMember(..))")
	public void memberNameEncrypt(JoinPoint joinPoint) {
		logger.debug("before call method : {} ", joinPoint.getSignature());
		logger.debug("메서드 선언부 : {} 전달 파라미터 : {}", joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()));
		MemberDto memberDto = (MemberDto) joinPoint.getArgs()[0];
		logger.debug("여기 {} 암호화 필요 ...", memberDto.getUserName());
		
		// TODO : 암호화 코드 작성 
		// Lib 사용 or 코드 paste
	}

}