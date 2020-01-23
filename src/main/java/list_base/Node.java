package list_base;

/**
 * Create with: list_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/16 15:35
 * version: 1.0
 * description: 链表 定义
 */

/**
 * 单链表
 */
public class Node {
    public Node next;
    public int value;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }

    //循环单链表
    public static Node createCricleList() {
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
        node7.next = node;
        Node head = node;
        Node last = node7;
        System.out.print("循环单链表初始化结构如下 : ");
        while (head != last) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
        return node;
    }

    //循环单链表
    public static void PrintCricleList(Node head, Node last) {
        System.out.print("循环单链表 当前 初始化结构如下 :");
        while (head != last) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        if (head == last) {
            System.out.print(head.value + " ");
        }
        System.out.println();
    }

    //单链表
    public static Node createList() {
        Node node = new Node(0);
        Node node1 = new Node(2);
        Node node2 = new Node(6);
        Node node3 = new Node(3);
        Node node4 = new Node(9);
        Node node5 = new Node(1);
        Node node6 = new Node(10);
        Node node7 = new Node(4);

        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        Node head = node;
        System.out.print("单链表初始化结构如下 : ");
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
        return node;
    }

    public static void PrintList(Node node) {
        Node head = node;
        System.out.println("单链表表 当前 结构如下 :");
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

}

/*
 * 双链表
 * */
class DoubleNode {
    public DoubleNode pre;
    public DoubleNode next;
    public int value;

    public DoubleNode(int value) {
        this.value = value;
        this.next = null;
        this.pre = null;
    }

    public static DoubleNode createDoubleList() {
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

        DoubleNode head = doubleNode;
        System.out.println("双链表 当前 结构如下 :");
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
        return doubleNode;
    }

    public static void PrintList(DoubleNode doubleNode) {
        DoubleNode head = doubleNode;
        System.out.println("双链表初始化结构如下 :");
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }
}

class RandNode {
    public int value;
    public RandNode next;
    public RandNode rand;

    public RandNode(int data) {
        this.value = data;
    }
}

