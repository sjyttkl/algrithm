package list_base;

/**
 * Create with: list_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/29 2:06
 * version: 1.0
 * description:  url : https://blog.csdn.net/e01528/article/details/85784496
 * 两个单链表相交的一系列问题
 * 【 题目】 给定两个可能有环也可能无环的单链表， 头节点head1和head2。 请实现一个函数， 如果两个链表相交， 请返回相交的 第一个节点。
 * 如果不相交， 返回null
 * 【 要求】 如果两个链表长度之和为N， 时间复杂度请达到O(N)， 额外空间复杂度请达到O(1)。
 */
public class LoopNode {

    // 首先，我们判断是有环还是无环，如果有环，返回入环的第一个节点。无环null。
    //简单方法：采用hashset，不断next往里面赋值，如果hashset里有重复的node出现，则这个节点就是，如果next出现了null就无环。
    //省空间的做法：快慢指针。当快指针与慢指针相遇，则有环，此时将快指针=head，然后快指针和慢指针都变成1步长，再相遇的地方就是入环节点。中途两个指针如果遇到null就是无环的结构。

    /**
     * 判断单链表是否是存在环，如果存在则返回环的入口节点
     * @param head 头节点
     * @return 返回入口节点
     */
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node n1 = head.next; // n1 -> slow
        Node n2 = head.next.next; // n2 -> fast
        while (n1 != n2) {
            if (n2.next == null || n2.next.next == null) {
                return null;//无环
            }
            n2 = n2.next.next;
            n1 = n1.next;
        }
        n2 = head; // n2 -> walk again from head
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    // 第二点学习：如果两个无环，找相交
    // 先判断是否相交，两个都遍历到最后一个非null的node，如果相等则相交，如果不等肯定不相交。
    // 相交之后，把长的那一个，先走完（长链表-短链表）插值的长度，然后和慢的一起走，当第一次相遇，返回。

    /**
     * 无环的情况下，返回相交的节点。
     * @param head1 第一个链表的头节点
     * @param head2 第二个链表的头节点
     * @return 相交节点
     */
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
//首先得到两个链表的最后一个节点，如果不相等，那肯定 不相交，相等，先走长的链表将两个链表的差值走完，然后一起走，找
        while (cur1.next != null) {//当cur1变成最后一个就出来了
            n++;//cur的总数
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;//得到差值
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {//如果两个无环链表的最后一个节点不相等，那么不相交
            return null;
        }
        cur1 = n > 0 ? head1 : head2;//谁长谁的head变成cur1
        cur2 = cur1 == head1 ? head2 : head1;//谁短谁的head变成cur2
        n = Math.abs(n);
        while (n != 0) {//先走两个链表的差值
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {//然后一起走，第一次相交的时候就是那个点
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    //    第一种：不相交，
//    第二种：环外相交，
//    第三种：如你所见，如果遇到了，那么两个点都是最近的点，任意返回一个都可以，
//    如果两个环的入环节点相等，就是第二个，不是就是1或3。区分1和3，选一个链表的入环节点开始next遍历，如果在再次遇到这个节点之前没有遇到另一个入环节点就是1，如果相遇了就是3,此时任选一个返回就好了。
//    做法：直接以入环节点为终止条件，其本身就是一个无环相交就第一个节点的问题。
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        //我们用head1表示链表1的头节点，loop1表示链表1入环的第一个节点，同理head2和loop2
        //若loop1=loop2为第二种结构，若loop1不等于loop2，让loop1继续走，若loop1会等于loop2，说明为第三中结构，若loop1转回来了还没遇到loop2为第一种结构。s
        //见图片所示
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) {//表示入环节点相同，为第二种结构
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
        //情况2，和单链表的相交差不多，原先是遇到 null结尾处终止 ，现在是loop处就终止
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {//看能不能遇到loop2，为第三种结构，入环节点不相同
            cur1 = loop1.next;
            while (cur1 != loop1) {//如果都转回自己，还没有遇到loop2，就是情况一
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }


}
