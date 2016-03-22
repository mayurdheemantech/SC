package com.Login;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class LoginHandler extends BaseHandler{
	public LoginHandler() {
		// TODO Auto-generated constructor stub

	}
	
	
	public void process(){
		 DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			// specify the host, protocol, and port
			String baseUrl = "http://app.ultrasure.in/api.php?";
			HttpHost target = new HttpHost("app.ultrasure.in", 80, "http");

			// specify the get request
			HttpGet getRequest = new HttpGet(
					baseUrl  + req.toUrl());

//			System.out.println("executing request to " + target);

			HttpResponse httpResponse = httpclient.execute(target, getRequest);
			HttpEntity entity = httpResponse.getEntity();

			System.out.println("----------------------------------------");
			System.out.println(httpResponse.getStatusLine());
			Header[] headers = httpResponse.getAllHeaders();
			for (int i = 0; i < headers.length; i++) {
				System.out.println(headers[i]);
			}
			System.out.println("----------------------------------------");

			if (entity != null) {
				System.out.println(EntityUtils.toString(entity));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}
	}
	
	public static void main(String[] args) {
		LoginRequest req = new LoginRequest();
		req.setUser("apratim");
		req.setPassword("appi");
		System.out.println(req.toUrl());
		
		LoginHandler hdl = new LoginHandler();
		hdl.handle(req);
		
	}
	
}
