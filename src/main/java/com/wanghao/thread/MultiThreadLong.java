package com.wanghao.thread;

public class MultiThreadLong {

	public static long t=0L;
	
	
	//定义一个改变
	public static class ChangeT implements  Runnable {
		
		private long to;
		public ChangeT(long to) {
			// TODO Auto-generated constructor stub
			this.to=to;
		}
		
		@Override
		public void run() {
			while(true){
				MultiThreadLong.t=to;
				Thread.yield();
			}
		}
	}
	
	public static class ReadT implements Runnable{
		@Override
		public void run() {
			while(true){
				long tmp=MultiThreadLong.t;
				if(tmp!=111L&&tmp!=-999L){
					System.out.println(tmp);
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		ChangeT t1=new ChangeT(111L);
		ChangeT t2=new ChangeT(-999L);
		new Thread(t1).start();
		new Thread(t2).start();
		new Thread(new ReadT()).start();
		//如果是32位的jdk,可能会出现问题. 但是现在咱是64位的jdk.所有没有问题
		System.out.println("this is main");
	}

}
