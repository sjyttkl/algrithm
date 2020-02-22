package list_base;

/**
 * Create with: list_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/30 3:29
 * version: 1.0
 * description: 给个单链表表实现选择排序，空间复杂度O(1)
 * 关键点：
 * 链表在逻辑上分成排序部分和未排序部分。利用选择排序，从未排序的链表中找到最小值，删除后链接在排好序部分的尾部 ，逐渐将未排序的部分缩小，最后变成排好序的部分。
 * 每次在未排序的链表中删除最小值节点的时候要保证未排序链表在结构上不断，故要找到删除节点的前一个节点
 */
public class SelectSortList {
    public static Node selectSort(Node head) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        Node tail = null;//排序部分的尾部
        Node minNode = null;//最小值节点
        Node minPre = null;//最小节点的前一个节点
        while (cur != null) {
            //1、每次在未排序的链表中找到最小值节点，然后把个节点从中删除，
            //删除过程中保证未排序部分的链表在结构上不断开，需要找到最小节点的前一个节点
            minNode = cur;
            minPre = getMinPre(cur);//返回最小值前一个节点
            //最小节点不是当前未排序链表的头结点，从未排序列中删除最小节点
            if (minPre != null) {
                minNode = minPre.next;
                minPre.next = minNode.next;
            }
            //最小节点是当前未排序链表的头节点
            cur = cur == minNode ? cur.next : cur;
            //2、把每次的最小值节点连接到排好序部分的尾部
            if (tail == null) {
                head = minNode;
            } else {
                tail.next = minNode;
            }
            tail = minNode;

        }
        return head;
    }

    //从无序链表中找到最小值节点的前一个节点并返回
    public static Node getMinPre(Node head) {
        Node minNode = head;//最小值节点
        Node minPre = null;//最小值节点的前一个节点
        Node pre = head;//记录当前节点cur的前一个节点
        Node cur = head.next;
        while (cur != null) {
            if (cur.value < minNode.value) {
                minPre = pre;
                minNode = cur;
            }
            pre = cur;
            cur = cur.next;
        }
        return minPre;
    }

}
