package com.trainspotting.hait.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.trainspotting.hait.exception.LoginFailedException;
import com.trainspotting.hait.jwt.JwtProvider;
import com.trainspotting.hait.model.AdminEntity;
import com.trainspotting.hait.model.ApplicationDTO;
import com.trainspotting.hait.model.ApplicationEntity;

@Service
public class AdminService {
	
	@Autowired
	private AdminMapper mapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	public String login(AdminEntity param) {
		
		AdminEntity data = mapper.findUserById(param.getId());

		if(data == null) {
			throw new LoginFailedException("ACCOUNT_NOT_FOUND");
		}
		
		if(!passwordEncoder.matches(param.getPw(), data.getPw())) {
			throw new LoginFailedException("PASSWORD_MISMATCH");
		}

		return jwtProvider.provideToken(data.getId(), "ADMIN").getToken();
	}
	
	public List<ApplicationDTO> listAll() {
		return mapper.listAll();
	}
	
	public List<ApplicationDTO> listStatus(int p) {
		return mapper.listStatus(p);
	}
	
	public ApplicationDTO detail(ApplicationEntity p) {
		return mapper.detail(p);
	}

}
