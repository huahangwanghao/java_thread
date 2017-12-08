package com.wanghao.thread.NIO;
/**
 * 这是模拟一个EchoServer
 * @author Administrator
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadEchoServer {

	
	private static ExecutorService es =Executors.newCachedThreadPool();
	static class HandleMsg implements Runnable{
		
		Socket clientSocket;
		
		public HandleMsg(Socket clientSocket){
			this.clientSocket=clientSocket;
		}
		
		public void run() {
			BufferedReader is =null;
			PrintWriter os=null;
			try {
				System.out.println(" i am in ");
				is=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				os=new PrintWriter(clientSocket.getOutputStream(),true);
				String inputLine=null;
				long b=System.currentTimeMillis();
				System.out.println("死循环之前的log"+is.readLine());
				//TODO
				while((inputLine=is.readLine())!=null){
					os.print(inputLine);
				}
				os.println("this is from you echo");
				long e=System.currentTimeMillis();
				System.out.println("spend:"+(e-b)+"ms");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(is!=null)
					try {
						is.close();
						if(os!=null)os.close();
						clientSocket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				
			}
			
			
			
		}
		
	}
	
	
	public static void main(String[] args) {
		ServerSocket echoServer=null;
		Socket clientSocket=null;
		try {
			echoServer=new ServerSocket(8000);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		while(true){
			try {
				clientSocket=echoServer.accept();
				System.out.println(clientSocket.getRemoteSocketAddress()+"connect");
				es.execute(new HandleMsg(clientSocket));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
}
