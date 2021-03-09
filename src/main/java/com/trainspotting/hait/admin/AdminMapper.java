package com.trainspotting.hait.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.trainspotting.hait.model.ApplicationDTO;
import com.trainspotting.hait.model.ApplicationEntity;

@Mapper
public interface AdminMapper {
	List<ApplicationDTO> listAll();
	List<ApplicationDTO> listStatus(int p);
	ApplicationDTO detail(ApplicationEntity p);
}
