package com.wanghao.thread.Futurejdk;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureMain {
/**
 * 先构造一个future模型的典例.
 * 1.构造一个FutureTask实例,表示这个任务是有返回值的, 在构造FutureTask的时候,使用了Callable接口,告诉FutureTask 我们需要的数据怎么产生,
 * 2.然后将FutureTask提交到线程池, 作为一个简单的任务提交, 这里都是立即返回, 因此程序不会阻塞
 * 3.接下来做一些其他的事情, 最后的时候 才用到实际的数据
 * @param args
 * @throws Exception
 * @throws ExecutionException
 */
	public static void main(String[] args) throws Exception, ExecutionException {
		FutureTask<String> future=new FutureTask<String>(new RealData("a"));
		ExecutorService es = Executors.newFixedThreadPool(1);
		es.submit(future);
		System.out.println("请求完毕");
		
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("数据="+future.get());
		
	}

}
