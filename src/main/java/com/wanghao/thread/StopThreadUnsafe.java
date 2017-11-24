package com.wanghao.thread;

public class StopThreadUnsafe {
	private static Object i=0;
	public static User u=new User();
	// 创建一个共享变量 一个内部类.
	public static class User{
		
		private int id;
		private String name;
		
		public User(){
			this.id=0;
			this.name="0";
		}
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + "]";
		}
	}
	
	//创建一个线程,用于修改user对象里面的内容
	public static class ChangeObjectThread extends Thread{
		
		volatile boolean isStop=false;
		
		public void stopMe(){
			this.isStop=true;
		}
		
		@Override
		public void run() {
			
			
				while(true){
					if(isStop){ //增加一个标志位,停止这个线程的时候 先执行一下t.stopMe(); 然后再执行t.stop() 这样就是手工的停止线程啦
						System.out.println("this thread is stop");
						break;
					}
					synchronized (u) {//对于共享的对象加锁.我只有拿到i这个对象锁,我才能进入下面的代码.
				int v=(int) (System.currentTimeMillis()/1000);
				u.setId(v);
				try {
					Thread.sleep(1000); //程序在sleep的时候,不会释放锁
				} catch (Exception e) {
				}
				u.setName(String.valueOf(v));
			}
			//Thread.yield();//让线程让出资源.
		}
			
			
					
		}
	}
	
	public static class ReadObjectThread extends Thread{
		
		@Override
		public void run() {
			
			while(true){
				synchronized (u) {
					System.out.println(u);
					if(u.getId()!=Integer.valueOf(u.getName())){
						System.out.println(u.toString());
					}
				}
			//	Thread.yield();
			}
			
		}
		
	}
	public static void main(String[] args) throws InterruptedException {
		new ReadObjectThread().start();
		new ChangeObjectThread().start();
		/*while(true){
			Thread t=new ChangeObjectThread();
			t.start();
			ChangeObjectThread.sleep(500);
			t.stop();
		}*/
	}

}
