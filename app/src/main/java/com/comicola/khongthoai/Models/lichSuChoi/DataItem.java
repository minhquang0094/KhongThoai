package com.comicola.khongthoai.Models.lichSuChoi;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class DataItem{

	@SerializedName("datetime")
	private String datetime;

	@SerializedName("package")
	private String jsonMemberPackage;

	@SerializedName("success")
	private boolean success;

	public void setDatetime(String datetime){
		this.datetime = datetime;
	}

	public String getDatetime(){
		return datetime;
	}

	public void setJsonMemberPackage(String jsonMemberPackage){
		this.jsonMemberPackage = jsonMemberPackage;
	}

	public String getJsonMemberPackage(){
		return jsonMemberPackage;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"datetime = '" + datetime + '\'' + 
			",package = '" + jsonMemberPackage + '\'' + 
			",success = '" + success + '\'' + 
			"}";
		}
}