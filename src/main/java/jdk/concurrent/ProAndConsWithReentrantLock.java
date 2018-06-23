package jdk.concurrent;

/**
 * Created by yuanhai on 2016/4/1.
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 定义容器，定义生产和消费的方法
 */
class Container1 {
	private String[] product = new String[1];

	//创建锁对象
	private Lock lock = new ReentrantLock();

	//创建各自的等待唤醒对象
	private Condition pro_con = lock.newCondition();

	private Condition cons_con = lock.newCondition();

	void put(String good) {
		lock.lock();
		try {
			while (product[0] != null) {
				try {
					pro_con.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			this.product[0] = good;
			System.out.println(good + "商品被生产");
			cons_con.signal();
		} finally {
			//必须在finally中释放锁!
			lock.unlock();
		}
	}

	void consu() {
		lock.lock();
		try {
			while (product[0] == null) {
				try {
					cons_con.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(product[0] + "商品消费");
			this.product[0] = null;
			pro_con.signal();
		} finally {
			lock.unlock();
		}

	}
}

/**
 * 生产者线程
 */
class Provider1 implements Runnable {
	private Container1 container;

	public Provider1(Container1 container) {
		this.container = container;
	}

	@Override
	public void run() {
		int no = 1;
		while (true) {
			container.put("F-22 " + no);
			no++;
		}
	}
}

/**
 * 消费者线程
 */
class Consumer1 extends Thread {
	private Container1 container;

	public Consumer1(Container1 container) {
		this.container = container;
	}

	public void run() {
		while (true) {
			container.consu();
		}
	}
}

public class ProAndConsWithReentrantLock {
	public static void main(String[] args) {
		Container1 container = new Container1();
		Provider1 provider = new Provider1(container);
		Consumer1 consumer = new Consumer1(container);
		Thread t = new Thread(provider);
		Thread t1 = new Thread(consumer);
		t.start();
		t1.start();

	}
}
