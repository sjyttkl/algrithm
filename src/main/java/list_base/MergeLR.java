package list_base;

/**
 * Create with: list_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/30 3:44
 * version: 1.0
 * description:按照左右半区的方式重新组合单链表
 * 给定一个单链表的头部节点head，链表长度为N，如果N为偶数，那么前 N/2个节点算作半左区，后N/2个节点算右半区；
 * 如果N为奇数，那么前N/2为左半区，后半区N/2+1个节点为右半区。左半区从左向右记为 L1->L2->...，
 * 右半区从左到右依次记为 R1->R2->...，则链表调整为 L1->R1->L2->R2->....的形式。
 *
 * 把链表分成左右半区，奇数个节点则右半区长一点，重新交叉排列成链表，如下例：
 * 1,2,3,4,5,# =>1,3,2,4,5,#
 * 1,2,3,4,#=>1,3,2,4,#
 *
 * 分析：
 *
 * 1. 链表为空或长度为1，直接返回
 * 2. 长度不为1，遍历链表找到中间节点
 * 3. 将链表分为左右两个链表，分别记为 left 和 right
 * 4. 将两个链表按照题目合并起来
 */
public class MergeLR {
    public void relocate(Node head) {
        if (head == null || head.next == null)
            return;
        Node mid = head, right = head.next;
        while (right.next != null || right.next.next != null) {
            mid = mid.next;
            right = right.next.next;
        }
        right = mid.next;
        mid.next = null;
        mergeLR(head, right);
    }

    public void mergeLR(Node left, Node right) {
        Node next = null;
        while (left.next != null) {
            next = right.next;
            right.next = left.next;
            left.next = right;
            left = right.next;
            right = next;
        }
        left.next = right;
    }
}
