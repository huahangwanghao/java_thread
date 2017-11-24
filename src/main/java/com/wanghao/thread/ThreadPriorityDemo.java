package com.wanghao.thread;
/**
 * 线程优先级demo类
 * @author Administrator
 *
 */
public class ThreadPriorityDemo {

	private static Object obj=new Object();
	
	public static class HightThread extends Thread{
		static int count=0;
			public void run() {
				while(true){
					synchronized (obj) {
						count++;
						if(count>1000000){
							System.out.println("HightThread is end");
							break;
						}
					}
				}
			}
	}
	

	public static class LowThread extends Thread{
		static int count=0;
			public void run() {
				while(true){
					synchronized (obj) {
						count++;
						if(count>1000000){
							System.out.println("LowThread is end");
							break;
						}
					}
				}
			}
	}
	
	public static void main(String[] args) {
			HightThread hightThread=new HightThread();
			LowThread lowThread =new LowThread();
			hightThread.setPriority(Thread.MAX_PRIORITY);
			lowThread.setPriority(Thread.MIN_PRIORITY);
			lowThread.start();
			hightThread.start();
	}

}
