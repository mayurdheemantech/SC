package com.screen;

import java.io.IOException;
import java.io.Serializable;

public interface Writable extends Serializable{
	public byte[] toByteArray() throws IOException;
	
//	public Writable fromByteArray(byte[] arr) throws IOException, ClassNotFoundException;
}
