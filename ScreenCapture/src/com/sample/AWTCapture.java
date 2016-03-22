package com.sample;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class AWTCapture {
  public static void main(String args[]) throws
           AWTException, IOException, InterruptedException {

	  for (int i = 0; i < 10; i++) {

		  // capture the whole screen
		  BufferedImage screencapture = new Robot().createScreenCapture(
				  new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()) );

		  // Save as JPEG
		  File file = new File("screencapture_"+ i +".jpg");
		  ImageIO.write(screencapture, "jpg", file);
		  Thread.sleep(1000);
		  // Save as PNG
		  // File file = new File("screencapture.png");
		  // ImageIO.write(screencapture, "png", file);
	  }
  }
}