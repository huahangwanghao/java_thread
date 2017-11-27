package com.wanghao.thread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
/**
 * 循环栅栏:
 * 	阻止线程继续执行,要求线程在栅栏处等待,前面的Cyclic意思是循环的意思,  指的是这个计数器可以循环使用
 * 比如我们将计数器设置为10,那么凑齐第一批10个线程后,计数器归零,然后接着凑齐下一批10个线程,
 * 
 * 应用场景:
 * 	司令下达命令, 要求10个士兵一起去完成一项任务, 这时候 就会要求10个士兵先集合报道,接着 一起去执行任务, 当10个士兵把
 * 自己手头上的任务都执行完毕后, 司令 才对外宣布 任务完成
 * 
 * 所有精华全部在构造函数里面
 * 
 * public CyclicBarrier (int parties,Runnable barrierAction) 意思就是 当计数器一次计数完成后, 系统会执行barrierAction的动作
 * 
 * @author Administrator
 *
 */
public class CyclicBarrierDemo {

	public static class Soldier implements Runnable{

		private String soldier;
		
		private  CyclicBarrier cyclic=null;
		
		public Soldier(CyclicBarrier cyclicBarrier,String soldierName) {
			this.soldier=soldierName;
			this.cyclic=cyclicBarrier;
		}
		
		public void run() {
			try {
				//等待所有士兵到齐
				cyclic.await();
				dowork();
				//等待所有士兵完成工作
				cyclic.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			
		}

		private void dowork() {
			try {
				//模拟操作过程
				Thread.sleep(Math.abs(new Random().nextInt()%10000));
			} catch (Exception e) {
				e.printStackTrace();
			}
			//任务执行完毕
			System.out.println(soldier+": 任务完成!");
		}
		
	}
	
	public static class BarrierRun implements Runnable{
		boolean flag;
		int N;
		
		public BarrierRun(boolean flag,int N){
			this.flag=flag;
			this.N=N;
		}
		public void run() {
			if(flag){
				System.out.println("司令:[士兵"+N+"个,任务完成]");
			}else{
				System.out.println("司令:[士兵"+N+"个,集合完毕]");
				flag=true;
			}
		}
		
	}

	public static void main(String[] args) {

		final int N=10;
		Thread [] allSoldier=new Thread[10];
		boolean flag = false;
		//创建一个cyclicBarrier实例,并将计数器设置为10, 并要求计数器达到指标时,执行 BarrierRun 的run方法.
		/**
		 * 1.下面这句话这么理解: 首先创建一个cyclicBarrier的实例, 并且初始化计数器的个数.
		 */
		CyclicBarrier cyclic=new CyclicBarrier(N, new BarrierRun(flag, N));
		
		System.out.println("集合队伍!");
		
		for (int i=0;i<N;++i){
			System.out.println("士兵"+i+"报到");
			//创建10个线程
			allSoldier[i]=new Thread(new Soldier(cyclic,"士兵"+i));
			/**
			 * 2.我们调用了soldier这个start方法,执行了里面的run()方法, 构造方法里面传递进去了 cyclic这个方法,
			 * 然后调用了await这个方法.调用一次 计数器减1 ,当栅栏处有10个线程的时候, 会执行 new CyclicBarrier(N, new BarrierRun(flag, N)); 后面这个Runnable接口里面
			 * 的run方法. 执行完毕后 又遇到一个 await方法,当执行完毕后 又一次执行BarrierRun 里面的run方法, 
			 * 瞬间感觉这个代码写的好牛逼
			 * 
			 */
			allSoldier[i].start();
		}
		
	}

}
