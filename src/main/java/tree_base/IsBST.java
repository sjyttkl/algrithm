package tree_base;

import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/15 23:57
 * version: 1.0
 * description:判断一棵树是否为搜索二叉树、判断一棵树是否是完全二叉树
 * 1. 判断为搜索二叉树，只需要中序遍历是升序即可
 */
public class IsBST {
    //1. 判断一棵树是否为搜索二叉树
    public static boolean isBST1(TreeNode head) {
        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            int pre = 0;
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    if (pre <= head.value) {//一定要升序，否则就不是搜索二叉树了。
                        pre = head.value;
                    } else {
                        return false;
                    }
                    head = head.right;
                }
            }
        }
        return true;
    }

    //Morris方式的中序遍历，进行判断是否为搜索二叉树
    public static boolean isBST2(TreeNode head) {
        if (head == null) {
            return true;
        }
        boolean res = true;
        TreeNode pre = null;
        TreeNode cur1 = head;
        TreeNode cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) { //建立线索二叉树
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {//删除线索二叉树的指针
                    cur2.right = null;
                }
            }
            if (pre != null && pre.value > cur1.value) {
                res = false;
            }
            pre = cur1;
            cur1 = cur1.right;  //线索二叉树，网上走
        }
        return res;
    }

    /**
     * 2 .判断一棵树是否是完全二叉树 (完全二叉树，左边必须是满二叉树，右边可以缺一层）
     * * *思路:
     * 1. 如果有右孩子，没有左孩子，那肯定不是完全二叉树
     * 2. 当第一次发现左右两个孩子不是双全的时候，后面遍历到的节点全部都是叶节点，否则返回false
     * *流程:层次遍历的流程
     * 1. 判断head是否为null，如果为null返回true，否则创建一个队列和创建一个布尔值leaf，表示判断是否开启叶子阶段
     * 2. 当队列不为null时，从队列弹出一个数，然后获取左右节点。
     * 3. 判断：如果左孩子为null，右孩子不为null，或者开启叶子判断并且左孩子节点或者右孩子节点不为null，则返回false。
     * 4. 如果左节点不为null，加入队列。如果右节点不为null，加入队列。
     * 5. 如果右节点为null，则开启叶子判断。
     */
    public static boolean isCBT(TreeNode head) {
        if (head == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        boolean leaf = false;
        TreeNode l = null;
        TreeNode r = null;
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
                return false;
            }
            if (l != null) {
                queue.offer(l);
            }
            if (r != null) {
                queue.offer(r);
            }else{
                leaf = true;
            }

//            if (l == null || r == null) {
//                leaf = true;
//            }
        }
        return true;
    }

}
