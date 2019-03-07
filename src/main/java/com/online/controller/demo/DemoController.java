package com.online.controller.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class DemoController {

	@RequestMapping("/tab/list")
	public String tabs(){
		return "/demo/tab/list";
	}
	
	@RequestMapping("/grid/list")
	public String grid(){
		return "/demo/grid/list";
	}
	
	@RequestMapping("/select/list")
	public String select(){
		return "/demo/select/list";
	}
}
