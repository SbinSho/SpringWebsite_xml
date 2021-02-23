package com.suho.web.util;

import com.suho.web.dto.LoginDTO;

public class AuthInfo {

	private String id;

	public AuthInfo() {}
	
	public AuthInfo(LoginDTO loginDTO) {
		this.id = loginDTO.getId();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	
}
