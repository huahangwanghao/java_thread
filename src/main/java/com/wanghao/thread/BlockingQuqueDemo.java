package com.wanghao.thread;
/**
 * BlockingQueue是一个接口, 下面有俩个实现  ArrayBlockingQueue(基于数组)和LinkedBlockingQueue(基于链表)
 * BlockingQueue之所以使用作为数据共享通道,关键在于Blocking上,Blocking意思是阻塞的意思,当服务线程处理完成队列中所有的消息后,它如何知道下一条信息何时到来呢?
 * 一种最傻瓜的方式就是让线程按照一定的时间间隔去扫描队列,会造成一定的资源浪费
 * 解决方案:
 * 	BlockingQueue在队列为空时,让服务线程队列进行等待(阻塞),当有新的消息进入队列后,自动将唤醒线程
 * 
 * ArrayBlockingQueue内部有一个  final Object[] items;  内部元素都放置在一个对象数据中
 * 向队列中压入元素可以使用offer()和put()
 * 	offer():方法内部是直接返回,这个不存在等待阻塞等内容, 下面我们主要关注在put方法
 * put();将元素压入队列的末尾,但是如果队列满了,它会一直等待,直到队列中有空闲的位置.
 * 与之对应的是
 * poll和take,它们都从队列的头部获得一个元素,不同之处在于队列为空时, poll 方法直接返回null, 
 * poll() 如果队列空时 直接返回null
 * take() 如果队列为空时 , 则会等待
 * 所以take 和 put 是体现BlockingQueue的关键
 * 
 * 查看take源码时候哈
 * 
 * @author Administrator
 *
 */
public class BlockingQuqueDemo {

}
