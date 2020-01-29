package list_base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Create with: list_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/17 10:30
 * version: 1.0
 * description:判断一个链表是否为回文结构   1 2 2 1
 */
public class Judge {
    public static void main(String[] args) throws IOException {
        Node head = creat();
        boolean result = judge3(head);
        System.out.println(result);
    }

    //读取输入的字符串而已
    public static Node creat() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] ss = br.readLine().trim().split(" ");
        int len = ss.length;
        Node head = new Node(0);
        Node r = head;
        for (int i = 0; i < len; i++) {
            Node node = new Node(Integer.parseInt(ss[i]));
            r.next = node;
            r = node;
        }
        return head.next; //不带头的 链表
    }

    //解法：利用栈
    public static boolean judge(Node head) {
        Stack<Integer> stack = new Stack<Integer>();
        Node r = head;
        while (r != null) {
            stack.push(r.value);
            r = r.next;
        }
        while (head != null) {
            if (head.value != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    //优化一下只用一半栈  :ListNode r = head.next; ListNode tmp = head;r走一步，tmp走两步，最后r的位子为后半部分位置
    public static boolean judge2(Node head) {
        if (head == null || head.next == null) return true;
        Stack<Integer> stack = new Stack<Integer>();
        Node r = head;
        Node tmp = head.next; //这里写  head 也可以：Node tmp = head;
        while (tmp.next != null && tmp.next.next != null) {
            r = r.next;
            tmp = tmp.next.next;
        }
        while (r != null) {
            stack.push(r.value);
            r = r.next;
        }
        while (!stack.isEmpty()) { //其实，栈了里的数据会比左边的链表数据少一两个（中间值），所以栈里只会存储回文数据。
            if (head.value != stack.pop()) return false;
            head = head.next;
        }
        return true;
    }


    //解法三：反转链表的一半 ：注意走一步，走两步位置不同最后到的点不同！（对比上一题）
        public static boolean judge3(Node head) {
        if (head == null || head.next == null) return true;
        Node low = head.next;//这里写  head 也可以：Node low = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            low = low.next;  //中间节点
            fast = fast.next.next;
        }
        fast = low.next;

        low.next = null;
        Node n3;
        while (fast != null) {
            n3 = fast.next;
            fast.next = fast; //指针往前移动一位
            fast = n3;
        }
        while (head != null && fast != null) {
            if (head.value != fast.value) return false;
            head = head.next;
            fast = fast.next;
        }
        return true;
    }


}
