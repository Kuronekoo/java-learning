package thread.sp;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 消费者和生产者
 * @author: shenchao
 * @create: 2020-05-11 18:21
 **/
public class ConsumerSupplierFalse {
     public static volatile Integer storage = 0;
     private final Lock lock =  new ReentrantLock();
     private final Condition condition = lock.newCondition();
    public static void main(String[] args) {

        ConsumerSupplierFalse consumerSupplierFalse = new ConsumerSupplierFalse();
        MyConsumer c1 = consumerSupplierFalse.new MyConsumer(200);
        MyConsumer c2 = consumerSupplierFalse.new MyConsumer(300);
        MyConsumer c3 = consumerSupplierFalse.new MyConsumer(300);
        MySupplier s1 = consumerSupplierFalse.new MySupplier();
        c1.setName("c1");
        c2.setName("c2");
        c3.setName("c3");
        s1.setName("s1");
        s1.start();
        c1.start();
        c2.start();
        c3.start();


    }

class MyConsumer extends Thread{
    private long sleepMils;

    public MyConsumer(long sleepMils) {
        this.sleepMils = sleepMils;
    }

    @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(sleepMils);
                    //
                    lock.lock();
                    if(storage<=0){
                        System.out.println(Thread.currentThread().getName() +  " storage is empty!");
                        condition.await();
                    }else {
                        storage--;
                        System.out.println(Thread.currentThread().getName() + " consumed storage, now storage is "+storage);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
    }

class MySupplier extends Thread{
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(100);
                    lock.lock();
                    storage++;
                    condition.signalAll();
                    System.out.println(Thread.currentThread().getName() + " add one storage ,now storage is " + storage);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
    }

}