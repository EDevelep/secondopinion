package org.secondopinion.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class SendSms {
	public static final String apiKey = "3QuztFlewnoALHhymDqk7VOIjMEKX5Pc4sR1dGx9T8agNfWvCSsYJ1dapu7G5TrZIUhW9HS8KkACzolR";
	public static final String sendId = "CHKSMS";
	public static final String language = "english";
	public static final String route = "p";

	public static void sendSms(String message, String number) {
		try {
			message = URLEncoder.encode(message, "UTF-8");
			String myUrl = "https://www.fast2sms.com/dev/bulk?authorization=" + apiKey + "&sender_id=" + sendId
					+ "&message=" + message + "&language=" + language + "&route=" + route + "&numbers=" + number;
			// sending get request using java..
			URL url = new URL(myUrl);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setRequestProperty("cache-control", "no-cache");
			int code = con.getResponseCode();
			StringBuffer response = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				response.append(line);
			}
		

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
