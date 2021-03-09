package com.trainspotting.hait.customer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trainspotting.hait.model.CustomerDTO;
import com.trainspotting.hait.model.ReservEntity;
import com.trainspotting.hait.model.RstrntDTO;
import com.trainspotting.hait.model.RstrntEntity;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@GetMapping("/search")
	public Map<String, Object> selRstrnt(RstrntEntity param) {
		Map<String, Object> json = new HashMap<>();
		json.put("restaurants", service.selRstrntList(param));
		
		return json;
	}
	
	@PostMapping("/search")
	public Map<String, Object> selRstrntSearch(RstrntEntity param) {
		Map<String, Object> json = new HashMap<>();
		json.put("restaurants", service.selRstrntList(param));
		
		return json;
	}
	
	//식당 pk
	@GetMapping("/detail/{r_pk}")
	public Map<String, Object> selDetail(RstrntDTO param) {
		Map<String, Object> json = new HashMap<>();
		json.put("restaurant", service.selDetail(param));
		
		return json;
	}
	
	// r_pk
	@GetMapping("/reservation/{pk}")
	public Map<String, Object> selReserv(RstrntDTO param) {
		Map<String, Object> json = new HashMap<>();
		json.put("restaurant", service.selDetail(param));
		
		return json;
	}
	
	// r_pk, contact, headcount
	@PostMapping("/reservation")
	public String insCustomer(CustomerDTO param) {
		int result = service.insCustomer(param);
		
		return "redirect:/result/" + result;
	}
	
	// customerPK
	@GetMapping("/result/{c_pk}")
	public Map<String, Object> selResult(RstrntDTO param) {
		
		return service.selResult(param);
	}
	
}
