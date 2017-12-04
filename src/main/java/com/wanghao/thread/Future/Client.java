package com.wanghao.thread.Future;
/**
 * 这个Client客户端是一个对外提供的统一接口,
 * request方法 返回的是一个接口类型,有利于后面对于实现类的扩展..
 * 这个相当于访问的时候 就直接返回一个对象啦. 这个对象是new出来的., 然后里面开启一下线程
 * @author Administrator
 *
 */
public class Client {

	public Data request(final String queryStr){
		final FutureData future=new FutureData();
		new Thread(){  //立马开启一个新的线程, 然后子线程去真的处理这个问题
			public void run() {
				RealData realData=new RealData(queryStr);
				future.setRealData(realData);
			};
		}.start();
		System.out.println("client inner future is "+future);
		return future;
	}
	
}
