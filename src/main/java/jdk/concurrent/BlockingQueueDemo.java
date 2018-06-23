package jdk.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author Robin
 * @ClassName: BlockingQueueDemo
 * @DESCRIPTION:A {@link java.util.Queue} that additionally supports operations
 * that wait for the queue to become non-empty when retrieving an
 * element, and wait for space to become available in the queue when
 * storing an element.
 * @date: 2016/5/24.
 */
public class BlockingQueueDemo {
	public static void main(String[] args) {
		//大小不定的BlockingQueue,若其构造函数带一个规定大小的参数,生成的BlockingQueue有大小限制,若不带大小参数,所生成的BlockingQueue的大小由Integer.MAX_VALUE来决定.其所含的对象是以FIFO(先入先出)顺序排序的。
		BlockingQueue<String> queue=new LinkedBlockingDeque<>();
		Consumer2 consumer=new Consumer2(queue);
		Provider2 provider=new Provider2(queue);
		for (int i = 0; i <5; i++) {
			new Thread(provider,"Provider"+(i+1)).start();
			new Thread(consumer,"consumer"+(i+1)).start();
		}
	}

}

/**
 * 消费者线程，使用BlockingQueue的阻塞take方法，
 * poll为非阻塞取走方法
 */
class Consumer2 implements Runnable{
	BlockingQueue<String> queue;
	public Consumer2(BlockingQueue<String> queue){
		this.queue=queue;
	}
	@Override
	public void run() {
		try {
			String take = queue.take();
			System.out.println("消费商品"+take);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

/**
 * 生产者线程，使用BlockingQueue的阻塞方法put，
 * add为非阻塞存储方法
 */
class Provider2 implements Runnable{
	BlockingQueue<String> queue;
	public Provider2(BlockingQueue<String> queue){
		this.queue=queue;
	}
	@Override
	public void run() {
		try {
			queue.put("A Product, 生产线程"
				+ Thread.currentThread().getName());
			System.out.println("I have made a product:"
				+ Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
