package com.wanghao.thread;
/**
 * Object对象中的wait和notify 方法的测试类
 * @author Administrator
 *
 *注意点
 *	1:wait和notify这俩个方法都是在Object对象中.当一个对象调用的wait以后,比如线程A 调用了 obj.wait().那么线程A就会进入等待状态.
 *何时结束呢, 直到有线程调用了 obj.notify() 这时候obj对象成了多个线程通信的桥梁.
 *原理:
 *	如果一个线程调用了obj.wait(),那么这个线程会进入obj的等待队列中,可能会有多个线程在这个等待队列中.当obj.notify()被调用的时候,会在这个
 *线程中随机唤醒一个线程.这个选择是一个随机的操作.
 *
 *注意点:object.wait不是随便调用的.它必须包含在对应的synchronized语句中.无论是wait还是notify 都需要首先获取目标对象锁
 *	可以这么理解.如果想调用这个方法, 你必须已经现在完成操作了这个对象,否则 别人正在操作这个对象. 你调用了
 */
public class ObjectWaitOrNotifyTest {

	private final static Object obj=new Object();//创建一个共享的对象
	
	//创建一个线程T1
	public static class T1 extends Thread{
		@Override
		public void run() {
			synchronized (obj) {
				System.out.println(System.currentTimeMillis()+"  T1 start");
				try {
					System.out.println(System.currentTimeMillis()+"  T1 调用wait()  进入等待队列,释放资源");
					obj.wait(); //会释放锁资源. sleep不会释放资源.
				} catch (Exception e) {
					
				}
				//obj.notify();   //这时候T2假如把T1唤醒啦. T1 醒来之后执行完了自己的代码, 这时候obj的队列里面还有一个T3线程呢,如果不执行T3就死在那里啦. 控制台的T3 end 一直没有打印出来
				System.out.println(System.currentTimeMillis()+"  T1 end");
			}
		}
	};
	
	//创建一个线程T1
	public static class T3 extends Thread{
		@Override
		public void run() {
			synchronized (obj) {
				System.out.println(System.currentTimeMillis()+"  T3 start");
				try {
					System.out.println(System.currentTimeMillis()+"  T3 调用wait()  进入等待队列,释放资源");
					obj.wait(); //会释放锁资源. sleep不会释放资源.
				} catch (Exception e) {
				}
				//obj.notify();
				System.out.println(System.currentTimeMillis()+"  T3 end");
			}
		}
	};

	//创建一个线程T2
	public static class T2 extends Thread{
		@Override
		public void run() {
			synchronized (obj) {
				System.out.println(System.currentTimeMillis()+"  T2 start");
				obj.notifyAll();//通知obj队列中的线程可以出来啦.  如果使用notify, 因为T1和T3最后没有notify的代码, 所以只能活一下,如果执行notifyall 时候 俩个就算没有notify代码也能活
				try {
					Thread.sleep(2000); //T2 线程睡了2s 之后再出来吧.
					System.out.println(System.currentTimeMillis()+"  T2 睡醒了, 先把T2的锁释放了后,才能让T1活过来");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(System.currentTimeMillis()+"  T2 end");
			}
		}
	};
	
	public static void main(String[] args) {
			Thread t1=new T1();
			Thread t2=new T2();
			Thread t3=new T3();
			t1.start();
			t2.start();
			t3.start();
	}

}
