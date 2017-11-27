package com.wanghao.thread;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.wanghao.thread.utils.TimeUtils;

/**
 * 读写锁
 * 	在大部分情况下,读线程和读线程 不应该互斥的.
 * @author Administrator
 *
 */
public class ReadWriteLockDemo {

	public static ReentrantLock lock =new ReentrantLock();
	private static ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
	private static Lock readLock=readWriteLock.readLock();
	private static Lock writeLock=readWriteLock.writeLock();
	private int value;
	
	
	/**
	 * 模拟一个对一个共享变量的读操作.
	 * @param lock
	 * @return
	 * @throws InterruptedException
	 */
	public Object handleRead(Lock lock) throws InterruptedException{
		try {
			lock.lock();
			Thread.sleep(1000);
			return value;
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * 模拟对一个共享变量的写操作
	 * @param lock
	 * @param index
	 * @throws InterruptedException
	 */
	public void handleWrite(Lock lock,int index) throws InterruptedException{
		try {
			lock.lock();
			Thread.sleep(1000);
			value=index;
		} finally {
			lock.unlock();
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		final ReadWriteLockDemo demo=new ReadWriteLockDemo();
		Runnable readRunnable=new Runnable() {
			
			public void run() {
				try {
					//demo.handleRead(readLock);
					demo.handleRead(lock);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		
		Runnable writeRunnable=new Runnable() {
			
			public void run() {

				try {
					//demo.handleWrite(writeLock, new Random().nextInt());
					demo.handleWrite(lock, new Random().nextInt());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		
		//模拟18个线程进行读
		for(int i=0;i<18;i++){
			new Thread(readRunnable).start();
		}
		//模拟2线程进行写
		for(int i=18;i<20;i++){
			new Thread(writeRunnable).start();
		}
/**
 * 如果是用了重入锁, 那么这20线程之间是互斥的, 对于资源是共享的,
 * 如果用的是读写锁, 只有2个写的线程是处于竞争状态.  所有的读操作不会互斥.		
 */
	}

}
