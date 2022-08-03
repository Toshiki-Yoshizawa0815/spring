package jp.co.internous.myfragment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fragmentsample")
public class IndexController {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}

}
