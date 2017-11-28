package com.wanghao.thread;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.wanghao.thread.utils.TimeUtils;

/**
 * jdk中提供的几种线程池的获取方法
 * 
 * 原理解释: 创建一个新的线程池 会有几个参数ThreadPoolExecutor(初始化数量,最大数量,存活时间,存活单位,队列类选);
 * 
 * newFixedThreadPool():该方法返回一个固定线程数量的线程池,该线程池中的线程数量始终不变,当有一个新的任务提交的时候,线程池中若有空闲,则立即
 * 			执行,如果没有,则新的任务会被暂存在一个任务队列中农工,待有线程空闲时,便处理在任务队列中的任务.
 * 
 * 			原理:LinkedBlockingQueue  这个是设置的有界的数组长度
 * 
 * newSingleThreadExecutor(): 该方法返回一个只有一个线程的线程池: 若多余一个任务被提交到线程池,任务会被保存到一个任务队列中,待线程空闲时
 * 			按照先入先出的顺序执行队列中的任务.
 * newCachedThreadPool() : 该方法返回一个可根据实际情况调整线程数量的线程池,线程池的数量不确认,,若有空闲的线程可以复用,则有限使用可以复用的线程
 * 			,如果所有的线程都工作呢, 又有新的任务提交,则会创建新的线程出出力,所有的线程在当前任务执行完毕后,返回线程池中.
 * 
 * 			原理解释: 创建一个新的线程池 会有几个参数ThreadPoolExecutor(0(初始化数量), Integer.MAX_VALUE(最大数量),60L(存活时间), TimeUnit.SECONDS(存活单位),new SynchronousQueue<Runnable>()(队列类选));
 * 				SynchronousQueue这个队列比较牛逼是没有容量的,每次里面只能放一个内容, 要想更新,只能新插入把之前的删除,
 * 				因为这个list没有容量,所以任务提交后,先判断有没有空闲的线程,(因为有60s存活时间) 如果有 就把这个任务就拿走哦啦.
 * 				如果没有空闲的线程, 就尝试创建新的线程,
 * 
 * 				SynchronousQueue的理解:  这也是一个队列,它的特别之处在于它内部没有容器, 一个生产线程,生产产品的时候(put的时候,)如果当前没有线程要消费(take)
 * 这时候这个生产线程进行阻塞,等待一个消费线程调用take操作,来唤醒生产线程的put.
 * 
 * newSingleThreadScheduledExecutor(): 该方法返回一个ScheduledExecutorService对象,线程池大小为1, ScheduledExecutorService接口在ExecutorService接口
 * 			之上扩展了在给定时间执行某任务的功能,
 * 			比如在固定的延时之后执行,或者周期性执行某个任务
 *newScheduledThreadPool(): 该方法也返回ScheduledExecutorService对象,但是该线程池可以制定线程数量
 * 
 * @author Administrator
 *
 */
public class ThreadPoolDemo {

	public static class MyTask implements Runnable{

		public void run() {
			System.out.println(TimeUtils.Date2String(new Date())+": Thread ID:"+Thread.currentThread().getId());
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) {
			MyTask myTask=new MyTask();
			//ExecutorService es=Executors.newFixedThreadPool(5);//创建一个连接池 固定大小为5,
			ExecutorService es=Executors.newCachedThreadPool();//创建一个cache的线程池,一下子就执行完毕啦.
			for (int i=0;i<13;i++){
				es.submit(myTask); //依次向线程池中提交了10个任务,线程池就会安排调度这10个任务,
			}
	}

}
