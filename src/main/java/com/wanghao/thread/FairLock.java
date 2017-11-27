package com.wanghao.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁:
 * 	在大多数情况下,锁的申请都是非公平的, 也就是说  线程1 首先请求了锁A,接着线程2也请求了锁A,  那么当锁A可用时, 是线程1请求的到呢?还是线程2请求的到呢?
 * 答案是不一定的,系统会随机挑选一个线程,让它只有锁A,因此不能保证其公平性
 * 比如: 大家去售票窗口买票,也不排队 大家乱哄哄的扎在一起, 售票员只能看见一只手就卖一张.这样来.
 * 对于公平锁,则不会这样.
 * 	特点:不会产生饥饿.,只要你排队.最终还是可以等到资源的.
 * 	我们使用synchronize 关键字进行锁控制,是产生的非公平锁.  
 * 重入锁是允许我们使用公平锁的, public ReentrantLock(true) 的时候,表示锁是公平锁, 公平锁看起来优美, 但是必然要维护一个有序队列,
 * 因此性能比较地下.
 * @author Administrator
 *
 */
public class FairLock implements Runnable {
	//true表明这是一个公平锁    false:非公平锁
	public static ReentrantLock lock=new ReentrantLock(true); 
	
	public void run() {
		while(true){
			try {
				lock.lock();
				System.out.println(Thread.currentThread().getName()+"获得锁");
			} catch (Exception e) {
				
			}finally {
				if(lock.isHeldByCurrentThread())
				{
					System.out.println(Thread.currentThread().getName()+"释放锁");
					lock.unlock();
				}
				
			}	
		}
		
	}
	
	public static void main(String[] args) {
		FairLock fl=new FairLock();
		Thread t1=new Thread(fl, "线程1");
		Thread t2=new Thread(fl, "线程2");
		t1.start();
		t2.start();
		Thread t3=new Thread(fl, "线程3");
		Thread t4=new Thread(fl, "线程4");
		t3.start();
		t4.start();
	}

}
