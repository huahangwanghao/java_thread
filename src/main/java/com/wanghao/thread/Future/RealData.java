package com.wanghao.thread.Future;
/**
 * 这是最终需要使用的数据,  这个返回数据构造起来很慢
 * @author Administrator
 *
 */
public class RealData implements Data {
	protected  String result="";
	public  RealData(String para) {
		// TODO Auto-generated method stub
		StringBuffer sb =new StringBuffer();
		for (int i=0;i<10;i++) {
			sb.append(para);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		result=sb.toString();
	}

	public String getData() {
		return result;
	}
}
