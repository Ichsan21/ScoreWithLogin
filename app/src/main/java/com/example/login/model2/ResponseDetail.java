package com.example.login.model2;

import com.google.gson.annotations.SerializedName;

public class ResponseDetail{

	@SerializedName("success")
	private Success success;

	public void setSuccess(Success success){
		this.success = success;
	}

	public Success getSuccess(){
		return success;
	}

	@Override
 	public String toString(){
		return 
			"ResponseDetail{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}