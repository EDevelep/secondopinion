package org.secondopinion.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.secondopinion.fileconfig.connector.WriteReadReference;

public class AES {
	
	public static String getEncodedMessage(final String key, final String iv, final String message){
		final byte[] keyBytes = DatatypeConverter.parseBase64Binary(key);
		final byte[] ivBytes = DatatypeConverter.parseBase64Binary(iv);
		
		return getEncodedMessage(keyBytes, ivBytes, message);
	}
	
	public static String getDecodedMessage(final String key, final String iv, final String message){
		final byte[] keyBytes = DatatypeConverter.parseBase64Binary(key);
		final byte[] ivBytes = DatatypeConverter.parseBase64Binary(iv);
		
		return getDecodedMessage(keyBytes, ivBytes, message);
	}
	
	public static String getEncodedMessage(final byte[] keyBytes, final byte[] ivBytes, final String message){
		final String base64Message = Base64.getEncoder().encodeToString(message.getBytes());
		final byte[] messageBytes = DatatypeConverter.parseBase64Binary(base64Message);
		
		 byte[] byteMsg = encrypt(keyBytes, ivBytes, messageBytes);
		 
		 return Base64.getEncoder().encodeToString(byteMsg);
	}
	
	public static String getDecodedMessage(final byte[] keyBytes, final byte[] ivBytes, final String message){
		final byte[] messageBytes = DatatypeConverter.parseBase64Binary(message);
		
		 byte[] decryptedBytes = decrypt(keyBytes, ivBytes, messageBytes);
		 
		 try {
			return new String(decryptedBytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static String generateIv() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[16];
        random.nextBytes(bytes);
        String s = Base64.getEncoder().encodeToString(bytes);
        return s;
    }
	
	public static byte[] encrypt(final byte[] keyBytes, final byte[] ivBytes, final byte[] messageBytes) {
		return AES.transform(Cipher.ENCRYPT_MODE, keyBytes, ivBytes, messageBytes);
	}

	public static byte[] decrypt(final byte[] keyBytes, final byte[] ivBytes, final byte[] messageBytes){
		return AES.transform(Cipher.DECRYPT_MODE, keyBytes, ivBytes, messageBytes);
	}

	private static byte[] transform(final int mode, final byte[] keyBytes, final byte[] ivBytes,
			final byte[] messageBytes) {
		
		try{
			final SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
			final IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
			final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	
			cipher.init(mode, keySpec, ivSpec);
	
			return cipher.doFinal(messageBytes);
		}catch(  NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
				InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex){
			throw new IllegalArgumentException("Error in transforming data:" + ex.getMessage(), ex);
		}
	}
	
	public static Cipher getDecodeCipher(final String key, final String iv){
		final byte[] keyBytes = DatatypeConverter.parseBase64Binary(key);
		final byte[] ivBytes = DatatypeConverter.parseBase64Binary(iv);
		
		return getCipher(Cipher.DECRYPT_MODE, keyBytes, ivBytes);
	}
	
	public static String getDecodedMessage(Cipher cipher, final String message){
		final byte[] messageBytes = DatatypeConverter.parseBase64Binary(message);
		
		 try {
			 byte[] decryptedBytes = cipher.doFinal(messageBytes);
			return new String(decryptedBytes, "UTF-8");
		} catch (UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static Cipher getEncodeCipher(final String key, final String iv){
		final byte[] keyBytes = DatatypeConverter.parseBase64Binary(key);
		final byte[] ivBytes = DatatypeConverter.parseBase64Binary(iv);
		
		return getCipher(Cipher.ENCRYPT_MODE, keyBytes, ivBytes);
	}
	
	public static String getEncodedMessage(Cipher cipher, final String message){
		final String base64Message = Base64.getEncoder().encodeToString(message.getBytes());
		final byte[] messageBytes = DatatypeConverter.parseBase64Binary(base64Message);
		
		try {
			byte[] byteMsg = cipher.doFinal(messageBytes);
			return Base64.getEncoder().encodeToString(byteMsg);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		 
	}
	
	private static Cipher getCipher(final int mode, final byte[] keyBytes, final byte[] ivBytes) {
		try{
			final SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
			final IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
			final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	
			cipher.init(mode, keySpec, ivSpec);
			
			return cipher;
		}catch(  NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
				InvalidAlgorithmParameterException  ex){
			throw new IllegalArgumentException("Error in transforming data:" + ex.getMessage(), ex);
		}
	}
	

	public static final String AES_SECRETKEY = "A*B(C)D-E_F=G{H}I9a!b@c#d$e%f^g&";//length 32
	public static String encrypt(String str) throws Exception {
		return WriteReadReference.encrypt(str, AES_SECRETKEY);
	}
	public static String decrypt(String str) throws Exception {
		return WriteReadReference.decrypt(str, AES_SECRETKEY);
	}
	
}
