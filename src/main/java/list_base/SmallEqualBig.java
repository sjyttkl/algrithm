package list_base;

/**
 * Create with: list_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/21 17:46
 * version: 1.0
 * description:将单向链表按某值划分成左边小，中间相等，右边大的形式
 */
//普通方法，将链表节点放到数组然后partition(快速排序）找到  某值的位置
// 进阶方法，将链表划分成三个子链表，然后合并
// 进阶思路：将原链表中的所有节点依次划分为是三个链表，
// 分别为small代表做部分，equal代表中间部分，big代表左部分，例如：链表7->9->1->8->5->2->5，pivot =5，在划分之后，samll，equal，big分别为：
// small：1->2->null
//  equal：5->5->null
//  big：7->9->8->null
//  将small，equal，big三个链表重新连接起来
//  需要注意的是三个链表如何连接

public class SmallEqualBig {
    public static void main(String ars[]) {
        Node head = Node.createList();
        System.out.println("----------普通方式---------");
        head = partitionList_1(head, 7);//普通方式
        Node.PrintList(head);

        System.out.println("----------进阶方式---------");
        head = listPartition(head, 7);
        Node.PrintList(head);

    }

    public static Node lisPartition2(Node head, int privot) {
        Node small = null;
        Node equal = null;
        Node big = null;
        Node endSmall = null;
        Node endEqual = null;
        Node endBig = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < privot) {
                if (small == null) {
                    small = head;
                    endSmall = head;
                } else {
                    endSmall.next = head;
                    endSmall = head;
                }
            } else if (head.value == privot) {
                if (equal == null) {
                    equal = head;
                    endEqual = head;
                } else {
                    endEqual.next = head;
                    endEqual = head;
                }
            } else if (head.value > privot) {
                if (big == null) {
                    big = head;
                    endBig = head;
                } else {
                    endBig.next = head;
                    endBig = head;
                }
            }
            head = next;
        }
        if (endSmall != null) {
            endSmall.next = equal;
            endEqual = endEqual == null ? endSmall : endEqual;
        }
        if (endEqual != null) {
            endEqual.next = big;

        }
        return small != null ? small : equal != null ? equal : big;
    }

    //    时间复杂度O(n),额外空间复杂度O(1)
    public static Node listPartition(Node head, int pivot) {
        Node small = null;    //小的头
        Node equal = null;    //相等的头
        Node big = null;    //大的头
        Node endSmall = null;    //小的尾
        Node endEqual = null;    //相等的尾
        Node endBig = null;        //大的尾

        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (small == null) {
                    small = head;
                    endSmall = head;
                } else {
                    endSmall.next = head;
                    endSmall = head;
                }
            } else if (head.value == pivot) {
                if (equal == null) {
                    equal = head;
                    endEqual = head;
                } else {
                    endEqual.next = head;
                    endEqual = head;
                }
            } else {
                if (big == null) {
                    big = head;
                    endBig = head;
                } else {
                    endBig.next = head;
                    endBig = head;
                }
            }

            head = next;
        }

        //将小的部分和相等的部分连接
        if (endSmall != null) {
            endSmall.next = equal;
            endEqual = endEqual == null ? endSmall : endEqual;
        }
        // 将所有都连接
        if (endEqual != null) {
            endEqual.next = big;
        }
        return small != null ? small : equal != null ? equal : big;
    }


    //普通的需要额外空间O(n)且不能达到稳定性的　方法
    public static Node partitionList_1(Node head, int pivot) { //pivot表示　枢轴；中心点；旋转运动
        if (head == null) {
            return null;
        }
        Node cur = head;
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }//len 就算出 链表的长度
        Node[] nodeArr = new Node[len];
        cur = head;
        for (int i = 0; i < nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }//nodeArr 数组存起来。
        arrPartition(nodeArr, pivot);
        for (int i = 1; i < nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[nodeArr.length - 1].next = null;  //一定要记得把最后一个指针指向null
        return nodeArr[0];
    }

    //数组划分的paration
    public static void arrPartition(Node[] nodeArr, int pivot) {
        int less = -1; //less表示的是 小于pivot 范围的右边。
        int R = nodeArr.length;//R表示的是 大于pivot 范围的 左边。
        int cur = 0;//表示当前位置
        while (cur < R) {
            if (nodeArr[cur].value < pivot) { //小于 privot值放到左边
                swap(nodeArr, ++less, cur++); //这里传入的 less  和 cur一直都是相等 ，
//                System.out.println(less+"  "+cur);
            } else if (nodeArr[cur].value > pivot) {//大于 privot 值的放到右边。
                swap(nodeArr, --R, cur); //注意放到大于区域的时候cur不能++
            } else {
                cur++;
            }
        }
    }

    //交换两个结点
    public static void swap(Node[] arrNode, int a, int b) {
        Node temp = arrNode[a];
        arrNode[a] = arrNode[b];
        arrNode[b] = temp;
    }
}
