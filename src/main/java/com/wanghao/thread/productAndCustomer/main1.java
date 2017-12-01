package com.wanghao.thread.productAndCustomer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class main1 {

	public static void test(){
		Product product1=new Product(MainDemo.queue);
		Product product2=new Product(MainDemo.queue);
		Product product3=new Product(MainDemo.queue);
		Product product4=new Product(MainDemo.queue);
		Product product5=new Product(MainDemo.queue);
		Product product6=new Product(MainDemo.queue);
		Product product7=new Product(MainDemo.queue);
		ExecutorService es = Executors.newCachedThreadPool();
		es.execute(product1);
		es.execute(product2);
		es.execute(product3);
		es.execute(product4);
		es.execute(product5);
		es.execute(product6);
	}
	
	public static void main(String[] args) {
		Product product1=new Product(MainDemo.queue);
		Product product2=new Product(MainDemo.queue);
		Product product3=new Product(MainDemo.queue);
		ExecutorService es = Executors.newCachedThreadPool();
		es.execute(product1);
		es.execute(product2);
		es.execute(product3);
	}
}
