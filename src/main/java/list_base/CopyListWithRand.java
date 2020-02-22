package list_base;

import java.util.HashMap;

/**
 * Create with: list_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/24 0:25
 * version: 1.0
 * description:
 */
//Node类中的value是节点值，next指针和正常单链表中next指针的意义
//  一 样，都指向下一个节点，rand指针是Node类中新增的指针，这个指
// 针可 能指向链表中的任意一个节点，也可能指向null。 给定一个由
// Node节点类型组成的无环单链表的头节点head，请实现一个 函数完成
//这个链表中所有结构的复制，并返回复制的新链表的头节点。
// 进阶：不使用额外的数据结构，只用有限几个变量，且在时间复杂度为 O(N) 内完成原问题要实现的函数。

public class CopyListWithRand {
    //使用额外 map数据结构进行 复制随机链表。
    public static RandNode copyListWithRand1(RandNode head) {
        HashMap<RandNode, RandNode> map = new HashMap<RandNode, RandNode>();
        RandNode cur = head;//cur头节点，用于遍历链表
        while (cur != null) {//遍历链表
            map.put(cur, new RandNode(cur.value));//（原链表节点，副节点）
            cur = cur.next;//指向下一个节点
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }
    public static RandNode copyListWithRand2(RandNode head) {
        if (head == null) {
            return null;
        }
        RandNode cur = head;
        RandNode next = null;
        // copy node and link to every node 最终变成：1 1 2 2 3 3 4 4 5 5 6 6
        while (cur != null) {
            next = cur.next;
            cur.next = new RandNode(cur.value);
            cur.next.next = next;
            cur = next;//指针移动到下一个节点
        }
        cur = head;
        RandNode curCopy = null;
        // set copy node rand
        while (cur != null) {
            next = cur.next.next;//原始节点
            curCopy = cur.next;//上个循环复制的节点
            curCopy.rand = cur.rand != null ? cur.rand.next : null;//这里也是复制rand节点，next是之前复制的节点
            cur = next;//指针移动到下一个节点
        }
        RandNode res = head.next;//复制过的头节点
        cur = head; //原始头节点
        // split
        while (cur != null) {   //1 1 2 2 3 3 4 4 5 5 6 6
            next = cur.next.next;//原始节点，下一个节点
            curCopy = cur.next;//复制节点相连,
            cur.next = next;//原始节点相连
            curCopy.next = next != null ? next.next : null;
            cur = next;//指针移动到下一个节点
        }
        return res;
    }

    public static void printRandLinkedList(RandNode head) {
        RandNode cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        RandNode head = null;
        RandNode res1 = null;
        RandNode res2 = null;
//        printRandLinkedList(head);
//        res1 = copyListWithRand1(head);
//        printRandLinkedList(res1);
//        res2 = copyListWithRand2(head);
//        printRandLinkedList(res2);
//        printRandLinkedList(head);
//        System.out.println("=========================");

        head = new RandNode(1);
        head.next = new RandNode(2);
        head.next.next = new RandNode(3);
        head.next.next.next = new RandNode(4);
        head.next.next.next.next = new RandNode(5);
        head.next.next.next.next.next = new RandNode(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

    }


}
