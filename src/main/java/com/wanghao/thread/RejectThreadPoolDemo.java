package com.wanghao.thread;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.wanghao.thread.utils.TimeUtils;

/**
 * 拒绝策略:
 * 		解释:当任务数量超过系统实际承载的能力时,该如何处理?
 * 可以理解为系统超负荷运行时的补救措施,  也就是线程池中的线程用完啦,并且队列也已经满啦, 再也放不下新的任务啦.
 * JDK的四种拒绝策略:
 * 	AbortPolicy: 该策略会直接抛出异常,阻止系统正常工作.
 *  CallerRunsPolicy:只要线程池未关闭,该策略直接在调用者线程中,运行当前被丢弃的任务.显然这样做不会真的丢弃任务,但是 任务提交线程的性能极可能急剧下降
 *  DiscardOledestPolicy: 该策略会丢弃最老的一个请求,也就是即将被执行的一个任务.并尝试再次提交.
 *  DiscardPolicy:该策略默默的丢弃无法处理的任务,不予以任何处理.  如果任务允许丢弃 这个策略是最好的
 * @author Administrator
 *
 */
public class RejectThreadPoolDemo {

	public static class MyTask implements Runnable{

		public void run() {
			System.out.println(TimeUtils.Date2String(new Date())+" Thread ID: "+Thread.currentThread()
			.getId());
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		MyTask myTask=new MyTask();
		ExecutorService es=new ThreadPoolExecutor(5, 1000, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000), new RejectedExecutionHandler() {
			
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				System.out.println(r.toString()+" is  discard");
			}
		});
		
		for (int i=0;i<Integer.MAX_VALUE;i++) {
			es.submit(myTask);
			
		} 
	}

}
