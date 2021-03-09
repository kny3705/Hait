package com.trainspotting.hait.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainspotting.hait.model.CustomerDTO;
import com.trainspotting.hait.model.CustomerEntity;
import com.trainspotting.hait.model.ReservEntity;
import com.trainspotting.hait.model.RstrntDTO;
import com.trainspotting.hait.model.RstrntEntity;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerMapper mapper;
	
	public int insCustomer(CustomerDTO param) {
		ReservEntity data = new ReservEntity();
		CustomerEntity customer = new CustomerEntity();
		customer.setContact(param.getContact());
		data.setRstrnt_pk(param.getPk());
		
		CustomerEntity result = mapper.selCustomer(customer);
		
		if(result == null) {
			mapper.insCustomer(param);
			data.setCustomer_pk(param.getPk());
		} else {
			data.setCustomer_pk(result.getPk());
		}
		
		data.setHeadcount(param.getHeadcount());
		mapper.insReserv(data);
		
		if(result == null) {
			return param.getPk();
		} else {
			return result.getPk();
		}
	}

	public List<RstrntEntity> selRstrntList(RstrntEntity param) {
		return mapper.selRstrntList(param);
	}

	public RstrntDTO selDetail(RstrntDTO param) {
		return mapper.selDetail(param);
	}
	
	public Map<String, Object> selResult(RstrntDTO param) {
		Map<String, Object> json = new HashMap<>();
		RstrntDTO data = mapper.selResult(param);
		
		json.put("result", data);
		json.put("count", mapper.selCount(data));
		
		return json;
	}
	
}
