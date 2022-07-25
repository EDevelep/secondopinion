package org.secondopinion.reports.util;

import java.security.SecureRandom;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Collection;
import java.util.Random;
import java.util.StringTokenizer;

public class StringUtil {

	public static String trimAllNonAlphaChars(String str) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		return str.replaceAll("^a-zA-Z0-9- ", "");
	}

	public static boolean isNullOrEmpty(String str) {
		boolean empty = false;

		if (str == null || str.trim().length() == 0) {
			empty = true;
		}

		return empty;
	}

	public static boolean equalsIgnoreCase(String string1, String string2) {
		return (string1 == null ? string2 == null : string1
				.equalsIgnoreCase(string2));
	}

	public static String initCap(String fieldName) {
		if(fieldName.length()>1){
			String str = fieldName.substring(0, 1);
			str = str.toUpperCase() + (fieldName.substring(1)).toLowerCase();
			return str;
		}else{
			return fieldName.toLowerCase();
		}
		
	}

	public static String initLower(String fieldName) {
		if(fieldName.length()>1){
			String str = fieldName.substring(0, 1);
		
			str = str.toLowerCase() + (fieldName.substring(1)).toUpperCase();
	
			return str;
		}else{
			return fieldName.toLowerCase();
		}
	}

	public static String trim(String str) {
		return (str == null ? null : str.trim());
	}

	public static String stripCharacter(char c, String parseString) {
		String tempVal = parseString;
		if (tempVal != null) {
			if (tempVal.indexOf(c) != -1) {
				int index = 0;

				StringBuffer sb = new StringBuffer();
				while (index < sb.length()) {
					if (sb.charAt(index) == c) {
						sb.deleteCharAt(index);
					} else {
						index++;
					}
				}

				tempVal = sb.toString();
			}
		}

		return tempVal;
	}

	public static boolean isYes(String str) {
		if (isNullOrEmpty(str)) {
			return false;
		}

		if ("YES".equalsIgnoreCase(str) || "Y".equalsIgnoreCase(str)) {
			return true;
		} else {
			return false;
		}
	}

	public static String stringArrayToDelimited(String[] values,
			String delimiter) {

		if (values == null || values.length == 0) {
			return "";
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < values.length; i++) {
			if (i > 0) {
				sb.append(delimiter);
			}
			sb.append(values[i]);
		}

		return sb.toString();
	}

	public static String toString(Collection<?> list) {
		return toString(list.toArray(), false);
	}

	public static String toString(Collection<?> list, boolean newLinePerElement) {
		return toString(list.toArray(), newLinePerElement);
	}

	public static String toString(Collection<?> list,
			boolean newLinePerElement, String delimiter) {
		return toStringDelimiter(list.toArray(), newLinePerElement, delimiter);
	}

	public static String toString(Object[] objArray) {
		return toString(objArray, false);
	}

	public static String toString(Object obj) {
		if (obj == null)
			return "";
		else
			return String.valueOf(obj);
	}

	/**
	 * Retuns the string representing the objArray.
	 *
	 * @param objArray
	 * @return
	 */
	public static String toString(Object[] objArray, boolean newLinePerElement) {
		return toString(objArray, newLinePerElement, true);
	}

	/**
	 * Retuns the string representing the objArray.
	 *
	 * @param objArray
	 * @return
	 */
	private static String toString(Object[] objArray,
			boolean newLinePerElement, boolean processSubArrays) {
		if (objArray == null)
			return null;

		String delimiter = ",";
		if (newLinePerElement)
			delimiter += "\n";
		else
			delimiter += "";

		StringBuffer buffer = new StringBuffer("[");
		Object currObject = null;
		for (int i = 0, size = objArray.length; i < size; i++) {
			if (i > 0)
				buffer.append(delimiter);
			currObject = objArray[i];
			if (currObject != null) {
				// processSubArrays is passed false, only one level of recursion
				// to avoid infinite loop.
				if (processSubArrays && currObject instanceof Object[])
					buffer.append(toString((Object[]) currObject,
							newLinePerElement, false));
				else
					buffer.append("{" + String.valueOf(currObject) + "}");
			} else
				buffer.append("{}");
		}
		buffer.append("]");
		return buffer.toString();
	}

	private static String toStringDelimiter(Object[] objArray,
			boolean newLinePerElement, String delimiter) {
		if (objArray == null)
			return null;

		if (!newLinePerElement)
			delimiter = "";
		else if (isNullOrEmpty(delimiter))
			delimiter = "/n";

		StringBuffer buffer = new StringBuffer("");
		Object currObject = null;
		for (int i = 0, size = objArray.length; i < size; i++) {
			if (i > 0)
				buffer.append(delimiter);
			currObject = objArray[i];
			if (currObject != null) {
				buffer.append(String.valueOf(currObject));
			}
		}
		return buffer.toString();
	}

	public static String getEscapedXMLRepresentation(String xmlStr) {
		if (StringUtil.isNullOrEmpty(xmlStr)) {
			return xmlStr;
		}

		xmlStr.replaceAll("\"", "~\"");
		char[] cs = xmlStr.toCharArray();

		for (int i = 0; i < cs.length; i++) {
			if (cs[i] == '~') {
				cs[i] = '\\';
			}
		}

		return new String(cs);

	}

	public static String[] split(String str, String delimeter) {
		StringTokenizer st = new StringTokenizer(str, delimeter);
		String[] strArr = new String[st.countTokens()];

		int i = 0;
		while (st.hasMoreTokens()) {
			strArr[i] = st.nextToken();
			i++;
		}

		return strArr;
	}

	// + - && || ! ( ) { } [ ] ^ " ~ * ? : \ /
	public static String escapeEscapeChars(String str) {

		final StringBuilder result = new StringBuilder();

		final StringCharacterIterator iterator = new StringCharacterIterator(
				str);

		char character = iterator.current();
		while (character != CharacterIterator.DONE) {
			/*
			 * All literals need to have backslashes doubled.
			 */
			if (character == '/') {
				result.append("\\//");
				// } else if (character == '-') {
				// result.append("\\-");
			} else if (character == '\\') {
				result.append("\\\\");
			} else if (character == '~') {
				result.append("\\~");
			} else if (character == '?') {
				result.append("\\?");
			} else if (character == '*') {
				result.append("\\*");
			} else if (character == '+') {
				result.append("\\+");
				// } else if (character == '&') {
				// result.append("\\&");
			} else if (character == ':') {
				result.append("\\:");
			} else if (character == '{') {
				result.append("\\{");
			} else if (character == '}') {
				result.append("\\}");
			} else if (character == '[') {
				result.append("\\[");
			} else if (character == ']') {
				result.append("\\]");
			} else if (character == '(') {
				result.append("\\(");
			} else if (character == ')') {
				result.append("\\)");
			} else if (character == '{') {
				result.append("\\{");
			} else if (character == '}') {
				result.append("\\}");
			} else if (character == '^') {
				result.append("\\^");
			} else {
				// the char is not a special one
				// add it to the result as is
				result.append(character);
			}
			character = iterator.next();
		}
		return result.toString();
	}

	public static String crop(String str, int length) {
		return (StringUtil.isNullOrEmpty(str) ? ""
				: (str.length() < length ? str : str.substring(1, length)));
	}
	
	 private static final Random RANDOM = new SecureRandom();
	  /**
	   * Generate a random String suitable for use as a temporary password.
	   *
	   * @return String suitable for use as a temporary password
	   * @since 2.4
	   */
	  public static String generateRandomPassword(int passwordLength)
	  {
	      // Pick from some letters that won't be easily mistaken for each
	      // other. So, for example, omit o O and 0, 1 l and L.
	      String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ0123456789+@$";

	      String pw = "";
	      for (int i=0; i<passwordLength; i++)
	      {
	          int index = (int)(RANDOM.nextDouble()*letters.length());
	          pw += letters.substring(index, index+1);
	      }
	      return pw;
	  }
	  
	  public static int getColumnIndex(String[] cols, String colVal) {
		int i = 0;
		for(String col : cols){
			if (col.contains(" ")){
				col = col.split(" ")[1];
			}
			if(StringUtil.equalsIgnoreCase(col, colVal)){
				return i;
			}
			i++;
		}
		throw new IllegalArgumentException("Unable to find index for the column:" + colVal + " in columns: " + StringUtil.stringArrayToDelimited(cols, ","));
	}
}
