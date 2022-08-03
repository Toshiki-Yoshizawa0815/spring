package jp.co.internous.mysession.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.internous.mysession.model.LoginSession;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private LoginSession loginSession;
	
	@RequestMapping("/")
	public String hello(Model model) {
		model.addAttribute("loginSession", loginSession);
		
		return "test";
	}

}
