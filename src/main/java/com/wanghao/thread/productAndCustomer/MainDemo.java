package com.wanghao.thread.productAndCustomer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class MainDemo {

	public static BlockingQueue<PCData> queue=new LinkedBlockingQueue<PCData>(10);;
	
	public static void main(String[] args) throws Exception {
		Product product1=new Product(queue);
		Product product2=new Product(queue);
		Product product3=new Product(queue);
		Consumer consumer1=new Consumer(queue);
		Consumer consumer2=new Consumer(queue);
		Consumer consumer3=new Consumer(queue);
		ExecutorService es = Executors.newCachedThreadPool();
//		es.execute(product1);
//		es.execute(product2);
//		es.execute(product3);
		es.execute(consumer1);
		es.execute(consumer2);
		es.execute(consumer3);
		Thread.sleep(1000*10);
		product1.stop();
		product2.stop();
		product3.stop();
		main1.test();
	//	es.shutdown();
	}

}
