package com.screen;

import java.io.File;
import java.io.IOException;

import com.squareup.tape.QueueFile;

public class ScreenCaptureWriter<E extends Writable>{

	private final QueueFile file;

	public ScreenCaptureWriter() throws IOException {
		file = new QueueFile(new File("data.log"));
	}

	public void addEntry(E t) throws IOException{
		file.add(t.toByteArray());
	}
	
	public Writable getNext() throws IOException, ClassNotFoundException{
		return DataCaptureObject.fromByteArray(file.peek());
	}

	public boolean hasNext(){
		return !file.isEmpty();
	}
	
	public void remove() throws IOException, ClassNotFoundException{
		file.remove();
	}

	public boolean isFull() {
		//this Q is never going to be full.
		
		return false;
	}
	
}
