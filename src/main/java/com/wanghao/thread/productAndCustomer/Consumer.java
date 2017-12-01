package com.wanghao.thread.productAndCustomer;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

	private BlockingQueue<PCData> queue;
	private static final int SLEEPTIME=1000;
	
	public Consumer(BlockingQueue<PCData> queue) {
		// TODO Auto-generated constructor stub
		this.queue=queue;
	}
	
	public void run() {
		System.out.println("start consumer id ="+Thread.currentThread().getId());
		Random r=new Random();
		try {
			while(true){
				PCData data=queue.take();
				if(null!=data){
					int re=data.getData()*data.getData();
					System.out.println(MessageFormat.format("{0}*{1}={2}",data.getData(),data.getData(),re));
					Thread.sleep(r.nextInt(SLEEPTIME));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

}
