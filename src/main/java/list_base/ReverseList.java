package list_base;

/**
 * Create with: list_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/18 13:26
 * version: 1.0
 * description: 翻转单链表
 */
public class ReverseList {
    public static void main(String args[]) {
        Node node = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
// 双向链表
        DoubleNode doubleNode = new DoubleNode(0);
        DoubleNode doubleNode1 = new DoubleNode(1);
        DoubleNode doubleNode2 = new DoubleNode(2);
        DoubleNode doubleNode3 = new DoubleNode(3);
        DoubleNode doubleNode4 = new DoubleNode(4);
        DoubleNode doubleNode5 = new DoubleNode(5);

        doubleNode.next = doubleNode1;
        doubleNode.pre = null;

        doubleNode1.next = doubleNode2;
        doubleNode1.pre = doubleNode;
        doubleNode2.next = doubleNode3;
        doubleNode2.pre = doubleNode1;
        doubleNode3.next = doubleNode4;
        doubleNode3.pre = doubleNode2;
        doubleNode4.next = doubleNode5;
        doubleNode4.pre = doubleNode3;
        doubleNode5.next = null;
        doubleNode5.pre = doubleNode4;

        Node reverNode = reverseList(node);
        System.out.println("翻转单链表");
        while (reverNode != null) {
            System.out.println(reverNode.value);
            reverNode = reverNode.next;

        }
        System.out.println("翻转双链表");
        DoubleNode reverDoubleNode = reverseDoubleList(doubleNode1);
        while (reverDoubleNode != null) {
            System.out.println(reverDoubleNode.value);
            reverDoubleNode = reverDoubleNode.next;

        }

    }

    /**
     * 翻转单向链表
     */
    public static Node reverseList(Node head) {
        Node temp = null;
        Node pre = null;
        if (head == null || head.next == null) {
            return head;
        }
        while (head != null) {//防止出现null指针异常
            temp = head.next;
            head.next = pre;
            pre = head; //使用pre,防止 head走到最后null指针了。
            head = temp;//指针继续往下走
        }
        return pre;
    }

    /**
     * 翻转双向链表
     */
    public static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head.pre = next;
            head = next;
        }
        return pre;
    }

    /**
     * 翻转双向链表
     */
    public static DoubleNode reverseDoubleList2(DoubleNode head) {
        //定义节点指向当前节点的前驱节点
        DoubleNode pre = null;
        //定义节点指向当前节点的后继节点
        DoubleNode next = null;
        //遍历链表调整指针指向
        while (head != null) {
            //先记录当前节点的后继节点，以免调整指针的指向后找不到原链表
            next = head.next;
            //调整后继节点
            head.next = pre;
            //调整前驱节点
            head.pre = next;
            //记录当前节点
            pre = head;
            //head != null,说明当前节点不可能为新链表的头结点，因此head向后移动
            head = next;
        }
        //因为head == null,因此当前节点便是翻转后的链表的头结点
        return pre;
    }
}


