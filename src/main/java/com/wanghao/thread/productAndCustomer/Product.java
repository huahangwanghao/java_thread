package com.wanghao.thread.productAndCustomer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者
 * @author Administrator
 *
 */
public class Product implements Runnable {

	private volatile boolean isRunnint = true;
	private BlockingQueue<PCData> queue;
	private static AtomicInteger count=new AtomicInteger();
	private static final int SLEEPTIME=1000;
	
	public Product(BlockingQueue<PCData> queue) {
		this.queue=queue;
	}
	
	public void run() {
		PCData data=null;
		Random r=new Random();
		System.out.println("start producter id ="+ Thread.currentThread().getId());
		try {
			while(isRunnint){
				Thread.sleep(r.nextInt(SLEEPTIME));
				data=new PCData(count.incrementAndGet());
				System.out.println(data+"is put into queue");
				if(!queue.offer(data, 2, TimeUnit.SECONDS)){
					System.err.println("fail to put data"+data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
		
	}
	
	public void stop(){
		isRunnint=false;
	}

}
