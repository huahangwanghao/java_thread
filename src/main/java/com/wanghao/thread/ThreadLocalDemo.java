package com.wanghao.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal:
 * 	除了控制资源的访问外,我们还可以通过增加资源来保护所有对象的线程安全.
 * 	比如让100个人填写个人信息. 如果只有一支笔, 那就挨个写, 如果有100支笔,那就快多啦
 *  从名字来看, 这是一个线程的局部变量,只有当前线程可以访问,既然只有的昂前线程可以访问. 那自然是线程安全的.
 * 
 * @author Administrator
 *
 */
public class ThreadLocalDemo {

	private static final SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static ThreadLocal<SimpleDateFormat> t1=new ThreadLocal<SimpleDateFormat>();
	public static class ParseDate implements Runnable{
		int i=0;
		public ParseDate(int i) {
			this.i=i;
		}
		public void run() {
			Date t;
			try {
					if(t1.get()==null){
						t1.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
					}
					t = t1.get().parse("2017-11-12 11:33:"+i%60);
					System.out.println(i+":"+t);
				
			
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	public static void main(String[] args) {
		ExecutorService es=Executors.newFixedThreadPool(10);
		for (int i=0;i<1000;i++) {
			es.execute(new ParseDate(i));
		} 
	}

}
