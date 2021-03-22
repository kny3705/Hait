package com.trainspotting.hait.owner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.trainspotting.hait.ResponseBody;
import com.trainspotting.hait.model.OwnerEntity;
import com.trainspotting.hait.model.ReservEntity;
import com.trainspotting.hait.model.RstrntDTO;
import com.trainspotting.hait.model.RstrntEntity;

@RestController
@RequestMapping("/api/owner")
class OwnerController {

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpSession session;

	@Autowired
	private OwnerService service;

	@PostMapping("/login")
	public ResponseEntity<ResponseBody> login(@RequestBody OwnerEntity p) {
		addTokenCookie(service.login(p));
		return new ResponseEntity<>(
				new ResponseBody(200, "LOGIN_SUCCESS", null),
				HttpStatus.OK
				);
	}

	@GetMapping("/logout")
	public ResponseEntity<ResponseBody> logout() {
		session.removeAttribute("r_pk");
		addTokenCookie(null);
		return new ResponseEntity<>(
				new ResponseBody(200, "LOGOUT_SUCCESS", null),
				HttpStatus.OK
				);
	}
	
	@PutMapping("/owner")
	public ResponseEntity<ResponseBody> updOwner(OwnerEntity p) {
		// TODO service.updOwner(p)
		// TODO password encoding
		return null;
	}
	
	@GetMapping("/restaurant/init")
	public ResponseEntity<ResponseBody> restaurantInitialInfo() {
		int pk = (int) session.getAttribute("r_pk");
		return new ResponseEntity<>(
				new ResponseBody(200, null, service.selRstrnt(pk)),
				HttpStatus.OK
				);
	}
	
	@PutMapping("/restaurant/init")
	public ResponseEntity<ResponseBody> initialSetting(MultipartFile file, RstrntDTO dto, HttpServletRequest request) {
		System.out.println(dto);
		dto.setPk((int) session.getAttribute("r_pk"));
		addTokenCookie(service.initialSetting(file, dto, getToken(request))); 
		return new ResponseEntity<>(
				new ResponseBody(200, null, null),
				HttpStatus.OK
				);
	}
	
	@GetMapping("/restaurant")
	public ResponseEntity<ResponseBody> restaurantInfo() {
		int pk = (int) session.getAttribute("r_pk");
		return new ResponseEntity<>(
				new ResponseBody(200, null, service.selRstrnt(pk)),
				HttpStatus.OK
				);
	}
	
	@PutMapping("/restaurant")
	public ResponseEntity<ResponseBody> updRstrntInfo(MultipartFile file, RstrntEntity p) {
		int pk = (int) session.getAttribute("r_pk");
		p.setPk(pk);
		//TODO service.updRstrnt(file, p)
		return null;
	}
	
	@PutMapping("/restaurant/state")
	public ResponseEntity<ResponseBody> updRstrntState(@RequestBody RstrntEntity p) {
		p.setPk((int) session.getAttribute("r_pk"));
		String message = service.updRstrntState(p);
		return new ResponseEntity<>(
				new ResponseBody(200, message, null),
				HttpStatus.OK
				);
	}
	
	// reservation list
	@GetMapping("/reservations")
	public ResponseEntity<ResponseBody> reservList() {
		int pk = (int) session.getAttribute("r_pk");
		return new ResponseEntity<>(
				new ResponseBody(200, null, service.reservList(pk)),
				HttpStatus.OK
				);
	}

	private void addTokenCookie(String token) {
		Cookie cookie = new Cookie("owner_token", token);
		cookie.setPath("/");
		cookie.setSecure(true);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(token == null ? 0 : (24 * 60 * 60));
		response.addCookie(cookie);
	}
	
	private String getToken(HttpServletRequest request) {
		String token = null;
		for(Cookie cookie : request.getCookies()) {
			if("owner_token".equals(cookie.getName())) {
				token = cookie.getValue();
			}
		}
		return token;
	}
}
