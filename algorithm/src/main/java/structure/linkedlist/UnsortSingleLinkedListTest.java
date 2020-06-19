package structure.linkedlist;

import org.junit.Test;

/**
 * @description:
 * @author: kuroneko
 * @create: 2020-06-09 23:28
 **/
public class UnsortSingleLinkedListTest {
    @Test
    public void testUnsortSingleLinkedListTest(){
        UnsortSingleLinkedList singleLinkedList = new UnsortSingleLinkedList();
        LinkedNode n1 = new LinkedNode(2);
        LinkedNode n2 = new LinkedNode(3);
        LinkedNode n3 = new LinkedNode(4);
        singleLinkedList.add(n2);
        singleLinkedList.add(n1);
        singleLinkedList.add(n3);
        singleLinkedList.show();
    }

    class UnsortSingleLinkedList{

        LinkedNode head  = new LinkedNode(0);

        public void add(LinkedNode node){
            LinkedNode tmp = head;
            //遍历获取最后一个节点
            while (true){
                if(tmp.next == null){
                    break;
                }
                tmp = tmp.next;
            }
            //赋值
            tmp.next = node;
        }

        public void show(){
            LinkedNode tmp = head;
                while (true){
                    if(tmp.next == null){
                        break;
                    }
                    tmp = tmp.next;
                    System.out.println(tmp);
                }
        }
    }

    class LinkedNode{
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
