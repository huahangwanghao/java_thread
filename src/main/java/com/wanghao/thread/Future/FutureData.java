package com.wanghao.thread.Future;

/**
 * 这个实现了一个快速返回RealData的包装, 这只是一个包装, 或者是一个虚拟实现.
 * 当使用FutureData的getData()的时候, 如果实际数据没有准备好, 程序就会阻塞, 等待realData准备好,并注入到FutrueData中, 才最终返回结果
 * @author Administrator
 *
 */

public class FutureData implements Data {
	private RealData realData=null;
	private boolean isReady=false;
	
	//set方法写的挺牛逼, 先判断 是否准备好啦,如果准备好啦, 直接return. 
	//如果没有准备好, 对字段就是赋值, 然后放开判断标准, 然后通知队列里面的人
	public synchronized void setRealData(RealData realData){
		if(isReady){
			return;
		}
		this.realData=realData;
		isReady=true;
		notifyAll();
	}
	
	
	//这个分俩种,如果在上面的set之前调用的,肯定进入里面的死循环,然后进行wait操作,然后是上面的set操作啦. notify啦. 然后可以正常工作啦.
	//如果是上面set之后调用的, 那么死循环已经进不去啦. 原因是 isReady 已经变了.
	public synchronized String getData() {
		while(!isReady){
			try {
				System.out.println("怕等待的时候, set操作还没有处理完");
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return realData.result;
	}

}
