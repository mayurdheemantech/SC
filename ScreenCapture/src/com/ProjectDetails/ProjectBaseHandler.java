package com.ProjectDetails;

import com.Login.BaseRequest;
import com.Login.BaseResponse;

public abstract class ProjectBaseHandler {
	protected BaseRequest req = null;
	protected BaseResponse response;
	
	public BaseResponse handle(BaseRequest req){
		this.req = req;
		process();
		return response;
	}

	protected abstract void process();
	

}
