package list_base;

/**
 * Create with: list_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/30 3:38
 * version: 1.0
 * description:合并两个有序链表
 * 题目
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * <p>
 * 思路
 * 依次比较两个链表中的数大小然后添加链表即可.技巧是可以先创建一个头结点,然后返回这个头结点的next,这样就比较容易实现
 * 递归
 * 非递归
 */
public class MergeTwoLists {
    public Node mergeTwoLists(Node l1, Node l2) {
        // 类似归并排序中的合并过程
        Node dummyHead = new Node(0);
        Node cur = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.value < l2.value) {
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            } else {
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }
        // 任一为空，直接连接另一条链表
        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }
        return dummyHead.next;
    }

    /**
     * 递归方式合并两个单链表
     *
     * @param head1 有序链表1
     * @param head2 有序链表2
     * @return 合并后的链表
     */
    public static Node mergeTwoList(Node head1, Node head2) {
        //递归结束条件
        if (head1 == null && head2 == null) {
            return null;
        }
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        //合并后的链表
        Node head = null;
        if (head1.value > head2.value) {
            //把head较小的结点给头结点
            head = head2;
            //继续递归head2
            head.next = mergeTwoList(head1, head2.next);
        } else {
            head = head1;
            head.next = mergeTwoList(head1.next, head2);
        }
        return head;
    }

    /**
     * 非递归方式
     *
     * @param head1 有序单链表1
     * @param head2 有序单链表2
     * @return 合并后的单链表
     */
    public static Node mergeTwoList2(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return head1 != null ? head1 : head2;
        }
        //合并后单链表头结点
        Node head = head1.value < head2.value ? head1 : head2;

        Node cur1 = head == head1 ? head1 : head2;
        Node cur2 = head == head1 ? head2 : head1;

        Node pre = null;//cur1前一个元素
        Node next = null;//cur2的后一个元素

        while (cur1 != null && cur2 != null) {
            //第一次进来肯定走这里
            if (cur1.value <= cur2.value) {
                pre = cur1;
                cur1 = cur1.next;
            } else {
                next = cur2.next;
                pre.next = cur2;
                cur2.next = cur1;
                pre = cur2;
                cur2 = next;
            }
        }
        pre.next = cur1 == null ? cur2 : cur1;
        return head;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        node1.next = node3;
        node3.next = node5;
        node2.next = node4;
//          Node node = mergeTwoList(node1, node2);
        Node node = mergeTwoList2(node2, node1);
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
    }

}
