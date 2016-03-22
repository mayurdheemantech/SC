package com.sample;

import java.io.UnsupportedEncodingException;

import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;

//interface Psapi extends StdCallLibrary {
//    Psapi INSTANCE = (Psapi) Native.loadLibrary("Psapi", Psapi.class);
//	
//    WinDef.CHAR GetModuleBaseNameW(Pointer hProcess, Pointer hModule, char[] lpBaseName, int nSize);
//}
//
//interface User32A extends StdCallLibrary {
//    User32A INSTANCE = (User32A) Native.loadLibrary("User32", User32A.class);    
//    
//    HWND FindWindowExA(HWND hwndParent, HWND childAfter, String className, String windowName);
//    HWND FindWindowExW(HWND hwndParent, HWND childAfter, String className, String windowName);
//    HWND FindWindowA(String className, String windowName);
//    int GetWindowText(HWND hWnd, char[] lpString, int nMaxCount);
//    LRESULT SendMessageA(HWND paramHWND, int paramInt, WPARAM paramWPARAM, LPARAM paramLPARAM);
//    HWND GetDesktopWindow();
//}




public class JNASample2 {

	public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
		if (Platform.isWindows()) {
	        final int PROCESS_VM_READ=0x0010;
	        final int PROCESS_QUERY_INFORMATION=0x0400;
	        final User32 user32 = User32.INSTANCE;
	        final Kernel32 kernel32=Kernel32.INSTANCE;
	        final Psapi psapi = Psapi.INSTANCE;
	        /*for(int i =0; i<10; i++){
	        	WinDef.HWND windowHandle=user32.GetForegroundWindow();
	        	IntByReference pid= new IntByReference();
	        	user32.GetWindowThreadProcessId(windowHandle, pid);
	        	WinNT.HANDLE processHandle=kernel32.OpenProcess(PROCESS_VM_READ | PROCESS_QUERY_INFORMATION, true, pid.getValue());
	        	char[] filename = new char[1024];
	        	Psapi.INSTANCE.GetModuleBaseNameW(processHandle.getPointer(), Pointer.NULL, filename, filename.length);
	        	
	        	String name = Native.toString(filename);
	        	System.out.println(name);
	        	Thread.sleep(1000);
	        }*/
			
	        for(int i =0; i<1; i++){
	        	Thread.sleep(1000);
//	        	HWND hWnd = user32.GetForegroundWindow();
//	        	printWindowDetails(user32, hWnd);

	        	

	        }
	        
	        user32.EnumWindows(printWindowRecNew(user32), null);
		
		}
		
		
	
	}

	private static void printWindowDetails(final User32 user32, HWND hWnd, WNDENUMPROC handle) {

		user32.EnumChildWindows(hWnd, handle, null);
	}

	private static WNDENUMPROC printWindowRec(final User32 user32) {
		return new User32.WNDENUMPROC() {

			int level;
			int count;
		    public boolean callback(HWND wnd, Pointer userData) {
				char[] windowText = new char[1024];
		    	char[] textBuffer = new char[512];
		        user32.GetClassName(wnd, textBuffer, 512);
		        User32.INSTANCE.GetWindowText(wnd, windowText, 512);
		    	String windowT = Native.toString(windowText);
		    	String className = Native.toString(textBuffer);
//		    	if(level >0 || className.contains("Chrome")){
		    		System.out.println(windowT);
			    	System.out.println(className);
//			    	level++;
//		    		printWindowDetails(user32, wnd, this);
//			    	level --;
//		    	}
//		    	printWindowDetails(user32, wnd, this);
		    	count++;
		        return true;
		    }
		};
	}
	
	
	
	private static WNDENUMPROC printWindowRecNew(final User32 user32) {
		return new User32.WNDENUMPROC() {

		    public boolean callback(HWND wnd, Pointer userData) {

		    	char[] textBuffer = new char[512];
		        user32.GetClassName(wnd, textBuffer, 512);
		    	String className = Native.toString(textBuffer);
		    	if(className.contains("Chrome")){

		    		HWND main = User32A.INSTANCE.GetDesktopWindow();

		    		HWND hwnd1 = User32A.INSTANCE.FindWindowExA(null, null, "Chrome_WidgetWin_1", null);
		    		HWND hwnd2 = User32A.INSTANCE.FindWindowExA(main, hwnd1, "Chrome_WidgetWin_1", null);
		    		HWND hAddressBox = User32A.INSTANCE.FindWindowExA(hwnd2, null, "SysListView32", null);
		    		printWindowDetails(user32, hwnd1, printWindowRec(user32));
		    		//		    	HWND hAddressBox = User32A.INSTANCE.FindWindowExA(wnd, null, "Chrome_WidgetWin_1", null );
		    		char[] windowText = new char[1024];
		    		char[] textBuffer1 = new char[512];
		    		user32.GetClassName(hAddressBox, textBuffer, 512);
		    		User32.INSTANCE.GetWindowText(hAddressBox, windowText, 512);
		    		String windowT = Native.toString(windowText);
		    		String className1 = Native.toString(textBuffer1);
		    		System.out.println(windowT);
		    		System.out.println(className1);
		    	}
		        return true;
		    }
		};
	}
}
