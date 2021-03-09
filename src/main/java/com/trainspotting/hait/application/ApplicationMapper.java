package com.trainspotting.hait.application;

import org.apache.ibatis.annotations.Mapper;

import com.trainspotting.hait.model.ApplicationEntity;
import com.trainspotting.hait.model.OwnerEntity;

@Mapper
public interface ApplicationMapper {
	ApplicationEntity select(ApplicationEntity p);
	int update(ApplicationEntity p);
	int insOwner(OwnerEntity p);
	int insRstrnt(ApplicationEntity p);
}