package jdk.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Robin
 * @ClassName: ThreadPoolDemo
 * @DESCRIPTION:
 * @date: 2016/5/24.
 */
public class ThreadPoolDemo {
	public static void main(String[] args) {
		//定义ThreadPool,
		ExecutorService pool = Executors.newCachedThreadPool();
		for (int i = 1; i < 10; i++) {
			pool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("thread name: " + Thread.currentThread().getName());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		pool.shutdown();
	}
}
