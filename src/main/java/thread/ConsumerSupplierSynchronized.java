package thread;

/**
 * @description: 消费者和生产者
 * @author: shenchao
 * @create: 2020-05-11 18:21
 **/
public class ConsumerSupplierSynchronized extends Thread {
    public static volatile Integer storage = 0;


    public static void main(String[] args) {
        Object lock = new Object();
        ConsumerSupplierSynchronized consumerSupplierLock = new ConsumerSupplierSynchronized();
        MyConsumer c1 = consumerSupplierLock.new MyConsumer(lock);
        MySupplier s1 = consumerSupplierLock.new MySupplier(lock);
        c1.setName("c1");
        s1.setName("s1");
        s1.start();
        c1.start();

    }

    class MyConsumer extends Thread {
        private Object lock;

        MyConsumer(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                    synchronized (lock) {
                        if (storage <= 0) {
                            System.out.println(Thread.currentThread().getName() + " storage is empty!");
                            lock.wait();
                        } else {
                            storage--;
                            System.out.println(Thread.currentThread().getName() + " consumed storage, now storage is " + storage);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class MySupplier extends Thread {
        private Object lock;

        MySupplier(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                    synchronized (lock) {
                        storage++;
                        lock.notifyAll();
                        System.out.println(Thread.currentThread().getName() + " add one storage ,now storage is " + storage);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}