package com.trainspotting.hait.home;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.trainspotting.hait.model.ApplicationEntity;
import com.trainspotting.hait.model.CityEntity;

@Mapper
public interface HomeMapper {
	List<CityEntity> select();
	int insert(ApplicationEntity p);
}
