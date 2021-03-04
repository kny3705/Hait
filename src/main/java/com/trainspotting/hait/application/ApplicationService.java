package com.trainspotting.hait.application;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainspotting.hait.mail.SendMail;
import com.trainspotting.hait.model.ApplicationEntity;
import com.trainspotting.hait.model.OwnerEntity;

@Service
public class ApplicationService {

	@Autowired
	private ApplicationMapper mapper;
	
	@Autowired
	private SendMail mail;
	
	public int update(ApplicationEntity p) {
		//TODO 받아온 값 확인!!
		ApplicationEntity dbData = mapper.select(p);
		
		if(p.getProcess_status() == -1) {
			mail.rejectMail(dbData.getOwner_email());
			
		} else if (p.getProcess_status() == 1) {
			String tempPW = UUID.randomUUID().toString().replaceAll("-", "");
			tempPW = tempPW.substring(0, 10);
			
			OwnerEntity oe = new OwnerEntity();
			oe.setNm(dbData.getOwner_nm());
			oe.setEmail(dbData.getOwner_email());
			oe.setContact(dbData.getOwner_email());
			oe.setPw(tempPW);
			mapper.insert(oe);
			
			mail.acceptMail(dbData.getOwner_email(), tempPW);
		}
		
		return mapper.update(p);
	}
}
