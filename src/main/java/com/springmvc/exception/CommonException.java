package com.springmvc.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice //전역예외처리 

//모든 컨트롤러의 예외처리
public class CommonException {  
	@ExceptionHandler(RuntimeException.class) //예외처리 클래스 설정 
	
	//컨트롤러에서 바생되는 예외처리 메서드
	private ModelAndView handleErrorCommon(Exception e) {
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.addObject("exception", e);
	modelAndView.setViewName("errorCommon");
	return modelAndView;
	}
}
