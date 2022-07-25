package org.secondopinion.utils;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

public class S3Util {
public static final Logger log = LoggerFactory.getLogger(S3Util.class);
private static AmazonS3 s3Client = null;

	
	/**
	 * Gives AmazonS3 client to work with s3 object IO operations
	 **/
	public static AmazonS3 getClient(String access,String secret){
		if(Objects.isNull(s3Client )){
			synchronized("Dummy String to be get locked"){
				try {
					ClassLoader classloader = Thread.currentThread().getContextClassLoader();
					s3Client = new AmazonS3Client(new BasicAWSCredentials(access, secret));
				} catch (Exception e) {
				    log.error("While accessing the S3 following exception occured: "+e);
				}
			}
		}
		return s3Client;
	}
}

