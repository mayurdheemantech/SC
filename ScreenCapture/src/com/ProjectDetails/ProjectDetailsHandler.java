package com.ProjectDetails;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.Common.Main;

public class ProjectDetailsHandler extends ProjectBaseHandler {
	public void process(){
		 DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			// specify the host, protocol, and port
			String baseUrl = "http://app.ultrasure.in/api?";
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
		GetProjectsRequest pr = new GetProjectsRequest(null);
		pr.setUserid("1");
		System.out.println(pr.getUserid());
		
		/*HttpGet session= pr.getUserid();
		if(cust!=null)
		{   
			
			session.setAttribute("CustomerID", cust.getCustomerId());
			session.setAttribute("EmailId", login);
			return cust.getRole();
			
		}
		try
		{
			
		}catch (Exception e)
		{
			e.printStackTrace();
		}*/
	}
}
