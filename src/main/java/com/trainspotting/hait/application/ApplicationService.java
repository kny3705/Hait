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
		
		if(p.getProcess_status() == -1) {
			mail.rejectMail(p.getOwner_email());
			
		} else if (p.getProcess_status() == 1) {
			String tempPW = UUID.randomUUID().toString().replaceAll("-", "");
			tempPW = tempPW.substring(0, 10);
			
			OwnerEntity oe = new OwnerEntity();
			oe.setEmail(p.getOwner_email());
			oe.setPw(tempPW);
			mapper.insOwner(oe);
			mapper.insRstrnt(p);
			
			mail.acceptMail(p.getOwner_email(), tempPW);
		}
		
		return mapper.update(p);
	}
}