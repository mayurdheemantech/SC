package com.screen.thread;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

import com.screen.DataCaptureObject;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;

interface Psapi extends StdCallLibrary {
	Psapi INSTANCE = (Psapi) Native.loadLibrary("Psapi", Psapi.class);

	WinDef.CHAR GetModuleBaseNameW(Pointer hProcess, Pointer hModule, char[] lpBaseName, int nSize);
}

interface User32A extends StdCallLibrary {
	User32A INSTANCE = (User32A) Native.loadLibrary("User32", User32A.class);

	HWND FindWindowExA(HWND hwndParent, HWND childAfter, String className, String windowName);

	HWND FindWindowExW(HWND hwndParent, HWND childAfter, String className, String windowName);

	HWND FindWindowA(String className, String windowName);

	int GetWindowText(HWND hWnd, char[] lpString, int nMaxCount);

	LRESULT SendMessageA(HWND paramHWND, int paramInt, WPARAM paramWPARAM, LPARAM paramLPARAM);

	HWND GetDesktopWindow();
}

public class ActivityAnalizerWindows implements ActivityAnalyzer {

	/* (non-Javadoc)
	 * @see com.screen.thread.ActivityAnalyzer#getCurrentActivity()
	 */
	@Override
	public DataCaptureObject getCurrentActivity() throws InterruptedException {
		final int PROCESS_VM_READ = 0x0010;
		final int PROCESS_QUERY_INFORMATION = 0x0400;
		final User32 user32 = User32.INSTANCE;
		WinDef.HWND windowHandle = user32.GetForegroundWindow();
		if(windowHandle == null){
			return null;
		}
		String name = getProcessName(PROCESS_VM_READ, PROCESS_QUERY_INFORMATION, windowHandle);
		String windowText = getWindowText(user32, windowHandle);
		DataCaptureObject obj = new DataCaptureObject(name, windowText, getCurrentTime());
		return obj;
	}

	private Date getCurrentTime() {
		return Calendar.getInstance().getTime();
	}

	private String getProcessName(final int PROCESS_VM_READ, final int PROCESS_QUERY_INFORMATION,
			WinDef.HWND windowHandle) {
		final User32 user32 = User32.INSTANCE;
		final Kernel32 kernel32 = Kernel32.INSTANCE;
		IntByReference pid = new IntByReference();
		user32.GetWindowThreadProcessId(windowHandle, pid);
		WinNT.HANDLE processHandle = kernel32.OpenProcess(PROCESS_VM_READ | PROCESS_QUERY_INFORMATION, true,
				pid.getValue());
		char[] filename = new char[1024];
		Psapi.INSTANCE.GetModuleBaseNameW(processHandle.getPointer(), Pointer.NULL, filename, filename.length);
		String name = Native.toString(filename);
		return name;
	}

	public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {

	}

	private String getWindowText(User32 user32, HWND hWnd) {
		char[] windowText = new char[1024];
		User32.INSTANCE.GetWindowText(hWnd, windowText, 512);
		String windowT = Native.toString(windowText);
		return windowT;
	}

}
