package com.screen.thread;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import com.Common.KeyboardMouseListener;
import com.screen.DataCaptureObject;
import com.screen.ScreenCaptureQueue;

public class ActivityCaptureThread implements Runnable {

	private ActivityAnalyzer analyzer = ActivityAnalyzerFactory.getAnalyzer();
	
	private final ScreenCaptureQueue<DataCaptureObject> Q; 
	
	private AtomicInteger KeyboardKey = new AtomicInteger();
	
	private AtomicInteger MouseKey = new AtomicInteger();

	Thread MAT = null;	

	
	public AtomicInteger getKeyboardKey() {
		return KeyboardKey;
	}


	public AtomicInteger getMouseKey() {
		return MouseKey;
	}
	
	public ActivityCaptureThread(ScreenCaptureQueue<DataCaptureObject> q) {
		super();
		Q = q;
	}

	
	
	
	@Override
	public void run() {
		try{
			KeyboardMouseListener listener = new KeyboardMouseListener(this);
			listener.register();
			while(true){
				try {
					DataCaptureObject obj = analyzer.getCurrentActivity();
					if(obj == null){
						continue;
					}
					obj.setMouseCount(MouseKey.get());
					MouseKey.set(0);
					obj.setKbcount(KeyboardKey.get());
					KeyboardKey.set(0);
					Q.put(obj);
					if(Thread.interrupted()){
						listener.deregister();
						return;
					}
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					listener.deregister();
					return;
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}