package com.Common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import com.screen.thread.ActivityCaptureThread;
import com.screen.thread.ActivitySynchronizerThread;
import com.util.Util;

public class Main {
	
	private static Thread captureThread;
	
	
  public static void main(String[] args) throws Exception {
    if (!SystemTray.isSupported()) {
      return;
    }
    SystemTray tray = SystemTray.getSystemTray();

    Dimension size = tray.getTrayIconSize();
    BufferedImage bi = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
    Graphics g = bi.getGraphics();

    g.setColor(Color.blue);
    g.fillRect(0, 0, size.width, size.height);

    ActionListener exitActionListner;
	PopupMenu popup = getPopupMenu();

    TrayIcon ti = new TrayIcon(bi, "System Tray Demo #2", popup);

    exitActionListner = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
      }
    };
    ti.setActionCommand("My Icon");
    ti.addActionListener(exitActionListner);

  /**/
    tray.add(ti);
    
    
    //start sync
    Thread th = new Thread(new ActivitySynchronizerThread(Util.getQ()));
    th.start();
    
    
    
  }


private static PopupMenu getPopupMenu() {
	PopupMenu popup = new PopupMenu();
    MenuItem Exit = new MenuItem("Exit");
    
    MenuItem aboutItem = new MenuItem("About");
    MenuItem Start = new MenuItem("Start");
    MenuItem Stop = new MenuItem("Stop");
    MenuItem Open = new MenuItem("Open");
	MenuItem Option = new MenuItem("Option");
	MenuItem SyncNow = new MenuItem("Sync Now");
	MenuItem AutoSyncr = new MenuItem("Auto Syncr");
	
	popup.add(aboutItem);
	popup.add(Start);
	popup.add(Stop);
	popup.add(Open);
	popup.add(Option);
	popup.add(SyncNow);
	popup.add(AutoSyncr);
    
    
	ActionListener startActionListener = getStartActionListener();
    Start.addActionListener(startActionListener);
    
    
    ActionListener exitActionListner;
    exitActionListner = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    };
    Exit.addActionListener(exitActionListner);
    popup.add(Exit);
    
    
    
    ActionListener stopActionListener;
    stopActionListener = new ActionListener()
    {

		public void actionPerformed(ActionEvent e) {
			if(captureThread != null && captureThread.isAlive()){
				captureThread.interrupt();
			}
    	}
    };
    Stop.addActionListener(stopActionListener);
	return popup;
}


private static ActionListener getStartActionListener() {
	ActionListener startActionListener = new ActionListener()
    {
		public void actionPerformed(ActionEvent e) {
			if(captureThread == null || !captureThread.isAlive()){
				ActivityCaptureThread thread = new ActivityCaptureThread(Util.getQ());
				captureThread = new Thread(thread);
				captureThread.start();
			}
    	}
    };
	return startActionListener;
}
}