package org.secondopinion.utils;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;

public class UserHelper {
	
	public static String getHashedPassWord(String password) {
		String salt = BCrypt.gensalt();
		return BCrypt.hashpw(password, salt);
	}
	
	public static boolean checkpw(String plaintext, String hashed) {
        return BCrypt.checkpw(plaintext, hashed);
    }
	
	public static void main(String[] args ) {
		String salt = BCrypt.gensalt();
		System.out.println(salt);
	}
	
	public static boolean passwordValidation(String password, boolean validationRequired) {
		
		if(validationRequired) {
			return passwordValidation(password);
		}
		return true;
	}
	
	// Function to generate random alpha-numeric password of specific length
    public static String generateRandomPassword(int len)
    {
        // ASCII range - alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
 
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
 
        // each iteration of loop choose a character randomly from the given ASCII range
        // and append it to StringBuilder instance
 
        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
 
        return sb.toString();
    }
 
	
	public static boolean passwordValidation(String password) {
		
		//String validationMessage = "Password should at least 8 characters and at most 15 characters, at least one digit, one upper/lower case alphabet, one special character which includes !@#$%&*()-+=^, doesn’t contain any white space";
		String validationMessage = "Password should at least 8 characters and at most 15 characters, at least one digit, one upper/lower case alphabet, one special character , doesn’t contain any white space";
		// Regex to check valid password. 
	    String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$"; 
	
	    // Compile the ReGex 
	    Pattern p = Pattern.compile(regex); 
	
	    // If the password is empty 
	    // return false 
	    if (StringUtil.isNullOrEmpty(password)) { 
	    	throw new RuntimeException(validationMessage);
	    } 
	
	    // Pattern class contains matcher() method 
	    // to find matching between given password 
	    // and regular expression. 
	    Matcher m = p.matcher(password); 
	
	    // Return if the password 
	    // matched the ReGex 
	    boolean isValid = m.matches(); 
	    if ( !isValid) { 
	    	throw new RuntimeException(validationMessage);
	    } 
	    return isValid;
	}
}
