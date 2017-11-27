package com.wanghao.thread.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 时间工具类
 * @author Administrator
 *
 */
public class TimeUtils {

	private static SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public static String Date2String(Date date){
		return sdf.format(date);
	}
	
	
}
