package com.util;

import java.io.IOException;

import com.screen.DataCaptureObject;
import com.screen.ScreenCaptureQueue;

public class Util {
	private static ScreenCaptureQueue<DataCaptureObject> q = null;
	
	static{
		try {
			q = new ScreenCaptureQueue<>();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ScreenCaptureQueue<DataCaptureObject> getQ() {
		return q;
	}

}
