package com.wanghao.thread.Futurejdk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainCollecitons {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		User u1=new User(1,"1",1);
		User u2=new User(1,"1",11);
		User u3=new User(1,"1",5);
		User u4=new User(1,"1",3);
		User u5=new User(1,"1",9);
		
		List<User> list=new ArrayList<User>();
		list.add(u1);
		list.add(u2);
		list.add(u3);
		list.add(u4);
		list.add(u5);
	
		
		Collections.sort(list, new Comparator<User>() {

			public int compare(User o1, User o2) {
				
				return o1.getAge()-o2.getAge();
			}
		});
		System.out.println(list);
		
	}
	
	static class User{
		private int id;
		private String name;
		private int age;
		
		
		
		public User(int id, String name, int age) {
			super();
			this.id = id;
			this.name = name;
			this.age = age;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
		}
		
		
		
		
	}

}
