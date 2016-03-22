package com.screen;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataCaptureBaseObject implements Writable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8180766280829972367L;


	@Override
	public byte[] toByteArray() throws IOException {
		return serialize(this);
	}

	
	public static byte[] serialize(Object obj) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(obj);
		return out.toByteArray();
	}

	public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream is = new ObjectInputStream(in);
		return is.readObject();
	}


	public static DataCaptureBaseObject fromByteArray(byte[] arr) throws IOException, ClassNotFoundException {
		return (DataCaptureBaseObject) deserialize(arr);
	}

	
	
}
