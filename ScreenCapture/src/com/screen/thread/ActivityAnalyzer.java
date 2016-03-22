package com.screen.thread;

import com.screen.DataCaptureObject;

public interface ActivityAnalyzer {

	DataCaptureObject getCurrentActivity() throws InterruptedException;

}