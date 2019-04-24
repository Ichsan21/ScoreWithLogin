package com.example.login.model;


import com.google.gson.annotations.SerializedName;


public class Success{

	@SerializedName("token")
	private String token;

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}
}