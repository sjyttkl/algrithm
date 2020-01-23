package list_base;

/**
 * Create with: list_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/19 0:30
 * version: 1.0
 * description:翻转部分单链表
 * 问题描述：给定头节点head，两个整数from和to，要求在单向链表上把第from到to个节点之间的部分进行反转
 * 对输入的from和to进行合理性的判断：
 * 1. 遍历链表求出len，1<=from<=to<=len；
 * 同时确定好from的前一个几点和to的下一个节点；
 * 2. 对head节点的重定义：
 * 当在from是要从头开始反转的时候，head应该等于to节点； 若不是则保持原来的节点；
 * 3.对from和to之间的节点进行反转，参考04问题反转链表思想；
 */
public class ReversePart {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Node head = Node.createList();
        Node reverNode = reversePart2(head, 3, 5);
        while (reverNode != null) {
            System.out.print(reverNode.value + " ");
            reverNode = reverNode.next;
        }
    }
    public static Node reversePart2(Node head,int from ,int to){
        int len = 0;
        Node fpre = null;
        Node tpos = null;
        Node curNode = head;
        while(curNode!=null){
            len ++;
            fpre =len==from -1?curNode :fpre;
            tpos =len == to+1?curNode:tpos;
            curNode = curNode.next;
        }
        if(from <1 || from >=to || to>len){
            System.out.println("illegle input of from or to .");
            return null;
        }
        Node node1 = null;
        Node node2 = null;
        Node next = null;
        node1 = fpre == null?head:fpre.next;
        node2 = node1.next;
        node1.next = tpos;
        while(node2!=tpos){
            next = node2.next ;
            node2.next = node1;
            node1 = node2;
            node2 = next;
        }
        if(fpre!=null){
            fpre.next = node1;
            return head;
        }
        return node1;
    }
    public static Node reversePart(Node head, int from, int to) {
        System.out.println("将以head为头节点的链表从第" + from + "个到第" + to + "个之间进行反转。,位置从1开始");
        int len = 0;
        Node fpre = null;//from节点
        Node tpos = null; //to位置下一个
        Node curNode = head;
        // 确定len和fpre，tpos结点；
        while (curNode != null) {
            len++;//说明目前指针到那个位置了。
            // a?b:c 在这里如果c写null那么当len增大
            // 到不符合情况的时候他们就会被设置为null
            fpre = len == from - 1 ? curNode : fpre; //获得当前from节点前一个位置
            tpos = len == to + 1 ? curNode : tpos; //获得当前to节点后一个位置
            curNode = curNode.next;
        }
        // 对输入的from，to进行错误检测，越界判断
        if (from < 1 || from >= to || to > len) {
            System.out.println("illegle input of from or to .");
            return null;
        }
        // 对from到to的结点进行反转
        Node node1 = null;
        Node node2 = null;
        Node next = null;
        // 首先确定是否从头节点开始反转
        node1 = fpre == null ? head : fpre.next;//node1 需要定位到 pre下一个节点(from 节点）
        node2 = node1.next; // node2 定位到 node1下一个节点 （from下下一个节点）
        // 因为node1=node(from)
        // 在最后反转完之后node1是正好在tpos前一个
        node1.next = tpos; //开始连接第一步，
        // 开始反转
        while (node2 != tpos) {

            next = node2.next;
            node2.next = node1;
            node1 = node2;//前进一步
            node2 = next; //前进一步
        }
        // 对反转完之后链表头进行设置
        if (fpre != null) {
            fpre.next = node1;// 这时候的node1 应该是 tops 前一位，所以需要 fpre接下 头
            return head;
        }
        return node1; //如果 fpre 等于空，说明， from 的位置是 1。翻转之后，则to前一个位置，就是 第一位需要返回的节点。
    }
}