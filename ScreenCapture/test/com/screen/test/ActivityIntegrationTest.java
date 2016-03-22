package com.screen.test;

import java.io.IOException;

import org.junit.Test;

import com.screen.DataCaptureObject;
import com.screen.ScreenCaptureQueue;
import com.screen.thread.ActivityCaptureThread;
import com.screen.thread.ActivitySynchronizerThread;

public class ActivityIntegrationTest {

	@Test
	public void test() throws IOException, InterruptedException {
		ScreenCaptureQueue<DataCaptureObject> q = new ScreenCaptureQueue<>();
		ActivityCaptureThread thread = new ActivityCaptureThread(q);
		Thread main = new Thread(thread);
		main.start();
		
		main.interrupt();
		
		ActivitySynchronizerThread syncThread = new ActivitySynchronizerThread(q);
		new Thread(syncThread).start();
		
		main.join();
		
	}

}
