package list_base;

/**
 * Create with: list_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/20 20:15
 * version: 1.0
 * description:
 * 给定链表的头节点head，实现删除链表的中间节点的方法；
 * 若为偶数个：比如1234则删除节点2；
 * 给定链表的头节点head，实现删除链表的a/b处节点的方法；
 *
 * 删除a/b处节点
 * 给定整数a和b，实现删除位于a/b位置的节点；
 * 打个比方：1->2->3->4->5 这个链表
 * 如果在区间 （0，1/5]， 删除节点1；
 * 如果在区间 （0，2/5]， 删除节点2；
 * 如果在区间 （0，3/5]， 删除节点3；
 * 如果在区间 （0，4/5]， 删除节点4；
 * 如果在区间 （0，1]， 删除节点5；
 * 可以看出，是在r = ( a / b ) * n 然后将n向上取整得到的节点位置的方法；
 * 所以根本是在于 求出 r的值并且运用Math.ceil()方法向上取整来得到准确的节点值，然后遍历链表删除即可；
 */
public class RemoveByRatio {
    public static Node removeByRatio(Node head, int a, int b) {

        int length = 0;
        Node curr =head;
        while(curr!=null){
            curr = curr.next;
            length ++;
        }
        System.out.print("length = " + length + " ");
        int r = (int) Math.ceil((double) (length * a) / (double) b);//这里 a/b 代表的是比率  ，在乘以 n，表示 这个列表中第几个数
        System.out.println("r = " + r);
        Node current = head;
        while (--r > 1) {
            current = current.next; //到需要剔除该位置的前一个节点
        }
        current.next = current.next.next;
        return head;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Node head = Node.createList();
        removeByRatio(head, 5, 7);
        Node.PrintList(head);
        System.out.println("=====================");
        head = Node.createList();
        removeByRatio(head, 3, 6);
        Node.PrintList(head);
    }

}
