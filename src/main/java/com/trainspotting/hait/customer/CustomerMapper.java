package com.trainspotting.hait.customer;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.trainspotting.hait.model.CustomerDTO;
import com.trainspotting.hait.model.CustomerEntity;
import com.trainspotting.hait.model.ReservEntity;
import com.trainspotting.hait.model.RstrntDTO;
import com.trainspotting.hait.model.RstrntEntity;

@Mapper
public interface CustomerMapper {
	int insCustomer(CustomerDTO param);
	int insReserv(ReservEntity param);
	CustomerEntity selCustomer(CustomerEntity param);
	List<RstrntEntity> selRstrntList(RstrntEntity param);
	RstrntDTO selDetail(RstrntDTO param);
	RstrntDTO selResult(RstrntDTO param);
	int selCount(RstrntDTO param);
	
}
