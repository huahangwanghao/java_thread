package com.wanghao.thread;

public class IntegerDemo implements Runnable {
	//在java中Integer属于不变对象,也就是对象一旦被创建,就不可能被修改,如果Integer m=1.那么它就永远是1.如果想让m=2. 就是新创建一个Integer m=2
	public static Integer i=0;
	public static Integer i1=0;
	public static void main(String[] args) throws Exception {
			Thread t1=new Thread(new IntegerDemo());
			Thread t2=new Thread(new IntegerDemo());
			t1.start();
			t2.start();
			t1.join();
			t2.join();
			System.out.println(i);
	}

	public void run() {
		for (int j=0;j<10000000;j++){
			synchronized (i1) {
				i++;
			}
		}
	}

}
