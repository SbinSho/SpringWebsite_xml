package com.suho.web.dto;

import org.hibernate.validator.constraints.NotBlank;

public class LoginDTO {

	@NotBlank(message = "아이디를 입력해주세요.")
	private String id;
	
	@NotBlank(message = "비밀번호를 입력해주세요.")
	private String pwd;
	
	private boolean reid;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public boolean isReid() {
		return reid;
	}

	public void setReid(boolean reid) {
		this.reid = reid;
	}


	
}
