package com.screen.thread;

import com.sun.jna.Platform;

public class ActivityAnalyzerFactory {
	
	public static ActivityAnalyzer getAnalyzer(){
		if(Platform.isWindows()){
			return new ActivityAnalizerWindows();
		}
		return null;
	}
}
