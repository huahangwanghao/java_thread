package com.wanghao.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/***
 * 除了等待外部通知之外,避免死锁还有一个方法,就是使用限时等待.
 * 举例: 如果约了人, 我们到达地点后 朋友迟迟不来, 我们等到1~2小时就会自己走啦. 这就是超时操作.
 * 通常,我们无法判断一个线程是死锁啦,还是产生了饥饿. 但是如果给一个线程一个等待时间,超过这个等待时间就让线程自动放弃, 这么来说对于系统是有意义的.
 * 我们可以使用tryLock来实现
 * @author Administrator
 *
 */
public class TimeLockDemo implements Runnable{

	public static ReentrantLock lock=new ReentrantLock();

	public void run() {
		try {
			if(lock.tryLock(5, TimeUnit.SECONDS)){  //这个是让线程等待5秒中,如果一直尝试访问不到,就返回false
			//if(lock.tryLock()){  //如果不加时间控制,意思是 里面判断如果获取不到就返回false
				System.out.println(Thread.currentThread().getName()+"i have get lock!---->"+new Date());
				Thread.sleep(4000);
			}else{
				System.out.println(Thread.currentThread().getName()+"get lock fail----->"+new Date());
			}
		} catch (Exception e) {
			
		}finally {
			if(lock.isHeldByCurrentThread()){
				//查询线程是否还持有此锁
				lock.unlock();
			}
		}
	}
	public static void main(String[] args) {
		TimeLockDemo tld=new TimeLockDemo();
		Thread t1=new Thread(tld);
		t1.setName("线程1");
		Thread t2=new Thread(tld);
		t2.setName("线程2");
		t1.start();
		t2.start();
	}
}
