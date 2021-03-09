package com.trainspotting.hait.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;
import com.trainspotting.hait.model.ApplicationEntity;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	@GetMapping("/applications")
	public Map<String, Object> list(@RequestParam(required = false, defaultValue = "") String process_status) {
		System.out.println(process_status.equals(""));
		Map<String, Object> json = new HashMap<>();
		switch(process_status) {
		case "":
			json.put("data", service.listAll());
			return json;
		default:
			json.put("data", service.listStatus(Integer.parseInt(process_status)));
			return json;
		}
	}
	
	@GetMapping("/applications/{pk}")
	public Map<String, Object> detail(ApplicationEntity p) {
		System.out.println(p.getPk());
		Map<String, Object> json = new HashMap<>();
		json.put("data", service.detail(p));
		
		return json;
	}
}
