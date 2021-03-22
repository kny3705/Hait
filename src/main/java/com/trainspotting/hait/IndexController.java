package com.trainspotting.hait;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = {"/home/**", "/owner/**", "/customer/**", "/admin/**"}, method = RequestMethod.GET)
public class IndexController {
	
	@GetMapping
	public String index() {
		System.out.println("index controller!");
		return "index";
	}
}
