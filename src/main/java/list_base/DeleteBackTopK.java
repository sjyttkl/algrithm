package list_base;

/**
 * Create with: list_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/17 1:07
 * version: 1.0
 * description: 返回链表倒数K 个值(注意是删除不是 找到）
 */
public class DeleteBackTopK {
    public static void main(String args[]) {
        Node head = Node.createList();
        Node.PrintList(removeLastKthNode(head,1));

        DoubleNode doubleList = DoubleNode.createDoubleList();
        DoubleNode.PrintList(removeLastKthNode(doubleList,1));
    }
    //双链表 删除倒数第 k个值
    public static DoubleNode removeLastKthNode(DoubleNode head, int lastKth) {
        if (head == null || lastKth < 1) {
            return head;
        }
        // 头结点的位置在不确定是不是删除头结点的情况下不能改动，
        // 需要用另外一个结点来代替他移动
        DoubleNode curr = head;
        DoubleNode next = null;
        while (curr != null) {
            lastKth--;
            curr = curr.next;
        }
        if (lastKth == 0) {
            next = head.next.next;// k 需要剔除  第一个node
            head.next.next.pre = head;
            head.next = next;
        } else if (lastKth < 0) {
            curr = head;
            while (++lastKth != 0) {// 如果用++lastKth则会导致 NullPointerException异常
                curr = curr.next;   //这里剔除需要删除第K值的前一个。
            }
            next = curr.next.next;
            if(next != null){ //防止，下一node 为null 这样pre 就不存在。
                curr.next.next.pre = curr;
            }
            curr.next = next;
        }
        return head;

    }

    //单链表 删除倒数第 k个值
    public static Node removeLastKthNode(Node head, int lastKth) {
        if (head == null || lastKth < 1) {
            return head;
        }
        Node cur = head;
        while (cur != null) {
            cur = cur.next; // 这里cur  最终会 移动到 末尾+1 的位置， 这样在后面才会移动到需要剔除 位置的前一位。
            lastKth--;      //这里 lastKth 会 计算  末尾+1 的位置，这样在后面才会移动到需要剔除 位置的前一位。
        }
        if (lastKth == 0) {
            head.next = head.next.next; // k是第一个node
        }
        if (lastKth < 0) {
            cur = head; //需要重头再来一遍
            while (++lastKth != 0) { //如果用++lastKth则会导致 NullPointerException异常
                cur = cur.next;  //   - head 1 2 3 4 5     1 0 -1 -2 -3  -4     -2  -1 0    这里剔除需要删除第K值的前一个。
            }
            cur.next = cur.next.next;
        }
        return head;

    }

}
