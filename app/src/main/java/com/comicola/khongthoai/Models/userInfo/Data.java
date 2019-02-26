package com.comicola.khongthoai.Models.userInfo;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Data{

	@SerializedName("balance")
	private int balance;

	@SerializedName("phone")
	private String phone;

	@SerializedName("session_count")
	private int sessionCount;

	@SerializedName("reward_count")
	private int rewardCount;

	public void setBalance(int balance){
		this.balance = balance;
	}

	public int getBalance(){
		return balance;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setSessionCount(int sessionCount){
		this.sessionCount = sessionCount;
	}

	public int getSessionCount(){
		return sessionCount;
	}

	public void setRewardCount(int rewardCount){
		this.rewardCount = rewardCount;
	}

	public int getRewardCount(){
		return rewardCount;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"balance = '" + balance + '\'' + 
			",phone = '" + phone + '\'' + 
			",session_count = '" + sessionCount + '\'' + 
			",reward_count = '" + rewardCount + '\'' + 
			"}";
		}
}