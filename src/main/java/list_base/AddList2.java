package list_base;

import java.util.Stack;

/**
 * Create with: list_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/24 0:39
 * version: 1.0
 * description:
 * 假设链表中每一个节点的值都在0-9之间，那么链表整体就可以代表一个整数。例如9->3->7，代表937.
 * 给定两个这种链表的头节点head1和head2，请生成代表两个整数相加值的结果链表。
 * 例如：9->3->7和6->3，相加结果为1->0->0->0
 */
public class AddList2 {
    //两个单链表生成相加链表
    public static void main(String args[]){
        Node head1 = Node.createList();
        Node head2 = Node.createList();
        Node.PrintList(addList1(head1,head2));
        Node.PrintList(addList2(head1,head2));
    }
    //方法1：利用栈结构求解
    public static Node addList1(Node head1, Node head2) {
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();
        //压栈存数，如9->3->7变成7->3->9
        while (head1 != null) {
            s1.push(head1.value);
            head1 = head1.next;
        }
        while (head2 != null) {
            s2.push(head2.value);
            head2 = head2.next;
        }
        Node node = null;
        Node pre = null;
        int n1 = 0;
        int n2 = 0;
        int ca = 0;//存进位
        int n = 0;//存相加值
        while (!s1.isEmpty() || !s2.isEmpty()) {
            n1 = s1.isEmpty() ? 0 : s1.pop();
            n2 = s2.isEmpty() ? 0 : s2.pop();
            n = n1 + n2 + ca;//ca是进位 保留
            pre = node;//低位
            node = new Node(n % 10);//对个位进行 创建新节点
            node.next = pre;//高位指向低位，反转链表都免了。
            ca = n / 10;//去除他的 十进制位置
        }
        if (ca == 1) {//两表都遍历完了，还要判断是否有进位
            pre = node;
            node = new Node(1);
            node.next = pre;
        }
        return node;
    }

    //方法2：利用链表的逆序求解，省掉用栈的空间
    public static Node addList2(Node head1, Node head2) {
        head1 = reList(head1);
        head2 = reList(head2);
        int ca = 0;
        int n1 = 0;//存节点值
        int n2 = 0;
        int n = 0;
        Node c1 = head1;//存节点
        Node c2 = head2;
        Node pre = null;
        Node node = null;
        while (c1 != null || c2 != null) {
            n1 = c1 != null ? c1.value : 0;
            n2 = c2 != null ? c2.value : 0;
            n = n1 + n2 + ca;
            pre = node;
            node = new Node(n % 10);
            node.next = pre;
            ca = n / 10;
            c1 = c1 != null ? c1.next : null;
            c2 = c2 != null ? c2.next : null;
        }
        if (ca == 1) {
            pre = node;
            node = new Node(1);
            node.next = pre;
        }
        reList(head1);//还原
        reList(head2);
        return node;//node是最高位，头节点
    }

    //链表逆序
    public static Node reList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;//->变为<-
            pre = head;//pre和head都右移，遍历后面的
            head = head.next;
        }
        return pre;
    }
}
