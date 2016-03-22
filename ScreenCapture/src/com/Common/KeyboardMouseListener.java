package com.Common;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;

import com.screen.thread.ActivityCaptureThread;


public class KeyboardMouseListener implements NativeKeyListener, NativeMouseInputListener, NativeMouseWheelListener {
	
	
	private ActivityCaptureThread th;
	private static final Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());

	public KeyboardMouseListener(ActivityCaptureThread th) {
		super();
		this.th = th;
	
		logger.setUseParentHandlers(false);
		logger.setLevel(Level.ALL);

		// Add our custom formatter to a console handler.
		ConsoleHandler handler = new ConsoleHandler();
		handler.setLevel(Level.OFF);
		logger.addHandler(handler);

		
	}

	/**
	 * @throws NativeHookException 
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	public void register() throws NativeHookException {
		GlobalScreen.registerNativeHook();
		GlobalScreen.addNativeKeyListener(this);
		GlobalScreen.addNativeMouseListener(this);
		GlobalScreen.addNativeMouseWheelListener(this);
		GlobalScreen.addNativeMouseMotionListener(this);
	}

	/**
	 * @throws NativeHookException 
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	public void deregister() throws NativeHookException {
		GlobalScreen.unregisterNativeHook();
		GlobalScreen.removeNativeKeyListener(this);
		GlobalScreen.removeNativeMouseListener(this);
		GlobalScreen.removeNativeMouseWheelListener(this);
		GlobalScreen.removeNativeMouseMotionListener(this);
	}
	
	/**
	 * @see org.jnativehook.keyboard.NativeKeyListener#nativeKeyPressed(org.jnativehook.keyboard.NativeKeyEvent)
	 */
	public void nativeKeyPressed(NativeKeyEvent e) {
		
	}

	/**
	 * @see org.jnativehook.keyboard.NativeKeyListener#nativeKeyReleased(org.jnativehook.keyboard.NativeKeyEvent)
	 */
	public void nativeKeyReleased(NativeKeyEvent e) {

	}

	/**
	 * @see org.jnativehook.keyboard.NativeKeyListener#nativeKeyTyped(org.jnativehook.keyboard.NativeKeyEvent)
	 */
	public void nativeKeyTyped(NativeKeyEvent e) {
		th.getKeyboardKey().incrementAndGet();
	}

	/**
	 * @see org.jnativehook.mouse.NativeMouseListener#nativeMouseClicked(org.jnativehook.mouse.NativeMouseEvent)
	 */
	public void nativeMouseClicked(NativeMouseEvent e) {
		th.getMouseKey().incrementAndGet();
	}

	/**
	 * @see org.jnativehook.mouse.NativeMouseListener#nativeMousePressed(org.jnativehook.mouse.NativeMouseEvent)
	 */
	public void nativeMousePressed(NativeMouseEvent e) {
	//	displayEventInfo(e);
	}

	/**
	 * @see org.jnativehook.mouse.NativeMouseListener#nativeMouseReleased(org.jnativehook.mouse.NativeMouseEvent)
	 */
	public void nativeMouseReleased(NativeMouseEvent e) {
	}

	/**
	 * @see org.jnativehook.mouse.NativeMouseMotionListener#nativeMouseMoved(org.jnativehook.mouse.NativeMouseEvent)
	 */
	public void nativeMouseMoved(NativeMouseEvent e) {
	}

	/**
	 * @see org.jnativehook.mouse.NativeMouseMotionListener#nativeMouseDragged(org.jnativehook.mouse.NativeMouseEvent)
	 */
	public void nativeMouseDragged(NativeMouseEvent e) {
	}

	/**
	 * @see org.jnativehook.mouse.NativeMouseWheelListener#nativeMouseWheelMoved(org.jnativehook.mouse.NativeMouseWheelEvent)
	 */
	public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
		th.getMouseKey().incrementAndGet();
	}

}
