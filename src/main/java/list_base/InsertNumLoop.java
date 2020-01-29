package list_base;

/**
 * Create with: list_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/30 3:35
 * version: 1.0
 * description:向有序的环形单链表中插入新节点
 * 题目
 * 一个环形链表从头节点开始的顺序为不降序的顺序，也就是如1->2->2->2->3->4这样的链表叫做不降序，
 * 同时由最后的节点指回头节点。给定这个环形单链表的头节点head和一个整数num，要生成节点值为num的新节点，
 * 并插入环形链表中，保证依然有序。
 * <p>
 * 思路
 * 本题题目不难，但一定要考虑全面
 * <p>
 * 生成新的节点node,节点值为num
 * 如果链表为空，node自己指向自己成环形链表，并返回node
 * 若链表不为空，遍历链表，找到pre<node并且cur>node的位置，并插入，然后返回头节点。这个应该很好理解
 * 对剩余情况分析，若遍历完，发现不存在步骤3中的情况，也就是node无法插入，此时node应该插入头节点的前面，因为此时的情况下，node的值要么大于链表中的所有值，要么小于所有值
 * 针对步骤4中的情况，若node的值是最大的，返回头节点，否则返回node。
 */
public class InsertNumLoop {
    public Node insertNum(Node head, int num) {
        Node node = new Node(num);
        if (head == null) {
            node.next = node;
            return node;
        }
        Node pre = head;
        Node cur = head.next;

        while (cur != head) {
            if (pre.value <= node.value && cur.value >= node.value) {
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        pre.next = node;
        node.next = cur;
        return head.value < node.value ? head : node;
    }
}
