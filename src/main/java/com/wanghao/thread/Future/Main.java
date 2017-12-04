package com.wanghao.thread.Future;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client client=new Client();
		/**
		 * 创建一个请求,然后里面返回结果, 因为是直接new的对象,所以很快
		 */
		Data data=client.request("name");
		/**
		 * 进行相关的其他逻辑操作
		 */
		System.out.println("请求完毕!+ doSomeThing()");
		try {
			//睡了1秒,表示做了其他操作. 比如进行数据库操作等等.
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO: handle exception
		}
		//然后获取新的内容 这个getData() 其实是调用的Future里面的getData()方法, 这个方法调用的RealData里面的getData()方法
		System.out.println("真实数据="+data.getData());
	}

}
