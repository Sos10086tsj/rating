package com.chinesedreamer.rating.common.utils;

public class ThreadUtils {
	public static Thread getThread(long threadId){
		ThreadGroup tg = Thread.currentThread().getThreadGroup();
		while (null != tg) {
			Thread[] threads = new Thread[(int)(tg.activeCount() * 1.2)];
			int cout = tg.enumerate(threads, true);
			for (int i = 0; i < cout; i++) {
				if (threadId == threads[i].getId()) {
					return threads[i];
				}
			}
			tg = tg.getParent();
		}
		return null;
	}
	
	public static Thread getThread(String threadName){
		ThreadGroup tg = Thread.currentThread().getThreadGroup();
		while (null != tg) {
			Thread[] threads = new Thread[(int)(tg.activeCount() * 1.2)];
			int cout = tg.enumerate(threads, true);
			for (int i = 0; i < cout; i++) {
				if (threadName.equals(threads[i].getName())) {
					return threads[i];
				}
			}
			tg = tg.getParent();
		}
		return null;
	}
}
