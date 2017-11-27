package com.wanghao.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch 是一个非常实用的多线程控制工具类,
 * 这个工具通常用来控制线程等待,它可以让某一个线程一直等到,直到倒计时结束,再开始执行.
 * @author Administrator
 *
 */
public class CountDownLatchDemo implements Runnable {
	//表示需要有10个线程完成任务, 等待在CountDownLatch上的线程才能继续执行, 每次调用countDown()方法,计算器减1,计数器大于0时, await()方法 会阻塞程序继续进行
	public static final CountDownLatch end =new CountDownLatch(30);
	
	public static final CountDownLatchDemo demo=new CountDownLatchDemo();
	
	public void run() {
		try {
			//模拟检查任务
			Thread.sleep(new Random().nextInt(10)*1000);
			System.out.println("check complete|线程id: "+Thread.currentThread().getId());
			end.countDown(); //通知CountDownLatch  表示一个线程已经完成了任务  计数器可以减1
			System.out.println(end.getCount());  //每次调用countDown()之后, 这个计数器就会减去1
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec=Executors.newFixedThreadPool(11);
		for (int i=0;i<11;i++){
			exec.submit(demo);
		}
		end.await();  //要求主线成等待所有的10个检查任务全部完成, 全部任务完成后主线程才能继续执行.
		
		System.out.println("fire");
		exec.shutdown();
	}

}
