package com.wanghao.thread;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.wanghao.thread.utils.TimeUtils;

/**
 * 计划任务:
 * 	不过这个用的比较少,原因是Spring框架本身就有定时器,但是还是看看吧
 *  这个ScheduledExecutorService和其他几个线程池不一样,这个并不一定会立即执行,它起到一个计划任务的作用.
 * @author Administrator
 *
 */
public class ScheduledExecutorServiceDemo {

	public static void main(String[] args) {
		 //newScheduledThreadPool(): 该方法也返回ScheduledExecutorService对象,但是该线程池可以制定线程数量
		
		ScheduledExecutorService ses=Executors.newScheduledThreadPool(10);
		ses.scheduleWithFixedDelay(new Runnable() {
			//这个因为3s的时间大于周期2s, 所有按照周期是3+2=5s执行
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				System.out.println(TimeUtils.Date2String(new Date()));
			}
		}, 0, 2, TimeUnit.SECONDS);
		
		ses.scheduleAtFixedRate(new Runnable() { 
			//这个因为3s的时间大于周期2s, 所有按照周期是3s执行
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				System.err.println(TimeUtils.Date2String(new Date()));
			}
		}, 0, 2, TimeUnit.SECONDS);
		
	}
	

}
