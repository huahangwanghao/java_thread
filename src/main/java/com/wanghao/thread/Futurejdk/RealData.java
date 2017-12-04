package com.wanghao.thread.Futurejdk;

import java.util.concurrent.Callable;

public class RealData implements Callable<String> {
	
	private String para;
	
	public RealData(String para){
		this.para=para;
	}

	/**
	 * call方法,会构造我们需要的真实数据,并返回. 这个过程可能是缓慢的,我们用Thread.sleep 来模拟
	 */
	public String call() throws Exception {
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<10;i++){
			sb.append(para);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}
