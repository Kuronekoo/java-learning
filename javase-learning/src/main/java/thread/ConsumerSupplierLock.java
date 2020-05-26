package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 消费者和生产者
 * @author: kuroneko
 * @create: 2020-05-11 18:21
 **/
public class ConsumerSupplierLock extends Thread {
    public static volatile Integer storage = 0;


    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        ConsumerSupplierLock consumerSupplierLock = new ConsumerSupplierLock();
        MyConsumer c1 = consumerSupplierLock.new MyConsumer(condition,lock);
        MySupplier s1 = consumerSupplierLock.new MySupplier(condition,lock);
        c1.setName("c1");
        s1.setName("s1");
        s1.start();
        c1.start();


    }

    class MyConsumer extends Thread {
        //    private final Lock lock = new ReentrantLock();
//    lock.new
//    private final Condition condition = lock.newCondition();
        private Condition condition;
        private Lock lock;
        MyConsumer(Condition condition,Lock lock){
            this.condition = condition;
            this.lock = lock;
        }
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                    this.lock.lock();
                    if (storage <= 0) {
                        System.out.println(Thread.currentThread().getName() + " storage is empty!");
                        this.condition.await();
                    } else {
                        storage--;
                        System.out.println(Thread.currentThread().getName() + " consumed storage, now storage is " + storage);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    this.lock.unlock();
                }
            }
        }
    }

    class MySupplier extends Thread {
        private Condition condition;
        private Lock lock;
        MySupplier(Condition condition,Lock lock){
            this.condition = condition;
            this.lock = lock;
        }
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                    this.lock.lock();
                    storage++;
                    this.condition.signalAll();
                    System.out.println(Thread.currentThread().getName() + " add one storage ,now storage is " + storage);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    this.lock.unlock();
                }
            }
        }
    }

}