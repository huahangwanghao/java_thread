package com.wanghao.thread.productAndCustomer;

public class PCData {
	private final int intData;
	public PCData(int d) {
		intData=d;
	}
	
	public PCData (String d){
		intData=Integer.valueOf(d);
	}
	
	public int getData(){
		return intData;
	}
	
	public String toString(){
		return "data"+intData;
	}
}
