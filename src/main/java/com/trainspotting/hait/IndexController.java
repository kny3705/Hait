package com.trainspotting.hait;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping("/errors")
	public String error() {
		return "index";
	}
	
}
