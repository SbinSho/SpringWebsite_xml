package com.suho.web.domain;

import java.sql.Timestamp;


import org.hibernate.validator.constraints.NotBlank;

public class MemberVO {
	
	@NotBlank
	private String userid;
	
	@NotBlank
	private String userpwd;
	
	@NotBlank
	private String username;
	
	private Timestamp rdate;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Timestamp getRdate() {
		return rdate;
	}

	public void setRdate(Timestamp rdate) {
		this.rdate = rdate;
	}

	@Override
	public String toString() {
		return "MemberVO [userid=" + userid + ", userpwd=" + userpwd + ", username=" + username + ", rdate=" + rdate
				+ "]";
	}

	

}
