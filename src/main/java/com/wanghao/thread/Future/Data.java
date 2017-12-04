package com.wanghao.thread.Future;
/**
 * 在Future模式中,这个Data就是客户端真的需要的数据
 * 有俩个实现, 一个是realData  一个是FutureData
 * @author Administrator
 *
 */
public interface Data {

	public String getData();
	
}
