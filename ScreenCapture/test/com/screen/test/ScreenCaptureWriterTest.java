package com.screen.test;

import java.io.IOException;
import java.util.Calendar;

import org.junit.Test;

import com.screen.DataCaptureObject;
import com.screen.ScreenCaptureQueue;

public class ScreenCaptureWriterTest {

	@Test
	public void test() throws IOException, ClassNotFoundException, InterruptedException {
		final ScreenCaptureQueue<DataCaptureObject> writer = new ScreenCaptureQueue<DataCaptureObject>();
		
		new Thread(){
			public void run() {
				try {
					write(writer);
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};

		}.start();
		
		while(true){
			DataCaptureObject ob1 = (DataCaptureObject) writer.get();
			System.out.println(ob1);
			writer.remove();
		}
		
	}

	private void write(ScreenCaptureQueue<DataCaptureObject> writer) throws IOException, InterruptedException {
		for(int i=0; i<1000; i++){
			DataCaptureObject ob1 = new DataCaptureObject("sample" + i, "sample" + i, Calendar.getInstance().getTime());
			writer.put(ob1);
			Thread.sleep(20);
		}
	}

}
