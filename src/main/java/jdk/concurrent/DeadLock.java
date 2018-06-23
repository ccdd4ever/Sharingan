package jdk.concurrent;
/*
 * ��ʾ��������
 */

class Demo6 implements Runnable{

        //����2��������
        private Object lockA = new Object();
        private Object lockB = new Object();

        //��һ��������Ŀ����2���߳̽��뵽��ͬ�Ĵ�����ȥ����
        public boolean flag = false;

        public void run() {
                //�ж�flag��ǣ��ò�ͬ���߳̽��뵽��ͬ����������
                if( flag ){
                        while(true){
                                synchronized( lockA ){
                                        System.out.println(Thread.currentThread().getName()+".......if.....lockA.........");

                                        synchronized( lockB ){
                                                System.out.println(Thread.currentThread().getName()+".......if.....lockB.........");
                                        }
                                }
                        }
                }else{
                        while(true){
                                synchronized( lockB ){
                                        System.out.println(Thread.currentThread().getName()+".......else.....lockB.........");

                                        synchronized( lockA ){
                                                System.out.println(Thread.currentThread().getName()+".......else.....lockA.........");
                                        }
                                }
                        }
                }
        }

}
public class DeadLock {
        public static void main(String[] args) throws InterruptedException {

                //�����̵߳�����
                Demo6 d = new Demo6();

                //�����̵߳Ķ���
                Thread t = new Thread(d);
                Thread t2 = new Thread(d);
                t.start();
                //�����߳�����һ�ᣬ��֤Thread-0����run�����е�else�У����޸�flag���
                Thread.sleep(1);
                //��concurrent-0����֮�󣬰�Demo6�е�flag�޸�Ϊtrue��Ŀ������concurrent-1����if������
                d.flag = true;
                t2.start();
        }
}
