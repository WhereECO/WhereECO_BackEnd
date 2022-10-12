package com.WhereECO.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	int id;
	String userid;

	// 클라이언트에서 pwd 필드를 사용할 필요가 없으므로,
	// response 보내지 않도록 함
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	String pwd;

	String name;

	public int getid() {
		return id;
	}
	public void setid(int id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
