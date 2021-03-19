package com.trainspotting.hait.owner;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.trainspotting.hait.model.OwnerDTO;
import com.trainspotting.hait.model.ReservEntity;
import com.trainspotting.hait.model.RstrntDTO;
import com.trainspotting.hait.model.RstrntEntity;

@Mapper
public interface OwnerMapper {

	OwnerDTO findUserByEmail(String email);
	int resetPw(RstrntDTO dto);
	//t_reserv
	List<ReservEntity> resvList();

	int resvDetail(ReservEntity p);

	int updResStatus(ReservEntity p);

	//t_rstrnt
	int updRstState(RstrntEntity p);

	int insRstrnt(RstrntEntity p);

//	int updRstsetting(RstrntEntity p);
	int updRstrntInfo(RstrntEntity p);

	int updImgSetting(RstrntEntity p);

//	RstrntEntity selRstrnt(RstrntEntity p);

	RstrntEntity selRstrnt(int pk);
}
