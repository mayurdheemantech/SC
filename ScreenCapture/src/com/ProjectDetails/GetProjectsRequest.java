package com.ProjectDetails;


public class GetProjectsRequest extends ProjectBaseRequest{
	String userid;

	public GetProjectsRequest(String user) {
		super();
		this.userid = "login";
	}
	
	@Override
	public String toUrl(){
		return super.toUrl() +  "&username=" + userid ;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
}
