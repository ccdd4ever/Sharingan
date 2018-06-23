package jdk.concurrent;

/**
 * Created by yuanhai on 2016/4/1.
 */
class Container {
        private String[] product=new String[1];

        private Object lock = new Object();
        //synchronized实现.需要手动释放锁使用notifyAll方法，唤醒处于等待中的线程，这样会导致在唤醒的时候会唤醒本方，而唤醒本方是没有任何意义的。我们更希望生产者线程直接唤醒消费者线程，或者消费者线程直接唤醒生产者线程
        void put(String good) {
                synchronized (lock) {
                        while(product[0] != null) {
                                try {
                                        lock.wait();
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                        this.product[0] = good;
                        System.out.println(good + "商品被生产");
                        lock.notifyAll();
                }
        }

        void consu() {
                synchronized (lock) {
                        while(product[0] == null) {
                                try {
                                        lock.wait();
                                } catch (InterruptedException e) {
                                        e.printStackTrace();
                                }
                        }
                        System.out.println(product[0]+ "商品消费");
                        this.product[0] = null;
                        lock.notifyAll();
                }
        }
}

class Provider implements Runnable {
        private Container container;

        public Provider(Container container) {
                this.container = container;
        }

        @Override
        public void run() {
                while (true) {
                        container.put("F-22");
                }
        }
}

class Consumer extends Thread {
        private Container container;

        public Consumer(Container container) {
                this.container = container;
        }

        public void run() {
                while (true) {
                        container.consu();
                }
        }
}

public class ProAndConsWithSyschronized {
        public static void main(String[] args) {
                Container container = new Container();
                Provider provider = new Provider(container);
                Consumer consumer = new Consumer(container);
                Thread t = new Thread(provider);
                Thread t1 = new Thread(consumer);
                t.start();
                t1.start();

        }
}
