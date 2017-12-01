package com.wanghao.thread.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainTest {

	public static void main(String[] args) {
		long start=System.currentTimeMillis();
		// TODO Auto-generated method stub
			List<User> out = new ArrayList<User>();
			for (int i=0;i<10;i++) {
				User user=new User();
				user.setId(i+"");
				user.setName(i+"");
				out.add(user);	
			} 
			
			List<User> in = new ArrayList<User>();
			for (int i=0;i<10;i++) {
				User user=new User();
				user.setId(i+"");
				user.setName(i+"1");
				in.add(user);	
			} 
						
			
			
		/*	for (User u:out){
				String id=u.getId();
				Iterator<User> it = in.iterator();
				while(it.hasNext()){
					User x = it.next();
				    if(x.getId().equals(id)){
				    	u.setName(x.getName());
				        it.remove();
				        break;
				    }
				}
			}*/
			for (User u:out) {
				String id=u.getId();
				for (User i:in){
					if(i.getId().equals(id)){
						u.setName(i.getName());
						break;
					}
				}
			} 
			
			System.out.println(out);
			System.out.println(System.currentTimeMillis()-start);
			System.out.println(Runtime.getRuntime().availableProcessors());
			
			for (int i=0;i<10;i++){
				System.out.println(new Random().nextInt(123));
			}
	}

	
}
