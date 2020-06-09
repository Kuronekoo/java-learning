package structure.queue;

/**
 * @description: 环形队列
 * 1.  front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
 * front 的初始值 = 0
 * 2.  rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 空出一个空间做为区分队列为空和队列为满队情况.
 * rear 的初始值 = 0
 * 3. 当队列满时，条件是  (rear  + 1) % maxSize == front 【满】
 * 4. 对队列为空的条件， rear == front 空
 * 5. 当我们这样分析， 队列中有效的数据的个数   (rear + maxSize - front) % maxSize   // rear = 1 front = 0 或者为 Math.abs(rear - front)
 * 6. 我们就可以在原来的队列上修改得到，一个环形队列
 *
 * 只要队列没有满，环形队列就可以一直往里面加数据
 * 环形队列队尾的 rear 和front 值增加都需要通过取模来循环得到 (rear+1)%maxSize (front+1)%maxSize
 *
 * @author: kuroneko
 * @create: 2020-06-08 09:14
 **/
public class TestCycleQueue {
    public static void main(String[] args) {
        CycleQueue cycleQueue = new CycleQueue(3);
        cycleQueue.add(1);
        cycleQueue.showQueue();

        cycleQueue.add(2);
        cycleQueue.showQueue();

        cycleQueue.get();
        cycleQueue.showQueue();

        cycleQueue.add(3);
        cycleQueue.showQueue();

        cycleQueue.get();
        cycleQueue.showQueue();

        cycleQueue.add(4);
        cycleQueue.showQueue();


        cycleQueue.get();
        cycleQueue.showQueue();

        cycleQueue.add(5);
        cycleQueue.showQueue();

        cycleQueue.get();
        cycleQueue.showQueue();


        cycleQueue.get();
        cycleQueue.showQueue();
    }
}
    class CycleQueue{
        private int[] queue;

        private int front = 0;
        private int rear = 0;
        private int maxSize = 0;

        CycleQueue(int maxSize) {
            this.queue = new int[maxSize];
            this.maxSize = maxSize;
            showQueue();
        }

        public boolean isEmpty(){
            return rear == front;
        }

        public boolean isFull(){
            return (rear+1)%maxSize == front;
        }

        public void add(int item){
            if(isFull()){
                throw new RuntimeException("队列已满");
            }
            this.queue[rear] = item;
            System.out.printf("add queue[%d] = %d \n",rear,item);
            rear = (rear  + 1) % maxSize;
        }

        public int get(){
            if(isEmpty()){
                throw new RuntimeException("队列为空");
            }
            int item = this.queue[front];
            System.out.printf("get queue[%d] = %d \n",front,item);
            front = (front + 1) % maxSize;
            return item;

        }

        public void showQueue(){
            if(isEmpty()){
                System.out.println("rear = "+rear);
                System.out.println("front = "+front);
                System.out.println("队列为空");
                System.out.println();
                return;
            }
            System.out.println("rear = "+rear);
            System.out.println("front = "+front);
            //从front开始打印数据
            for (int i = front; i < front + size() ; i++) {
                //因为是环形的，加上size之后可能超出数组的长度，所以要取模
                System.out.printf("arr[%d]=%d\n", i % maxSize, queue[i % maxSize]);
            }
            System.out.println();
        }

        public int size(){
            if(isEmpty()){
                return 0;
            }
            //因为是环形队列队列，相减可能为负数
            return Math.abs(rear - front);
        }

        // 显示队列的头数据， 注意不是取出数据
        public int headQueue() {
            // 判断
            if (isEmpty()) {
                throw new RuntimeException("队列空的，没有数据~~");
            }
            return queue[front];
        }
    }

