package com.wanghao.thread.Future;

/**
 * 这个实现了一个快速返回RealData的包装, 这只是一个包装, 或者是一个虚拟实现.
 * 当使用FutureData的getData()的时候, 如果实际数据没有准备好, 程序就会阻塞, 等待realData准备好,并注入到FutrueData中, 才最终返回结果
 * @author Administrator
 *
 */

public class FutureData implements Data {

	
	
	
	static class HasService  implements Runnable{
		
		private UserService userService;
		
		public HasService (UserService userService){
			this.userService=userService;
		}

		public void run() {
			System.out.println(userService);
		}
		
	}
	public static void main(String[] args) {
		UserService userService=new UserServiceImpl();
		HasService hasService=new HasService(userService);
		Thread newThread =new Thread(hasService);
		newThread.start();
	}
	
	
	
	
	
	private RealData realData=null;
	private boolean isReady=false;
	
	public synchronized void setRealData(RealData realData){
		if(isReady){
			return;
		}
		this.realData=realData;
		isReady=true;
		notifyAll();
	}
	
	
	
	public synchronized String getData() {
		while(!isReady){
			try {
				wait();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return realData.result;
	}

}
