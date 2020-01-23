package list_base;

/**
 * Create with: list_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/16 15:32
 * version: 1.0
 * description:如何找到环的入口点
 * 思路：
 * 如果单链表有环，当slow指针和fast指针相遇时，slow指针还没有遍历完链表，而fast指针已经在环内循环n（n>=1）圈了，
 * 假设此时slow指针走了s步，fast指针走了2s步，r为fast在环内转了一圈的步数，a为从链表头到入口点的步数，b为从入口点到相遇点的步数，
 * c为从相遇点再走c步到达入口点，L为整个链表的长度。
 */
public class FindLoopPort {
    public  static void main(String args[])
    {
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
        node6.next = node4;
        System.out.println(findLoopPort(node).value);
    }

    public static Node findLoopPort(Node head) {
        Node slow = head;
        Node fast = head;
        //先判断该链表是否有环
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        //如果链表有环，则将slow设置指向链表头，此时fast指向相遇点，然后同时开始移动，直到两个指针相遇
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
