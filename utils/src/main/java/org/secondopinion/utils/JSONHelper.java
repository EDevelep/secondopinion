package org.secondopinion.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
	
	public static JSONObject buidJSONObject(String responseData) {
		if(StringUtil.isNullOrEmpty(responseData)) {
			return null;
		}
		try {
			return (JSONObject) new JSONParser().parse(responseData);
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
