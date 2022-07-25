package org.secondopinion.utilities.feedbackreport.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpsClient {

//	public static void main(String[] args) throws Exception {
//		String urlStr = "https://api.worldpostallocations.com/pincode?postalcode=500084&countrycode=IN";

	public static Map<String, String> allPostalDataByPostalCode(String postalCode) {
		String urlStr = "https://api.worldpostallocations.com/pincode";

		Map<String, String> params = new HashMap<String, String>();

		params.put("postalcode", postalCode);
		params.put("countrycode", "IN");

		System.out.println(HTTPS_GET_BypassSSL(urlStr, params));

		return null;
	}

	public static String HTTPS_GET_BypassSSL(String urlStr, Map<String, String> urlParameters) {
		// configure the SSLContext with a TrustManager

		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());

			SSLContext.setDefault(ctx);

			URI uri = applyParameters(urlStr, urlParameters);// ("https://mms.nw.ru");

			HttpsURLConnection conn = (HttpsURLConnection) uri.toURL().openConnection();
			conn.setHostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}
			});

			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			StringBuilder sb = new StringBuilder();
			String inputLine;

			try {
				while ((inputLine = br.readLine()) != null) {
					sb.append(inputLine);
				}
			} finally {
				br.close();
				conn.disconnect();
			}

			return sb.toString();
		} catch (Throwable t) {
			throw new RuntimeException("Error calling HTTPS GET: " + t.getMessage(), t);
		}

	}

	private static URI applyParameters(String baseUriStr, Map<String, String> urlParameters) throws URISyntaxException {

		URI baseUri = new URI(baseUriStr);

		StringBuilder query = new StringBuilder();
		boolean first = true;

		for (String n : urlParameters.keySet()) {
			if (first) {
				first = false;
			} else {
				query.append("&");
			}

			try {
				query.append(n).append("=").append(URLEncoder.encode(urlParameters.get(n), "UTF-8"));
			} catch (UnsupportedEncodingException ex) {
				/*
				 * As URLEncoder are always correct, this exception should never be thrown.
				 */
				throw new RuntimeException(ex);
			}
		}

		try {
			return new URI(baseUri.getScheme(), baseUri.getAuthority(), baseUri.getPath(), query.toString(), null);
		} catch (URISyntaxException ex) {
			/*
			 * As baseUri and query are correct, this exception should never be thrown.
			 */
			throw new RuntimeException(ex);
		}
	}

	private static class DefaultTrustManager implements X509TrustManager {

		@Override
		public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	}

}