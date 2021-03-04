package com.trainspotting.hait.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trainspotting.hait.model.ApplicationEntity;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
	
	@Autowired
	private ApplicationService service;
	
	@PutMapping("/{pk}")
	public int update(@RequestBody ApplicationEntity p) {
//		System.out.println(p.getOwner_email());
		return service.update(p);
	}
}
