package com.Login;

public class LoginRequest extends BaseRequest {
	String user;
	String password;
	
	
	
	public LoginRequest() {
		super();
		super.operation = "login";
	}

	@Override
	public String toUrl(){
		return super.toUrl() +  "&username=" + user + "&password=" + password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
