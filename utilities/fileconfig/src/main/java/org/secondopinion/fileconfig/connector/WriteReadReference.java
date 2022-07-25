package org.secondopinion.fileconfig.connector;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 *
 * @author jatin
 */
public class WriteReadReference {

	
	public static final String FILE_CONFIG_SECRETKEY = "I9a!b@c#d$e%f^g&";
	public static final String FILE_SEPARATOR = "/";
	
	public enum PropertiesEnum {
		TYPE, HOST, ACCES_KEY, SECRET_KEY, BUCKET_NAME, REMOTE_FILE_SERVER_LOCATION,
	}


	private WriteReadReference() throws Exception {
		// nothing

	}

	public static String encrypt(String str) throws Exception {
		return encrypt(str, FILE_CONFIG_SECRETKEY);
	}
	@SuppressWarnings("restriction")
	public static String encrypt(String str, String secretKey) throws Exception {
		
		byte[] bytes = secretKey.getBytes();
		
		SecretKey key = new SecretKeySpec(bytes, "AES");
		
		Cipher ecipher = Cipher.getInstance("AES");
		ecipher.init(Cipher.ENCRYPT_MODE, key);
		// Encode the string into bytes using utf-8
		byte[] utf8 = str.getBytes("UTF8");

		// Encrypt
		byte[] enc = ecipher.doFinal(utf8);

		// Encode bytes to base64 to get a string
		return new sun.misc.BASE64Encoder().encode(enc);
	}
	public static String decrypt(String str) throws Exception {
		return decrypt(str, FILE_CONFIG_SECRETKEY);
	}
	@SuppressWarnings("restriction")
	public static String decrypt(String str, String secret_key) throws Exception {
		byte[] bytes = secret_key.getBytes();
		SecretKey key = new SecretKeySpec(bytes, "AES");
		
		
		Cipher dcipher = Cipher.getInstance("AES");
		dcipher.init(Cipher.DECRYPT_MODE, key);
		// Decode base64 to get bytes
		byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

		
		
		byte[] utf8 = dcipher.doFinal(dec);

		// Decode using utf-8
		return new String(utf8, "UTF8");
	}
	
}
