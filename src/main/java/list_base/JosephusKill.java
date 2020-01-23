package list_base;

/**
 * Create with: list_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/19 0:41
 * version: 1.0
 * description: 环形单链表的约瑟夫问题
 * 时间复杂度 O(m*n) 空间复杂度O(1)
 * 1. 对链表进行合理性的判断
 * 2. 遍历每一个节点，当报数到了m则删除当前节点
 * 3. 不停的删除直到剩下最后一个节点即为所求
 */
public class JosephusKill {
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Node head = Node.createCricleList();
        Node last = head;
        while (last.next != head) {
            last = last.next;
        }
        Node.PrintCricleList(head, last);
        last = head = josephusKill_advanced(head, 4);
        Node.PrintCricleList(head, last);
        head = null;
        System.out.println();

//        Node head1 = Node.createCricleList(8);
//        Node.CirclePrint(head1);
//        Node.CirclePrint(josephusKill_advanced(head1, 8));
//        head1 = null;

        System.gc();
    }


    // 已知从head开始每报数到m就把这个结点删除
    public static Node josephusKill_normal(Node head, int m) {
        //时间复杂度 O(m*n) 空间复杂度O(1)
        System.out.println("正常版本：将从head结点开始循环报数，\n报到" + m + "的时候就会把这个结点删除直到只剩下一个节点位置。");
        if (head == null || head.next == head || m < 1) {
            return head;
        }
        Node last = head;
        while (head != last.next) {
            last = last.next;
        }
        int count = 0;
        while (head != last) {
            if (++count == m) {
                last.next = head.next;
                count = 0;
            } else {
                last = last.next;
            }
            head = last.next;
        }
        return head;

    }
    //进阶：如果链表节点数为N，想在时间复杂度为O(N)时完成原问题的要求，该怎么实现？
    //https://blog.csdn.net/qq_34342154/article/details/78224712
    public static Node josephusKill_advanced(Node head, int m) {
        System.out.println("高级版本： 将从head结点开始循环报数，\n报到" + m + "的时候就会把这个结点删除直到只剩下一个节点位置。");
        // 排除异常的输入情况
        if (head == null || head.next == head) {
            Node.PrintCricleList(head, head);
        } else if (m < 1) {
            System.out.println("wrong input of m .");
        }
        // 求出这个链表总共有多少个结点
        Node current = head.next;
        int n = 1;
        while (current != head) {
            n++;
            current = current.next;//其实这里 current 已经等于 head 了；
        }
        System.out.println("当前一共有： " + n + "  个节点");
        // 找出最后存活的结点，
        int last_node = getLive(n, m);
        System.out.println("last_node = " + last_node);
        while (--last_node != 0) {
            head = head.next;
        }
        head.next = head;
        // 将这个结点设置为指向自己代表最后幸存者编号
        return head;
    }

    // 有i个结点，循环到m就删除此结点，返回被删除的这个结点的编号 ,这里书上讲的很复杂，参考下循环链表的 指针旋转
    public static int getLive(int n, int m) {
        if (n == 1) {
            return 1;
        } else {
            return (getLive(n - 1, m) + m - 1) % n + 1;  //返回节点编号
        }
    }
}
