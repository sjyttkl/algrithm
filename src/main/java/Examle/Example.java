package Examle;

import list_base.Node;

/**
 * Create with: Examle
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/19 14:15
 * version: 1.0
 * description:
 */
public class Example {
    public static void main(String args[]){

    }
    public static Node reversePart(Node head, int from, int to) {
        int len = 0;
        Node fpre = null;
        Node tpos = null;
        Node curNode =head;
        while(curNode!=null){
            len ++;
            fpre=len==from-1? curNode:fpre;
            tpos=len==to+1 ?curNode:tpos;
            curNode = curNode.next;
        }
        if(from <1 || from >to || to>len){
            System.out.println("illegle input of from or to .");
            return null;
        }
        Node node1 = null;
        Node node2 = null;
        Node next = null;
        node1 = fpre==null?head:fpre.next;
        node2 = node1.next;
        node1.next = tpos;
        while(node2!=tpos){
            next = node2.next;
            node2.next = node1;
            node1 = node2;
            node2 = next;
        }
        if(fpre !=null){
            fpre.next = node1;
            return head;
        }
        return node1;

    }
}
