package com.wanghao.thread;

public class NewThread implements Runnable {

	
	
	public void run() {
		System.out.println("新线程:"+Thread.currentThread().getName());

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread(new NewThread()).start();
		System.out.println("主方法:"+Thread.currentThread().getName());

	}

}
