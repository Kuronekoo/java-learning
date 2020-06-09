package structure.queue;

import org.junit.Test;

import java.lang.reflect.Array;

/**
 * @description:队列
 * 队列是一个有序列表，采用数组或者链表进行实现
 * 1。队列的特点是 先进先出
 * @author: kuroneko
 * @create: 2020-06-05 17:23
 **/
public class TestArrayQueue {

    @Test
    public void testQueue(){
        MyArrQueue<String> stringMyArrQueue = new MyArrQueue<>( 3);
        stringMyArrQueue.addQueue("3");
        stringMyArrQueue.addQueue("2");
        stringMyArrQueue.addQueue("1");
        stringMyArrQueue.showQueue();
        System.out.println(stringMyArrQueue.getQueue());
    }

    class MyArrQueue<T> {
        private T [] queue;
        private int front;
        private int rear;
        private int maxSize;

        public MyArrQueue(int size) {
//            this.queue = (T []) Array.newInstance(clazz,size);
            this.queue =  (T [])  new Object[size];
            //指向队列头，front指向队列头的前一个位置
            this.front = -1;
            //指向队列尾，就是队列尾的位置
            this.rear = -1;
            this.maxSize = size;
        }

        public boolean isFull(){
            return rear > maxSize -1;
        }

        public boolean isEmpty(){
            return front == rear;
        }

        public void addQueue(T t){
            if(isFull()){
                throw new RuntimeException("队列已满");
            }
            this.rear++;
            this.queue[this.rear] = t;

        }

        public T getQueue(){
            if(isEmpty()){
                throw new ArrayIndexOutOfBoundsException("数组越界");
            }
            this.front++;
            T t = this.queue[this.front];
            return t;
        }

        public void showQueue(){
            if(isEmpty()){
                System.out.println("数组为空");
                return;
            }
            for (int i = 0; i < this.queue.length; i++) {
                System.out.printf("queue[%d] = %s \n",i,this.queue[i]);
            }
        }

        public int size(){
            if(isEmpty()){
                return 0;
            }
            return rear - front;
        }

    }
}
