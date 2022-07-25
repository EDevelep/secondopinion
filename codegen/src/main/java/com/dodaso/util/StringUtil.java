package com.dodaso.util;

import java.util.StringTokenizer;

public class StringUtil {
	
	public static boolean isNullOrEmpty(String str){
		boolean empty = false;
		
		if(str == null || str.trim().length() == 0){
			empty = true;
		}
		
		return empty; 
	}
	
	public static boolean isYes(String str){
		if(isNullOrEmpty(str)){
			return false;
		}
		
		if("YES".equalsIgnoreCase(str) || "Y".equalsIgnoreCase(str)){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean equals(String str1, String str2){
		if(str1 == null && str2 == null){
			return true;
		}
		
		if(str1 == null && str1 != null){
			return false;
		}
		
		if(str1 != null && str1 == null){
			return false;
		}
		
		if(str1.equals(str2)){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean equalsIgnoreCase(String str1, String str2){
		if(str1 == null && str2 == null){
			return true;
		}
		
		if(str1 == null && str1 != null){
			return false;
		}
		
		if(str1 != null && str1 == null){
			return false;
		}
		
		if(str1.equalsIgnoreCase(str2)){
			return true;
		}else{
			return false;
		}
	}
	
	public static String initCap(String str) {
		if(isNullOrEmpty(str)){
			return str;
		}
		
		return (str.length()> 1) ? (str.substring(0,1).toUpperCase() + str.substring(1)) : str.toUpperCase();
	}
	
	public static String initlower(String str) {
		if(isNullOrEmpty(str)){
			return str;
		}
		
		return (str.length()> 1) ? (str.substring(0,1).toLowerCase() + str.substring(1)) : str.toUpperCase();
	}
	
	public static String replace(String inString, String oldPattern, String newPattern) {
		if (!hasLength(inString) || !hasLength(oldPattern) || newPattern == null) {
			return inString;
		}
		StringBuilder sb = new StringBuilder();
		int pos = 0; // our position in the old string
		int index = inString.indexOf(oldPattern);
		// the index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sb.append(inString.substring(pos, index));
			sb.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sb.append(inString.substring(pos));
		// remember to append any characters to the right of a match
		return sb.toString();
	}
	
	public static boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}

	public static boolean hasLength(String str) {
		return hasLength((CharSequence) str);
	}

	public static String getCamelCaseRemovingUnderScore(String value) {
		StringTokenizer toekn = new StringTokenizer(value, "_");
		StringBuilder str = new StringBuilder(toekn.nextToken());
		while (toekn.hasMoreTokens()) {
			String s = toekn.nextToken();
			str.append(Character.toUpperCase(s.charAt(0))).append(s.substring(1));
		}
		System.out.println(str.toString());

		return str.toString();
	}


}
