package list_base;

/**
 * Create with: list_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/17 0:55
 * version: 1.0
 * description:打印两个有序链表公共的部分
 */
public class CommonPart {
    public static void main(String args[]) {
        Node node = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node41 = new Node(4);

        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;


        node41.next = node5;
        node5.next = node6;
        node5.next = node7;
        printCommentPart(node, node41);
    }

    public static void printCommentPart(Node head1, Node head2) {
        System.out.println("Common Part:");
        while (head1 != null && head2 != null) {
            if (head1.value > head2.value) {
                head2 = head2.next;
            } else if (head1.value < head2.value) {
                head1 = head1.next;
            } else {
                System.out.println(head1.value + "  ");
                head1 = head1.next;
                head2 = head2.next;

            }
        }
        System.out.println();

    }

}
