package list_base;

import java.util.Stack;

/**
 * Create with: list_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/30 2:35
 * version: 1.0
 * description:将单链表的每K个节点之间逆序
 * 思路:
 * 给定一个单链表的表头节点head，实现一个调整单链表的函数，是的每k个节点之间逆序，如果最后不够k个节点一组，则不调整最后几个节点
 * 思路：
 *
 *   如果k的值小于2，不调整。k<1 没有意义，k==1代表1个节点为1组进行逆序，原链表不变。
 *
 * 介绍两种方法：
 *
 * 方法一  利用栈结构
 *   1、从左到右遍历链表，如果栈的大小不等于k，就将节点不断压入栈中
 *   2、当栈大小第一次到达k，凑齐了k个节点进行逆序。然后弹出，并且连接。第一组逆序完成后，记录新的头，同时第一组最后一个（原头）连接下一个节点
 * 
 *方法二、不需要栈结构，在原链表中直接调整 *
 *  用变量记录每一组开始的第一个节点和最后一个节点。然后直接逆序调整，把这一组的节点都逆序。
 * 和方法一一样，同样需要注意第一组节点的特殊处理，以及之后的每个组在逆序重连之后，
 * 需要让该组的第一个节点（原来是最后一个节点）被之前的最后一个连上，将该组的最后一个节点连接下一个节点
 */
public class reverseKNodes {
    //方法一
    public Node reverseKNodes1(Node head, int k) {

        if (k < 2) {
            return head;
        }
        Stack<Node> stack = new Stack<Node>();
        Node newHead = head;
        Node cur = head;
        Node pre = null;//当前k个节点最前一个节点
        Node next = null;
        while (cur != null) {
            next = cur.next;//当前节点的下一个节点
            stack.push(cur);
            if (stack.size() == k) {
                pre = resign1(stack, pre, next);
                newHead = newHead == head ? cur : newHead;
            }
            cur = next;
        }
        return newHead;
    }

    public Node resign1(Stack<Node> stack, Node left, Node right) {
        Node cur = stack.pop();
        if (left != null) {
            left.next = cur;
        }
        Node next = null;
        while (!stack.isEmpty()) {
            next = stack.pop();
            cur.next = next;
            cur = next;
        }
        cur.next = right;
        return cur;
    }

    //方法二
    public Node reverseKNode2(Node head, int k){

        if(k<2){
            return head;
        }
        Node cur = head;
        Node start=null;
        Node pre=null;
        Node next=null;
        int count=1;
        while(cur!=null){
            next = cur.next;
            if(count==k){
                start=pre==null?head:pre.next;
                head=pre==null?cur:head;
                resign2(pre,start,cur,next);// left,start,end,right;
                pre=start;
                count=0;
            }
            count ++;
            cur=next;
        }
        return head;

    }

    public void resign2(Node left, Node start, Node end, Node right){

        Node pre=start;
        Node cur=start.next;
        Node next=null;
        while(cur!=null){
            next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        if(left!=null){
            left.next=end;
        }
        start.next=right;
    }

}
