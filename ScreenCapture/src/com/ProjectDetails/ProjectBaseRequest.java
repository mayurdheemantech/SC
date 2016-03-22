package com.ProjectDetails;

import java.util.HashMap;
import java.util.Map;

public class ProjectBaseRequest {
	private Map<String, String> cookies = new HashMap<>(); 
	
	protected String operation = null;

	public Map<String, String> getCookies() {
		return cookies;
	}

	public void setCookies(Map<String, String> cookies) {
		this.cookies = cookies;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String toUrl(){
		return "request=" + operation;
	}
}
