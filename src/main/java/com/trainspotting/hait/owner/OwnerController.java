package com.trainspotting.hait.owner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.trainspotting.hait.model.OwnerEntity;
import com.trainspotting.hait.model.ReservEntity;
import com.trainspotting.hait.model.RstrntEntity;

@CrossOrigin
@RestController
@RequestMapping("/api/owner")
class OwnerController {

	@Autowired
	private OwnerService service;

	// 로그인 할때 넘겨줄 정보
	@PostMapping("/login")
	public int ownerLogin(OwnerEntity p) {
		return service.selOwnerInfo(p);
	}

	// 초기정보 셋팅
	@PutMapping("/initial-setting")
	public int insRstrnt(RstrntEntity p) {
		return service.insRstrnt(p);
		//비밀번호 수정 (초기에 변경가능하게)

	}

	//정보수정 프로필사진만
	
	@PutMapping("/profile") 
	public int updImgSetting(MultipartFile profile, RstrntEntity p){ 
		 return service.updImgSetting(profile, p); 
		 //비밀번호 수정 
	 
	}
	 
	 // 고객 정보 List
	@GetMapping("/resvInfoList")
	public Map<String, Object> cstomInfoList() {
		Map<String, Object> json = new HashMap<>();
		json.put("resvInfo", service.resvInfoList());
		return json;
	}

	// 고개 정보 detail
	@GetMapping("/resvInfo/{pk}")
	public int cstomInfoDetail(ReservEntity p) {
		System.out.println(p.getPk());
		return service.resvDetail(p);
	}

	// 레스토랑 상태(open, close, break)
	@PutMapping("/state")
	public int updRstState(RstrntEntity p) {
		return service.updRstState(p);

	}

	// 고객정보만 보여주기
	@GetMapping("/dashboard-break")
	public List<ReservEntity> dsahBreak() {
		return service.resvInfoList();

	}

	// 새로고침 버튼 누를때마다 새로운 손님들 정보 받아오기(업데이트)
	@PutMapping("/dashboard-open")
	public Map<String, Object> open() {
		Map<String, Object> json = new HashMap<>();
		json.put("data", service.resvInfoList());
		return json;

	}

	// ----------------open page button-----------------//

	@PutMapping("/rstrntStatus") // 레스토랑 예약 상태 (-3,-2,-1,0,1,2)
	public int rstrntStatus(RstrntEntity p) {
		return service.updRstState(p);
	}

	

}
