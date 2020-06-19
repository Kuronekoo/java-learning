package structure.linkedlist;

import org.junit.Test;

import java.util.Stack;

/**
 * @description: 双向链表
 * @author: kuroneko
 * @create: 2020-06-16 23:44
 **/
public class DoubleLinkedListTests {

    @Test
    public void testDoubleLinkedList() {
        DoubleLinkeList list = new DoubleLinkeList();
        DoubleLinkedNode n1 = new DoubleLinkedNode(1);
        DoubleLinkedNode n2 = new DoubleLinkedNode(2);
        DoubleLinkedNode n3 = new DoubleLinkedNode(3);
        DoubleLinkedNode n4 = new DoubleLinkedNode(4);
        DoubleLinkedNode n5 = new DoubleLinkedNode(5);
        list.addOrderly(n5);
        list.addOrderly(n1);
        list.addOrderly(n3);
        list.addOrderly(n2);
        list.addOrderly(n4);

        list.show();
        list.clear();

        list.addOrderly(n1);
        list.addOrderly(n2);
        list.addOrderly(n3);
        list.addOrderly(n4);
        list.addOrderly(n5);

        list.show();
        list.clear();

        list.addOrderly(n1);
        list.addOrderly(n2);
        list.addOrderly(n5);
        list.addOrderly(n3);
        list.addOrderly(n4);

        list.show();


        list.del(5);

        list.show();

    }

    class DoubleLinkeList {
        private DoubleLinkedNode head = new DoubleLinkedNode(0);
        private int size = 0;

        //需要清空所有指针的指向
        public void clear(){
            if (isEmpty()) {
                System.out.println("linked list is empty!");
                return;
            }
            Stack<DoubleLinkedNode> stack = new Stack();
            DoubleLinkedNode cur = head.next;
            //先押入栈
            while (cur!=null){
                stack.push(cur);
                cur =  cur.next;
            }
            //从最后取出，清空指针
            while (!stack.empty()){
                DoubleLinkedNode pop = stack.pop();
                pop.next=null;
                pop.pre=null;
            }
            head.next=null;
            size=0;
        }

        public Boolean isEmpty() {
            return null == head.next;
        }

        public void del(int value) {
            if (isEmpty()) {
                System.out.println("linked list is empty!");
                return;
            }
            DoubleLinkedNode cur = head.next;
            while (true) {
                if (cur == null) {
                    break;
                }
                //执行删除
                if (cur.value == value) {
                    cur.pre.next = cur.next;
                    //如果删除的不是最后一个节点，就要修改pre
                    //否则会出现空指针
                    if (null != cur.next) {
                        cur.next.pre = cur.pre;
                    }
                    break;
                }
                cur = cur.next;
            }

        }

        public void addOrderly(DoubleLinkedNode node) {
            DoubleLinkedNode tmp = head.next;
            if (isEmpty()) {
                head.next = node;
                node.pre = head;
                return;
            }

            //遍历获取节点需要加入的位置
            while (true) {
                if (tmp.value == node.value) {
                    System.out.println("node已存在，跳过");
                    break;
                }
                //找到比当前插入node value大的位置
                if (tmp.value > node.value) {
                    //把node放到tmp的前面去
                    //tmp的前一个节点指向node
                    tmp.pre.next = node;
                    //node的pre指向tmp的前一个节点
                    node.pre = tmp.pre;
                    //node的下一个节点指向tmp
                    node.next = tmp;
                    //tmp的pre指向node
                    tmp.pre = node;
                    size++;
                    break;
                }
                //如果已经到了队尾，就直接添加到队尾
                if (tmp.next == null) {
                    tmp.next = node;
                    node.pre = tmp;
                    size++;
                    break;
                }
                tmp = tmp.next;

            }
        }

        public void add(DoubleLinkedNode node) {
            DoubleLinkedNode cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = node;
            node.pre = cur;
        }

        public int size() {
            return size;
        }

        public DoubleLinkedNode getHead() {
            return head;
        }

        public void show() {
            DoubleLinkedNode tmp = head;
            if (isEmpty()) {
                System.out.println("linked list is empty!");
            }
            while (true) {
                if (tmp.next == null) {
                    break;
                }
                tmp = tmp.next;
                System.out.println(tmp);
            }
            System.out.println();
        }
    }

    class DoubleLinkedNode {
        DoubleLinkedNode pre;
        int value;
        DoubleLinkedNode next;

        public DoubleLinkedNode(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "DoubleLinkedNode {" +
                    "value=" + value +
                    '}';
        }
    }
}
