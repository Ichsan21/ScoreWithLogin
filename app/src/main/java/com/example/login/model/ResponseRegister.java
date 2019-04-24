package com.example.login.model;


import com.google.gson.annotations.SerializedName;


public class ResponseRegister{

	@SerializedName("success")
	private Success success;

	@SerializedName("error")
	private String error;

	public void setSuccess(Success success){
		this.success = success;
	}

	public Success getSuccess(){
		return success;
	}

	public String getError(){
		return error;
	}

	public void setError(String error){
		this.error = error;
	}

	@Override
 	public String toString(){
		return 
			"ResponseRegister{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}