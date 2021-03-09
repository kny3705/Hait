package com.trainspotting.hait.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainspotting.hait.model.ApplicationDTO;
import com.trainspotting.hait.model.ApplicationEntity;

@Service
public class AdminService {
	
	@Autowired
	private AdminMapper mapper;
	
	public List<ApplicationDTO> listAll() {
		return mapper.listAll();
	}
	
	public List<ApplicationDTO> listStatus(int p) {
		return mapper.listStatus(p);
	}
	
	public ApplicationDTO detail(ApplicationEntity p) {
		return mapper.detail(p);
	}

}
