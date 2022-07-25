package org.videoroomotpphone.otp;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.LoadingCache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

/**
 * OTP service is used to validate phone number or email
 * @author lenovo
 *
 */
public class OtpService {

	//cache based on number/email and OTP is MAX 5 mins
	public static final Integer EXPIRE_MINS = 1;

	public static OtpService instance;



	private static  LoadingCache<String, Integer> otpCache ;

	private OtpService(){
		super();

	}
	public static OtpService getInstance() {
		OtpService result = instance;
		if(Objects.isNull(result )) {
			synchronized(OtpService.class){
				result = instance;
				if (Objects.isNull(result )) {
					instance  = new OtpService();
					otpCache = CacheBuilder.newBuilder().
							expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES).build(new CacheLoader<String, Integer>() {
								public Integer load(String key) {
									return 0;
								}
							});
				}
			}

		}
		return instance;

	}
	

	//This method is used to push the otp number against Key. Rewrite the OTP if it exists
	//Using user id  as key
	public int generateOTP(String toKey) {

		Random random = new Random();	
		int otp = 100000 + random.nextInt(900000);
		otpCache.put(toKey, otp);

		return otp;
	}

	//This method is used to return the OPT number against Key->Key values is username
	public int getOtp(String key){		 
		try{
			return otpCache.get(key); 
		}catch (Exception e){
			return 0;			 
		}
	}

	//This method is used to clear the OTP catched already
	public void clearOTP(String key){		 
		otpCache.invalidate(key);
	}

	/**
	 * Validating the OTP 
	 * @param key
	 * @param userOtp
	 * @return
	 */
	public boolean validateOtp(String key, int userOtp) {
		if(userOtp <= 0 || getOtp(key) <= 0 || userOtp != getOtp(key)) {
			return false;
		}
		clearOTP(key);
		return true;
	}

	public String buildNewOTPTextMessageForPhone(int otp, String reason, String websiteName) {
		StringBuilder otpMessage = new StringBuilder("Dear Customer,").append(otp);
		otpMessage.append(" is OTP for your ").append(reason).append(" OTP is valid for ");
		otpMessage.append(OtpService.EXPIRE_MINS +" minuets. ");
		otpMessage.append("OTPs are SECRET. DO NOT disclose it to anyone ");
		otpMessage.append(websiteName).append(" NEVER asks for OTP.");
		return otpMessage.toString();
	}

	public String buildNewOTPVoiceMessageForPhone(int otp, String reason, String websiteName) {
		StringBuilder otpMessage = new StringBuilder("Dear Customer,");
		otpMessage.append("The OTP for your ").append(reason).append(" is ").append(otp).append(" OTP is valid for ");
		otpMessage.append(OtpService.EXPIRE_MINS +" minuets. ");
		otpMessage.append("OTPs are SECRET. DO NOT disclose it to anyone ");
		otpMessage.append(websiteName).append(" NEVER asks for OTP.");
		return otpMessage.toString();
	}


}
