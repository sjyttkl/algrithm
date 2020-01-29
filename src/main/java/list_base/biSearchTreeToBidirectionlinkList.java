package list_base;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Create with: list_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/30 3:11
 * version: 1.0
 * description:将搜索二叉树转换成双向链表
 * 对二叉树的节点来书，有本身的值域，有指向左孩子和右孩子的指针；对双链表的节点来说，有本身的值域，有指向上一个节点和下一个节点的指针。在结构上，两种结构有相似性，现在有一棵搜索二叉树，请将其转换为一个有序的双向链表。
 * 思路一：
 * 使用辅助队列，先遍历二叉搜索树，将节点存入一个队列，再依次出队中元素，将先后出队的节点前后链接起来。时间复杂度为O(N),空间复杂度为O
 * 思路二：
 * 使用递归，直接在二叉搜索树上修改指针，不需要使用辅助队列，时间复杂度是O(n),空间复杂度是树的高度；
 */
public class biSearchTreeToBidirectionlinkList {


    //(方法一:利用队列(栈)存储查询的结果)将搜索二叉树转换成双向链表
    public static TreeNode twoList(TreeNode head) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        InOrderToQueue(head, queue);
        //二叉树为空
        if (queue.isEmpty()) {
            return head;
        }
        head = queue.poll(); //弹出队列的第一个节点

        TreeNode pre = head;
        pre.left = null;
        TreeNode cur = null;
        //重新组合成双向链表
        while (!queue.isEmpty()) {
            cur = queue.poll();
            pre.right = cur;
            cur.left = pre;
            pre = cur;

        }
        pre.right = null;
        return head;

    }

    //递归将二叉树的中序遍历结果放入队列中
    public static void InOrderToQueue(TreeNode head, Queue<TreeNode> queue) {
        if (head == null) {
            return;
        }
        InOrderToQueue(head.left, queue);
        queue.offer(head);
        InOrderToQueue(head.right, queue);
    }

    //-------------------------------------------------------------------
    //(方法二:利用递归函数)将搜索二叉树转换成双向链表
    // 二叉搜索树转换成双向链表--用递归,
    public static TreeNode biSearchTreeToBidirectionlinkList(TreeNode head) {
        if (head == null)
            return null;
        TreeNode tail = bstTolist(head);
        head = tail.right;
        tail.right = null;
        return head;
    }

    // 二叉搜索树转换成双向链表的递归函数
    public static TreeNode bstTolist(TreeNode head) {
        if (head == null)
            return null;

        TreeNode leftE = bstTolist(head.left);// left end
        TreeNode rightE = bstTolist(head.right);// right end
        TreeNode leftS = leftE != null ? leftE.right : null; // left start
        TreeNode rightS = rightE != null ? rightE.right : null; // right start

        if (leftE != null && rightE != null) {
            leftE.right = head;
            head.left = leftE;
            head.right = rightS;
            rightS.left = head;
            rightE.right = leftS;
            return rightE;
        } else if (leftE != null) {
            leftE.right = head;
            head.left = leftE;
            head.right = leftS;
            return head;
        } else if (rightE != null) {
            head.right = rightS;
            rightS.left = head;
            rightE.right = head;
            return rightE;
        } else {
            head.right = head;
            return head;
        }
    }
    //--------------------------------------------------------------------
    //打印双向链表
    public static void PrintList(TreeNode head)
    {
        while (head!=null)
        {
            System.out.print(head.val+" ");
            head=head.right;
        }
        System.out.println();
    }
    public static void main(String []args)
    {

        //建立二叉搜索树
        TreeNode node=new TreeNode(6);
        node.left=new TreeNode(4);
        node.right=new TreeNode(7);
        node.left.left=new TreeNode(2);
        node.left.right=new TreeNode(5);
        node.right.right=new TreeNode(9);
        node.left.left.left=new TreeNode(1);
        node.left.left.right=new TreeNode(3);
        node.right.right.left=new TreeNode(8);

//        TreeNode mode=twoList(node);
        TreeNode mode=biSearchTreeToBidirectionlinkList(node);
        PrintList(mode);

    }

}
