package com.cafe24.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.vo.UserVo;

@Controller
public class MainController {
	
	@RequestMapping( {"/", "/main"} )
	public String main() {
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping("/hello")
	public String Hello() {
		return "<h1>안녕하세요!</h1>";
	}
	
	@ResponseBody
	@RequestMapping("/hello2")
	public UserVo Hello2() {
		UserVo vo = new UserVo();
		vo.setNo(10L);
		vo.setName("서장규");
		vo.setEmail("jooyg62@naver.com");
		return vo;
	}
}
