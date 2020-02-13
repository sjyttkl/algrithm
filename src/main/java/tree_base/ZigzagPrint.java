package tree_base;

import sun.reflect.generics.tree.Tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/13 23:58
 * version: 1.0
 * description:
 //二叉树的按层打印与ZigZag打印

 */
public class ZigzagPrint {
    //二叉树节点的定义

    //二叉树按照层打印(运用队列的方式打印)
    public static void LayerPrint(TreeNode head) {
        if (head == null) {
            return;
        }

        //运用队列的方式存储
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        int level = 1;
        TreeNode last = head; //当前行的最后节点
        TreeNode nlast = null; //下一行的最后节点
        queue.offer(head);
        System.out.print("Level" + (level++) + ":");
        while (!queue.isEmpty()) {
            head = queue.poll();
            System.out.print(head.value + " ");
            if (head.left != null) {
                //左节点压入队列
                queue.offer(head.left);
                nlast = head.left;
            }
            if (head.right != null) {
                //右节点压入队列
                queue.offer(head.right);
                nlast = head.right;
            }
            //换到下一行打印
            if (head == last && !queue.isEmpty()) {
                System.out.print("\nlevel" + (level++) + ":");
                last = nlast;  //换到下一行的最后一个元素
            }

        }

        System.out.println();

    }

    //二叉树按照Ziazag打印(利用双端队列)
    public static void ZigzagPrint(TreeNode head) {
        if (head == null) {
            return;
        }
        /**
         System.out.print(head.value);
         if(head.right!=null)
         {
         System.out.print(head.right.value);
         }
         if(head.left!=null)
         {
         System.out.print(head.left.value);
         }
         */
        //运用双端队列的方式存储(基数行左->右,偶数行右->左)
        Deque<TreeNode> dq = new LinkedList<TreeNode>();  //双端队列
        int level = 1;
        boolean lr = true;  //记录打印的方向
        TreeNode last = head; //当前行的最后节点
        TreeNode nlast = null; //下一行的最后节点
        dq.offerFirst(head);
        printLevelAndOrientation(level++, lr);
        while (!dq.isEmpty()) {
            if (lr) //奇数行(尾进头出)
            {
                head = dq.pollFirst();
                if (head.left != null) {
                    nlast = nlast == null ? head.left : nlast;
                    dq.offerLast(head.left);
                }
                if (head.right != null) {
                    nlast = nlast == null ? head.right : nlast;
                    dq.offerLast(head.right);
                }

            } else //偶数行(头进尾出)
            {
                head = dq.pollLast();
                if (head.right != null) {
                    nlast = nlast == null ? head.right : nlast;
                    dq.offerFirst(head.right);
                }
                if (head.left != null) {
                    nlast = nlast == null ? head.left : nlast;
                    dq.offerFirst(head.left);
                }
            }
            System.out.print(head.value + " ");

            if (head == last && !dq.isEmpty()) {
                lr = !lr; //进行换行的操作
                last = nlast;
                nlast = null;
                System.out.println();
                printLevelAndOrientation(level++, lr);
            }

        }
        System.out.println();

    }

    //显示打印的方向
    public static void printLevelAndOrientation(int level, boolean lr) {
        System.out.print("Level" + level + " from ");
        System.out.print(lr ? "left to right " : "right to left: ");
    }


    public static void main(String[] args) {

        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.right.left = new TreeNode(5);
        node.right.right = new TreeNode(6);
        node.right.left.left = new TreeNode(7);
        node.right.left.right = new TreeNode(8);
        PrintBinaryTree.printTree(node);
        System.out.println("按照层遍历:");
        LayerPrint(node);
        System.out.println("按照Zigzag遍历:");
        ZigzagPrint(node);


    }

}
