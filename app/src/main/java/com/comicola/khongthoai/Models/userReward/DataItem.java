package com.comicola.khongthoai.Models.userReward;

public class DataItem{
	private String reward;
	private String datetime;

	public void setReward(String reward){
		this.reward = reward;
	}

	public String getReward(){
		return reward;
	}

	public void setDatetime(String datetime){
		this.datetime = datetime;
	}

	public String getDatetime(){
		return datetime;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"reward = '" + reward + '\'' + 
			",datetime = '" + datetime + '\'' + 
			"}";
		}
}
