package com.online.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.online.entity.online.Section;

@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/abc")
   public void test(Section section){
		System.out.println(section);
   }
}
