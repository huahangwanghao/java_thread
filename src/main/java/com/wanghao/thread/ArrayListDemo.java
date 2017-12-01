package com.wanghao.thread;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 对于ArrayList进行测试
 * @author Administrator
 *
 */
public class ArrayListDemo {

	static ArrayList<Integer> al=new   ArrayList<Integer>(10);
	
	//static ArrayList<Integer> al=(ArrayList<Integer>) Collections.synchronizedList(new   ArrayList<Integer>(10));
	public static class AddThread implements Runnable{

		public void run() {
				for(int i=0;i<20;i++){
					al.add(i);
				}
		}
	}
	
	public static void main(String[] args) throws Exception {

			Thread t1=new Thread(new AddThread());
			Thread t2=new Thread(new AddThread());
			t1.start();
			t2.start();
			t1.join();
			t2.join();
			System.out.println(al.size());
	}

}
