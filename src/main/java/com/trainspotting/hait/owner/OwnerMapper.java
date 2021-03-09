package com.trainspotting.hait.owner;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.trainspotting.hait.model.OwnerEntity;
import com.trainspotting.hait.model.ReservEntity;
import com.trainspotting.hait.model.RstrntEntity;

@Mapper
public interface OwnerMapper {

	int selOwnerInfo(OwnerEntity p);

	//t_reserv
	List<ReservEntity> resvList();

	int resvDetail(ReservEntity p);

	int updResStatus(ReservEntity p);

	//t_rstrnt
	int updRstState(RstrntEntity p);

	int insRstrnt(RstrntEntity p);

	int updRstsetting(RstrntEntity p);

	int updImgSetting(RstrntEntity p);

	RstrntEntity selRstrnt(RstrntEntity p);
}
