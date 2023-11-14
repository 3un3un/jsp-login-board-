package com.login.dto;

public class MemberDto {
	public String id;
	public String pass;
	public String regidate;
	
	public MemberDto() {
	}

	public MemberDto(String id, String pass, String regidate) {
		this.id = id;
		this.pass = pass;
		this.regidate = regidate;
	}
	
	public String getRegidate() {
		return regidate;
	}

	public void setRegidate(String regidate) {
		this.regidate = regidate;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

}
