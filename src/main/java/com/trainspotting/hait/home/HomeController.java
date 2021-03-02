package com.trainspotting.hait.home;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trainspotting.hait.model.ApplicationEntity;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class HomeController {
	
	@Autowired
	private HomeService service;
	
	@GetMapping("/cities")
	public Map<String, Object> select() {
		Map<String, Object> json = new HashMap<>();
		json.put("cities", service.select());
		
		return json;
	}
	
	@PostMapping("/applications")
	public int insert(@RequestBody ApplicationEntity p) {
		return service.insert(p);
	}
}
