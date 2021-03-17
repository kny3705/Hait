package com.trainspotting.hait.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trainspotting.hait.ResponseBody;
import com.trainspotting.hait.jwt.JwtProvider;
import com.trainspotting.hait.model.AdminEntity;
import com.trainspotting.hait.model.ApplicationEntity;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@PostMapping("/login")
	public ResponseEntity<ResponseBody> login(@RequestBody AdminEntity param, HttpServletResponse response) {

		AdminEntity admin = service.login(param);
		String token = jwtProvider.provideToken(admin.getId(), "ADMIN").getToken();
		
		response.addCookie(new Cookie("token", token));
		
		ResponseBody body = new ResponseBody(200, "LOGIN_SUCCESS", null);
		return new ResponseEntity<>(body, null, HttpStatus.OK);
	}
	
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
