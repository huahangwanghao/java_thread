package com.wanghao.thread;

/**
 * 线程的join和yield的信息
 * @author Administrator
 *
 *线程之间的协作: 就和人和人之间的协作一下.
 *	比如: 软件设计中. 必须现有 产品的需求文档. 然后 java开发线程/H5线程/app线程 才能工作 这就是线程之间的协同合作.
 *  对应到线程中.一个线程的输入完全依赖于另一个线程的输出. 这个线程A就要依赖线程B执行完毕才能执行.
 * join 就是实现这个功能的.
 *  在主线程中启动at线程,然后执行at线程的join方法,表示主线程愿意在at线程执行完毕后再执行. 如果在at线程中sleep了. 那么主线等at线程睡醒了再执行
 */
public class JoinAndYieldTest {

	public volatile static int i=0;
	public static class AddThread extends Thread{
		@Override
		public void run() {
			for( i=0;i<10;i++){
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		AddThread at=new AddThread();
		at.start();
		at.join(); //这句话表示在主线程中,我愿意等待at线程执行完毕,我再执行.
		System.out.println(i);
	}

}
