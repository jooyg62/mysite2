package com.cafe24.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.security.Auth;
import com.cafe24.mysite.vo.SiteVo;

@Auth(role=Auth.Role.ADMIN)
@RequestMapping("/admin")
@Controller
public class AdminController {
	
	@RequestMapping({"","/main"})
	public String main(Model model) {
		SiteVo siteVo = new SiteVo();
		model.addAttribute(siteVo);
		
		return "admin/main";
	}
	
	@RequestMapping(value="/main/update", method=RequestMethod.POST)
	public String mainUpdate() {
		return "admin/main";
	}
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
}
