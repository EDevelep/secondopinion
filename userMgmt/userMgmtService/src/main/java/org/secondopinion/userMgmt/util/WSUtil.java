package org.secondopinion.userMgmt.util;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;

import javax.ws.rs.core.Response.ResponseBuilder;

public class WSUtil {

	public static javax.ws.rs.core.Response buildResponseFromFileStream(File file) {
		javax.ws.rs.core.Response response = null;

		try {
			ResponseBuilder builder = javax.ws.rs.core.Response.ok(file);
			builder.header("Content-Disposition", "attachment; filename=" + file.getName());
			response = builder.build();

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static javax.ws.rs.core.Response buildImgResponseFromFileStream(File file) {
		javax.ws.rs.core.Response response = null;

		
		try {
			ResponseBuilder builder = null;
			if(file != null && file.exists()) {
				byte[] bytes = Files.readAllBytes(file.toPath());
				byte[] encodedBytes = Base64.getEncoder().encode(bytes);
				builder = javax.ws.rs.core.Response.ok(encodedBytes);
			}
			else {
				builder = javax.ws.rs.core.Response.ok();
			}
			response = builder.build();

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}

