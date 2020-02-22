package list_base;

import java.util.Stack;

/**
 * Create with: list_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/30 3:07
 * version: 1.0
 * description:在单链表中删除指定值的节点
 * 给定一个链表的头节点head和一个整数num，实现一个函数删除链表中值为num的所有节点。
 * 例如，链表为 1->2->3->4->null ,num 为3，删除后，链表变为 1->2->4->null
 */
public class RemoveListNodeOfValue {
    /**
     * 方法一，使用栈或者其他容器收集节点的方法，其时间复杂度是 O(N)，空间复杂度是O(N)
     */
    public Node removeValue1(Node head, int num) {
        Stack<Node> stack = new Stack<Node>();
        while (head != null) {
            if (head.value != num) {
                stack.push(head);
            }
            head = head.next;
        }
        while (!stack.isEmpty()) {
            stack.peek().next = head;
            head = stack.pop();
        }
        return head;
    }

    /**
     * 方法二，不使用任何容器，直接调整的方法，其时间复杂度是 O(N)，空间复杂度是O(1)
     */
    public Node removeValue2(Node head, int num) {
        while (head != null) {//这里主要是防止头节点就是需要删除的节点
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
