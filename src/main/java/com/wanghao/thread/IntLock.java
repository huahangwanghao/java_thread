package com.wanghao.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁中 中断的使用
 * @author Administrator
 *
 */
public class IntLock implements Runnable {

	public static ReentrantLock lock1=new ReentrantLock();
	public static ReentrantLock lock2=new ReentrantLock();
	
	int lock;
	
	public IntLock(int lock){
		this.lock=lock;
	}
	
	public void run() {
		try {
			//对于下面代码是这么理解, 最新面创建了俩个线程t1 / t2 t1进来的时候进入if里面, 获取到了lock1的锁,然后等500毫秒, 这个过程中 t2 进入else 里面, 得到了lock2的锁, 然后就死锁啦.
			//因为之后 t1.还想要lock2 但是  lock2被t2占着呢.  t2还想要lock1 但是 lock1被t1 占着呢. 俩线程互相死等啦. .直到最后 t2. 打断啦. 然后t2 进入了finnaly里面 释放了lock2
			if(lock==1){
				lock1.lockInterruptibly(); //对于锁的请求,使用这个方法,这是一个可以对中断进行相应的申请动作.,既在等待锁的过程中,可以响应中断
				try {
					Thread.sleep(500);
				} catch (Exception e) {}
				lock2.lockInterruptibly();
			}else{
				lock2.lockInterruptibly();
				try {
					Thread.sleep(500);
				} catch (Exception e) {}
				lock1.lockInterruptibly();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(lock1.isHeldByCurrentThread()){ //  isHeldByCurrentThread 查询当前线程是否保持此锁定 
				lock1.unlock();
			}
			if(lock2.isHeldByCurrentThread()){
				lock2.unlock();
			}
			System.out.println(Thread.currentThread().getId()+"线程退出!");
		}
	}

	public static void main(String[] args) throws InterruptedException {

		IntLock r1=new IntLock(1);
		IntLock r2=new IntLock(2);
		
		Thread t1=new Thread(r1);
		Thread t2=new Thread(r2);
		t1.start();
		t2.start();
		Thread.sleep(1000);
		t2.interrupt();
		
	}

}
