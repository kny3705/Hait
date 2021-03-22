package com.trainspotting.hait.owner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.trainspotting.hait.Utils.FileUtils;
import com.trainspotting.hait.Utils.SMSUtil;
import com.trainspotting.hait.exception.LoginFailedException;
import com.trainspotting.hait.jwt.JwtProvider;
import com.trainspotting.hait.model.OwnerDTO;
import com.trainspotting.hait.model.OwnerEntity;
import com.trainspotting.hait.model.ReservEntity;
import com.trainspotting.hait.model.RstrntDTO;
import com.trainspotting.hait.model.RstrntEntity;

import io.jsonwebtoken.Claims;

@Service
public class OwnerService {
	
	@Autowired
	private OwnerMapper mapper;

	@Autowired
	private SMSUtil smsUtil;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private FileUtils fUtils;

	public String login(OwnerEntity p) {
		OwnerDTO user = mapper.findUserByEmail(p.getEmail());
		
		if(user == null) {
			throw new LoginFailedException("USER_NOT_FOUND");
		}
		if(!passwordEncoder.matches(p.getPw(), user.getPw())) {
			throw new LoginFailedException("PASSWORD_MISMATCH");
		}
		String role = user.getRstrnt_enabled() == 1 ? "OWNER" : null;
		return jwtProvider.provideToken(p.getEmail(), role, user.getRstrnt_pk()).getToken();
	}
	
	public String initialSetting(MultipartFile file, RstrntDTO dto, String token) {
		String filename = uploadProfileImg(file, dto.getPk());

		dto.setProfile_img(filename);
		dto.setReset_pw(passwordEncoder.encode(dto.getReset_pw()));
		
		mapper.resetPw(dto);
		mapper.updRstrntInfo(dto);
		
		Claims currentToken = jwtProvider.provideToken(token).getData();
		String email = currentToken.getSubject();
		int r_pk = (int) currentToken.get("r_pk");
		return jwtProvider.provideToken(email, "OWNER", r_pk).getToken();
	}
	
	public RstrntEntity selRstrnt(int pk) {
		return mapper.selRstrnt(pk);
	}
	
	public String updRstrntState(RstrntEntity p) {
		String message = null;
		if(p.getState() == -1) {
			mapper.updReservStatus(p.getPk());
			message = "SUCCESS_CANCLE";
			// TODO send sms
		}
		mapper.updRstrntState(p);
		return message;
	}

	// 고객정보 리스트
	public List<ReservEntity> reservList(int r_pk) {
		return mapper.selReservAll(r_pk);
	}

	// 고객정보 디테일(1개)
	public int reservDetail(int pk){
		return mapper.selReserv(pk);
	}

	// 고객예약 상태(-3,-2,-1,0,1,2)
	public int updResStatus(ReservEntity p) {

		String soldOut = "재료 소진으로 영업을 종료하였습니다."; // -3
		String rstSitu = "가게 사정으로 영업을 종료하였습니다."; // -2
		String cstomSitu = "고객님의 사정으로 예약을 취소하였습니다."; // -1
		String successReserv = "예약에 성공하셨습니다.";// 0
		String call = "고객님 자리가 준비되었습니다.식당으로 와주세요";// 1

		
		switch (p.getProcess_status()) {
		case -3:
			smsUtil.send(null, soldOut);
			break;
		case -2:
			smsUtil.send(null, rstSitu);
			break;
		case -1:
			smsUtil.send(null, cstomSitu);
			break;
		case 0:
			smsUtil.send(null, successReserv);
			break;
		case 1:
			smsUtil.send(null, call);
			break;
		}
		return mapper.updResStatus(p);
	}

	// 정보수정update profile img만
//	public int updProfileImg(MultipartFile profile, int pk) {
////
//		int restPk = p.getPk();
//		String folder = "/resources/img/rstrnt/" + restPk;
//		String profileImg = fUtils.transferTo(profile, folder);
//		if (profileImg == null) { // 파일 생성 실패
//			return 0;
//		}
//
//		// 기존이미지가 존재했다면 이미지 삭제!
//		RstrntEntity userInfo = mapper.selRstrnt(p);
//		if (userInfo.getProfile_img() != null) {
//			String basePath = fUtils.getBasePath(folder);
//			File file = new File(basePath, userInfo.getProfile_img());
//			if (file.exists()) {
//				file.delete();
//			}
//		}
////
//		p.setProfile_img(profileImg);
//		return mapper.updImgSetting(p);
//		return 0;
//	}

	private String uploadProfileImg(MultipartFile file, int pk) {
		try {
			return fUtils.save(file, pk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
