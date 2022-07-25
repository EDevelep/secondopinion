package org.secondopinion.userMgmt.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONHelper {

	
	
	public static Gson getGsonWithDateTime(){
		return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ssSSS").create();
	}

	public static Gson getGsonWithDate(){
		return new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
	}
	
	public static Gson getGson(String dateFormat){
		return new GsonBuilder().setDateFormat(dateFormat).create();
	}
}
