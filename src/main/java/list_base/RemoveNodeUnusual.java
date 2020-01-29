package list_base;

/**
 * Create with: list_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/30 3:31
 * version: 1.0
 * description:
 * 【题目】链表节点值类型为int型，给定一个链表中的节点node，但不给定整个链表的头结点。
 * 如何在链表中删除node？实现这个函数，并分析这么做会出现哪些问题。 *
 * 【要求】时间复杂度O（1）
 */
public class RemoveNodeUnusual {
    //没有头节点，怎么删除第一个节点的情况
    public static void removeNode(Node node) {
        if (node == null) {
            return;
        }
        Node next = node.next;
        if (next == null) {
            throw new RuntimeException("can not remove the last node");
        }
        node.value = next.value;//复制下一个节点
        node.next = next.next; //把下一个节点删除，并连上下下一个节点
    }

}
