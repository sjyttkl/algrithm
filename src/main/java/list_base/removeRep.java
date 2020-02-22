package list_base;

import sun.awt.image.PNGImageDecoder;

import java.util.HashSet;

/**
 * Create with: list_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/30 3:02
 * version: 1.0
 * description: *
 * 给定一个无序单链表的头结点head，删除其中值重复出现的节点。
 * 例如：1->2->3->3->4->4->2->1->1->null，删除重复的节点之后为1->2->3->4->null。
 * <p>
 * 方法1：时间复杂度O(N)
 * 方法2：额外空间复杂度O(1)
 */
public class removeRep {
    //删除无序单链表中值重复出现的节点
    //方法1：时间复杂度O(N),额外空间复杂度O(N)
    public static void removeRep1(Node head) {
        if (head == null) {
            return;
        }
        HashSet<Integer> hs = new HashSet<Integer>();
        Node pre = head;
        Node cur = head.next;
        hs.add(head.value);//头结点直接加到链表
        while (cur != null) {
            if (!hs.contains(cur.value)) {
                hs.add(cur.value);
                pre = cur;
            } else {
                pre.next = cur.next;//重复节点，删除
            }
            cur = cur.next;
        }
    }

    //方法2：时间复杂度O(N^2),额外空间复杂度O(1)
    public static void removeRep2(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node pre = null;
        Node next = null;
        //类似选择排序，选中一个节点，挨个比较后面是否有重复的节点，有则删除
        while (cur != null) {
            pre = cur;
            next = cur.next;
            while (next != null) {
                if (cur.value == next.value) {//后面next的节点等于当前cur节点，删除next节点
                    pre.next = next.next;
                } else {//不重复
                    pre = next;
                }
                next = next.next;
            }
            cur = cur.next;//再以下一个节点作为参考点
        }
    }

    //【另外一种解法-递归思想】
    public Node deleteDuplication(Node pHead) {
        if (pHead == null || pHead.next == null) { // 只有0个或1个结点，则返回
            return pHead;
        }
        if (pHead.value == pHead.next.value) { // 当前结点是重复结点
            Node pNode = pHead.next;
            while (pNode != null && pNode.value == pHead.value) {
                // 跳过值与当前结点相同的全部结点,找到第一个与当前结点不同的结点
                pNode = pNode.next;
            }
            return deleteDuplication(pNode); // 从第一个与当前结点不同的结点开始递归
        } else { // 当前结点不是重复结点
            pHead.next = deleteDuplication(pHead.next); // 保留当前结点，从下一个结点开始递归
            return pHead;
        }
    }
}

