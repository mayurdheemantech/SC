package com.screen;

import java.util.Date;

public class DataCaptureObject extends DataCaptureBaseObject {

	public int getKbcount() {
		return kbcount;
	}

	public void setKbcount(int kbcount) {
		this.kbcount = kbcount;
	}

	public int getMouseCount() {
		return mouseCount;
	}

	public void setMouseCount(int mouseCount) {
		this.mouseCount = mouseCount;
	}

	@Override
	public String toString() {
		return "DataCaptureObject [application=" + application
				+ ", windowText=" + windowText + ", time=" + time
				+ ", kbcount=" + kbcount + ", mouseCount=" + mouseCount + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5091616781095612081L;
	
	private String application;
	
	private String windowText;
	
	private Date time;
	
	int kbcount = 0;
	
	int mouseCount = 0;

	public DataCaptureObject(String application, String windowText, Date time) {
		super();
		this.application = application;
		this.windowText = windowText;
		this.time = time;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getWindowText() {
		return windowText;
	}

	public void setWindowText(String windowText) {
		this.windowText = windowText;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	

}
