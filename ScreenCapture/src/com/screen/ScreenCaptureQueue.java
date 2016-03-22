package com.screen;

import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ScreenCaptureQueue<T extends Writable> {

	private ScreenCaptureWriter<T> writer;

	private Condition isFullCondition;
	private Condition isEmptyCondition;
	private Lock lock;

	public ScreenCaptureQueue() throws IOException {
		this(Integer.MAX_VALUE);
	}

	public ScreenCaptureQueue(int limit) throws IOException {
		writer = new ScreenCaptureWriter<>();
		lock = new ReentrantLock();
		isFullCondition = lock.newCondition();
		isEmptyCondition = lock.newCondition();
	}

	public void put(T t) throws IOException {
		lock.lock();
		try {
			while (isFull()) {
				try {
					isFullCondition.await();
				} catch (InterruptedException ex) {
				}
			}
			writer.addEntry(t);
			isEmptyCondition.signalAll();
		} finally {
			lock.unlock();
		}
	}

	private boolean isFull() {
		return writer.isFull();
	}

	public void remove() throws ClassNotFoundException, IOException {
		lock.lock();
		try {
			while (isEmpty()) {
				try {
					isEmptyCondition.await();
				} catch (InterruptedException ex) {
				}
			}
			writer.remove();
			isFullCondition.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public T get() throws ClassNotFoundException, IOException {

		T t = null;
		lock.lock();
		try {
			while (isEmpty()) {
				try {
					isEmptyCondition.await();
				} catch (InterruptedException ex) {
				}
			}
			t = (T) writer.getNext();
		} finally {
			lock.unlock();
		}
		return t;

	}

	private boolean isEmpty() {
		return !writer.hasNext();
	}

}
