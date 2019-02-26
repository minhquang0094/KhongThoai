package com.comicola.khongthoai.Models.start;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Data{

	@SerializedName("session")
	private String session;

	public void setSession(String session){
		this.session = session;
	}

	public String getSession(){
		return session;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"session = '" + session + '\'' + 
			"}";
		}
}