package com.screen.thread;

import java.io.IOException;

import com.screen.DataCaptureObject;
import com.screen.ScreenCaptureQueue;

public class ActivitySynchronizerThread implements Runnable {

	private final ScreenCaptureQueue<DataCaptureObject> Q; 
	
	WSCommunicationHandler handler = new WSCommunicationHandler();
	
	
	public ActivitySynchronizerThread(ScreenCaptureQueue<DataCaptureObject> Q) throws IOException {
		super();
		this.Q = Q;
	}



	@Override
	public void run() {
		while(true){
			try {
				DataCaptureObject obj = Q.get();
				if(handler.handle(obj)){
					Q.remove();
				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}
