package org.secondopinion.utils;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by anil on 1/6/17.
 */
public class Util {

    private static final Logger logger = LoggerFactory.getLogger(Util.class);
    public static String nextNextAuthKey() {
        final SecureRandom random = new SecureRandom();

        return new BigInteger(130, random).toString(32);
    }

    public static ResponseEntity<RecaptchaResponse> getCaptchaResponse(String captcha, Environment env){
        logger.info("Calling recaptcha service");
        final RestTemplate restTemplate = new RestTemplate();
        final MultiValueMap<String, String> request= new LinkedMultiValueMap<String, String>();
        request.add("secret", env.getProperty("recaptcha.key"));
        request.add("response", captcha);
        logger.info("reCAPTCHA key :" + env.getProperty("recaptcha.key"));

        final ResponseEntity<RecaptchaResponse> response = restTemplate.postForEntity( "https://www.google.com/recaptcha/api/siteverify", request , RecaptchaResponse.class );
        return response;
    }
    public static String getSequenceNumber(Long id,String companyName,String identifier){
        return String.format("%s%s%010d",companyName.substring(0,3).toUpperCase(),identifier.toUpperCase(),id);
    }

    private static AmazonS3 s3Client = null;


    /**
     * Gives AmazonS3 client to work with s3 object IO operations
     **/
    public static AmazonS3 getClient(String access,String secret){
        if(s3Client == null){
            synchronized("Dummy String to be get locked"){
                try {
                    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                    s3Client = new AmazonS3Client(new BasicAWSCredentials(access, secret));
                } catch (Exception e) {
                    logger.error("While accessing the S3 following exception occured: "+e);
                }
            }
        }
        return s3Client;
    }

    /**
	 * Tax calculation for given amount
	 * 
	 * @param totalInvoicePrice
	 * @param precentage
	 * @return
	 */
	public static Double calculateTaxAmount(Double amount, Integer taxPercentage) {
		if(Objects.isNull(amount) || amount.equals(0.0d)) {
			return 0.0d;
		}
		return amount > 0 ? (amount * taxPercentage) / 100 : 0.0d;
	}
	
	public static <T> List<T> getListObjectsFromMap(Map<Long, List<T>> map) {
		Optional<List<T>> optional = map.entrySet().stream().map(es -> es.getValue()).findFirst();
		
		return optional.isPresent() ? optional.get() : new ArrayList<>();
	}
	
	public static Long addTwoLongOperands(Long value1, Long value2) {
		if(Objects.isNull(value1)) {
			value1 = 0L;
		}
		if(Objects.isNull(value2)) {
			value2 = 0L;
		}
		return value1 + value2;
	}
	
	public static Double addTwoDoubleOperands(Double value1, Double value2) {
		if(Objects.isNull(value1)) {
			value1 = 0.0d;
		}
		if(Objects.isNull(value2)) {
			value2 = 0.0d;
		}
		return value1 + value2;
	}

}
