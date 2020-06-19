package structure.linkedlist;

import org.junit.Test;

import java.util.Stack;

/**
 * @description:
 * @author: kuroneko
 * @create: 2020-06-09 23:55
 **/
public class SortSingleLinkedTest {
    @Test
    public void testSortSingleLinkedTest() {
        SortSingleLinkedList singleLinkedList = new SortSingleLinkedList();

        LinkedNode n1 = new LinkedNode(1);
        LinkedNode n2 = new LinkedNode(2);
        LinkedNode n3 = new LinkedNode(3);
        LinkedNode n4 = new LinkedNode(4);
        LinkedNode n5 = new LinkedNode(5);
        LinkedNode n51 = new LinkedNode(5);
        LinkedNode n52 = new LinkedNode(5);
        LinkedNode n6 = new LinkedNode(6);
        LinkedNode n7 = new LinkedNode(7);
        LinkedNode n8 = new LinkedNode(8);

        singleLinkedList.addOrderly(n3);
        singleLinkedList.addOrderly(n2);
        singleLinkedList.addOrderly(n1);
        singleLinkedList.addOrderly(n4);
        singleLinkedList.addOrderly(n7);
        singleLinkedList.addOrderly(n6);
        singleLinkedList.addOrderly(n5);
        singleLinkedList.addOrderly(n51);
        singleLinkedList.addOrderly(n52);
        singleLinkedList.addOrderly(n8);

        singleLinkedList.show();
        System.out.println(singleLinkedList.size());

//        singleLinkedList.remove(n5);
//        singleLinkedList.remove(n1);
//        singleLinkedList.remove(n2);
//        singleLinkedList.remove(n3);
//        singleLinkedList.remove(n4);
//        singleLinkedList.remove(n6);
//        singleLinkedList.remove(n7);
//        singleLinkedList.remove(n8);
//        singleLinkedList.show();

//        System.out.println(singleLinkedList.size());

//        singleLinkedList.reverse();
//        System.out.println();
//        singleLinkedList.show();

//        singleLinkedList.add(n1);

        singleLinkedList.reversePrint();


    }

    class SortSingleLinkedList {

        LinkedNode head = new LinkedNode(0);
        int size = 0;
        boolean reversed = false;

        public void reversePrint() {
            //链表为空
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }
            //创建一个栈的数据结构
            //栈的特点是后进先出，可能想象一下一些堆在一起的椅子
            Stack<LinkedNode> stack = new Stack<>();
            LinkedNode cur = head.next;
            while (cur != null) {
                stack.push(cur);
                cur = cur.next;
            }
            while (stack.size() != 0) {
                System.out.println(stack.pop());
            }
        }

        /**
         * 反转链表
         */
        public void reverse() {
            //链表为空
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }
            //创建一个新的head
            LinkedNode newHead = new LinkedNode(0);
            //指针
            LinkedNode cur = head.next;
            //用于临时保存指针的下一个node
            LinkedNode next = null;
            while (true) {
                if (cur == null) {
                    break;
                }
                //暂存指针的下一个
                next = cur.next;
                //往头部插入，使指针的下一个指向第一个node，也就是头节点的下一个
                cur.next = newHead.next;
                //让头节点指向指针，头部插入完成
                newHead.next = cur;
                //指针后移
                cur = next;
            }
            //原来的head next指向新的 head next
            head.next = newHead.next;
            reversed = true;
        }

        public void reverseStupid2() {
            //链表为空
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }
            //创建一个新的head
            LinkedNode newHead = new LinkedNode(0);
            //暂存head
            LinkedNode tmp = head.next;
            while (true) {
                if (tmp == null) {
                    break;
                }
                //创建一个新的node
                LinkedNode newNode = new LinkedNode(tmp.value);
                //新的node指向新head的下一个
                newNode.next = newHead.next;
                //新head指向新node
                newHead.next = newNode;
                //原链表后移
                tmp = tmp.next;
            }
            //原来的head next指向新的 head next
            head.next = newHead.next;
        }

        /**
         * 遍历链表，从后往前一个一个获取和复制
         */
        public void reverseStupid() {
            //链表为空
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }
            //创建一个新的head
            LinkedNode newHead = new LinkedNode(0);
            //暂存newHead
            LinkedNode newTmp = newHead;
            //获取倒数第i个数据,倒数第1～倒数第8
            for (int i = 1; i <= size; i++) {
                //获取第一个节点
                LinkedNode tmp = head.next;
                //i=1,j<7第一个喜欢执行7次，i=8,j<0,最后一次执行0次
                for (int j = 0; j < size - i; j++) {
                    //获取node节点，知道第size-i个
                    tmp = tmp.next;
                }
                //一个新的node等于第size-i个
                LinkedNode pss = tmp;
                //设置pss的下一个为空，不然会把tmp后面的next全部带过来
                pss.next = null;
                //暂存newHead的next等于pss
                newTmp.next = pss;
                //newTmp指向下一个，也就是tmp现在指向pss了，pss.next = null
                newTmp = newTmp.next;
            }
            //全部处理完将原来的head指向null，原来的链被垃圾回收
            head.next = null;
            //原来的head.next指向新head的next
            head.next = newHead.next;
            reversed = true;

        }

        public int size() {
            if (head.next == null) {
                return 0;
            }
            return size;
        }

        public void add(LinkedNode node) {
            LinkedNode tmp = head;
            //遍历获取最后一个节点
            while (true) {
                if (tmp.next == null) {
                    break;
                }
                tmp = tmp.next;
            }
            //赋值
            tmp.next = node;
        }

        public void addOrderly(LinkedNode node) {
            if (reversed) {
                throw new RuntimeException("链表已反转，无法顺序添加");
            }
            LinkedNode tmp = head;
            //遍历获取节点需要加入的位置
            while (true) {
                //如果找不到比当前插入node大节点，那就放到最后
                if (tmp.next == null) {
                    tmp.next = node;
                    size++;
                    break;
                }
                if (tmp.value == node.value) {
                    System.out.println("node已存在，跳过");
                    break;
                }
                //找到比当前插入node value大的位置
                if (tmp.value > node.value) {
                    //把node放到tmp的前面
                    node.next = tmp.next;
                    tmp.next = node;
                    size++;
                    break;
                }

                tmp = tmp.next;

            }
        }

        public void remove(LinkedNode node) {
            LinkedNode tmp = head;
            //链表为空
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }
            while (true) {
                //已经遍历到末尾
                if (tmp.next == null) {
                    System.out.println("node不存在");
                    break;
                }
                //如果value相等，准备删除
                if (tmp.next.value == node.value) {
                    //把当前节点的下个删除
                    //即把当前节点的next指向下下个节点
                    tmp.next = tmp.next.next;
                    size--;
                    break;
                }
                tmp = tmp.next;
            }
        }

        public void show() {
            LinkedNode tmp = head;
            if (head.next == null) {
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

    class LinkedNode {
        int value;
        LinkedNode next;

        public LinkedNode(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "LinkedNode{" +
                    "value=" + value +
                    '}';
        }
    }
}
