package com.wanghao.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁的测试
 * @author Administrator
 *
 */
public class ChongRuSuo implements Runnable {

	public static ReentrantLock lock=new ReentrantLock();
	public static int i=0;
	public void run() {
		for (int j=0;j<1000000;j++){
			lock.lock();
			try {
				i++;
			} finally {										
				lock.unlock();
			}
		}
	}
	public static void main(String[] args) throws Exception {
		Thread t1=new Thread(new ChongRuSuo());
		Thread t2=new Thread(new ChongRuSuo());
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}







}
