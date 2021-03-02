package com.trainspotting.hait.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainspotting.hait.model.ApplicationEntity;
import com.trainspotting.hait.model.CityEntity;

@Service
public class HomeService {
	
	@Autowired
	private HomeMapper mapper;
	
	public List<CityEntity> select() {
		return mapper.select();
	}
	
	public int insert(ApplicationEntity p) {
		return mapper.insert(p);
	}
}
