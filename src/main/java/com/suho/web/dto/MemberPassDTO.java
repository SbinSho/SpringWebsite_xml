package com.suho.web.dto;

public class MemberPassDTO {

	private String userid;
	private String userpwd;
	private String ch_userpwd;
	
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
	public String getCh_userpwd() {
		return ch_userpwd;
	}
	public void setCh_userpwd(String ch_userpwd) {
		this.ch_userpwd = ch_userpwd;
	}
	
	@Override
	public String toString() {
		return "MemberPassDTO [userid=" + userid + ", userpwd=" + userpwd + ", ch_userpwd=" + ch_userpwd + "]";
	}
	
	
	
	
}
