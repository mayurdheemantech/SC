package com.Common;

public abstract class BaseHandler {

	protected BaseRequest req = null;
	protected BaseResponse response;
	
	public BaseResponse handle(BaseRequest req){
		this.req = req;
		process();
		return response;
	}

	protected abstract void process();
}
