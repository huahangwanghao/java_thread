package com.wanghao.thread;
/**
 * 线程中断测试类
 * @author Administrator
 *
 */
public class ThreadInterrupt {
	/**
	 * 没有中断的方法
	 * @throws Exception
	 */
	public static void noInterrpt() throws Exception {
		//创建一个线程
		Thread t1=new Thread(){
			public void run() {
				while(true){
					Thread.yield();
					System.out.println(System.currentTimeMillis());
				}
			};
		};
		t1.start();
		Thread.sleep(2000);//主线程休眠一段时间   这个Thread 在哪个线程里面,哪个线程进行修改
		t1.interrupt();  //只是这样的话,上面没有做判断是否终止, 相当于主线程里面对于t1线程发送了一个中断的指令,至于t1线程是否中断,完全取决于
							//t1线程是否自己处理.
	}
	
	/**
	 * 有中断的方法
	 * @throws Exception
	 */
	public static void Interrpt() throws Exception {
		//创建一个线程
		Thread t1=new Thread(){
			public void run() {
				while(true){
					if(Thread.currentThread().isInterrupted()){
						System.out.println("当前线程中断"+System.currentTimeMillis());
						break; //上面接受到中断的信号, 这里进行自己的中断
					}
					Thread.yield();
					System.out.println(System.currentTimeMillis());
				}
			};
		};
		t1.start();
		Thread.sleep(2000);//主线程休眠一段时间   这个Thread 在哪个线程里面,哪个线程进行修改
		t1.interrupt();  //只是这样的话,上面没有做判断是否终止, 相当于主线程里面对于t1线程发送了一个中断的指令,至于t1线程是否中断,完全取决于
							//t1线程是否自己处理.
	}
	
	/**
	 * sleep被打断有中断的方法
	 * 
	 * Thread.sleep()方法 会让当前线程休眠一段时间,这段时间内不会释放资源,同时会抛出一个中断异常 InterruptException
	 * 如果线程在sleep的时候,被中断  就会抛出这个异常.
	 * 
	 * @throws Exception
	 */
	public static void sleepInterrpt() throws Exception {
		//创建一个线程
		Thread t1=new Thread(){
			public void run() {
				while(true){
					if(Thread.currentThread().isInterrupted()){
						System.out.println("当前线程中断"+System.currentTimeMillis());
						break; //上面接受到中断的信号, 这里进行自己的中断
					}
					try {
						Thread.sleep(5000);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						System.out.println("i am interrupted when i sleep");
						Thread.currentThread().interrupt();  //这里获取当前线程,让当前线程再次interrupt是为了进入上面的if  break 语句中
					}
					Thread.yield();
					System.out.println(System.currentTimeMillis());
				}
			};
		};
		t1.start();
		Thread.sleep(2000);//主线程休眠一段时间   这个Thread 在哪个线程里面,哪个线程进行修改
		t1.interrupt();  //这个意思是就是在主线程中主线程先sleep 2s 然后打断t1线程,但是 t1线程里面sleep 5s 这个时候正在休眠 就被你打断了 .抛出异常
							//在解释一下,为什么 会打印出来上面的语句, 因为虽然是一个死循环,并且 前面是if 判断. 但是这个时候线程处于sleep状态,被打断啦.所以先报出来异常信息
	}
	
	public static void main(String[] args) throws Exception {
		//noInterrpt();
		//Interrpt();
		sleepInterrpt();
	}
	
}
