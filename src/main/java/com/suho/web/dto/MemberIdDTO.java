package com.suho.web.dto;

import org.hibernate.validator.constraints.NotBlank;

public class MemberIdDTO {
	
	private String userid;
	
	@NotBlank(message = "아이디를 입력해주세요.")
	private String ch_userid;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCh_userid() {
		return ch_userid;
	}

	public void setCh_userid(String ch_userid) {
		this.ch_userid = ch_userid;
	}

	
}
