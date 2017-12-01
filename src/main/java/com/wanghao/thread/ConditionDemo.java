package com.wanghao.thread;

import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import com.wanghao.thread.utils.TimeUtils;

/**
 * Condition:
 * 	这个Condition和Object. wait() notify() 差不多,理解起来差不多,只不过 wait是和synchronize一起使用 
 * 	condition是和lock  重入锁一起使用
 * 得到方法  lock.newCondition() 可以得到和当前重入锁绑定的Condition实例
 * 方法如下:
 * 		await():会使当前线程(T1)处于等待状态,同时释放锁资源(wait也是释放, 但是sleep不释放) 其他线程(T2)使用 signal 的时候, 线程(T1) 重新获得锁并继续执行
 * 	或者当前线程被中断啦, 也能跳出等待  这个和wait很像
 *   signal() 唤醒一个等待的线程  类似于 notify 
 *   
 *   signalAll 类似于 notifya()
 * @author Administrator
 *
 */
public class ConditionDemo implements Runnable{

	public static ReentrantLock lock=new ReentrantLock();
	public static Condition condition=lock.newCondition();

	public void run() {
		try {
			System.out.println("Thread is started"+TimeUtils.Date2String(new Date()));
			lock.lock();
			condition.await();
			System.out.println("Thread is going on"+TimeUtils.Date2String(new Date()));
		} catch (Exception e) {
			
		}finally {
			if(lock.isHeldByCurrentThread()) 
				lock.unlock();
		}
	}
	

	public static void main(String[] args) throws Exception {
		 ConditionDemo td=new ConditionDemo();
		 Thread t1=new Thread(td);
		 t1.start();
		 Thread.sleep(3000);
		 lock.lock();
		 condition.signalAll();
		 lock.unlock();
	}

}
