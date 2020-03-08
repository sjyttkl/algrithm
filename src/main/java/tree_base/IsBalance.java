package tree_base;

import sun.reflect.generics.tree.Tree;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/15 0:19
 * version: 1.0
 * description: 判断是否为平衡二叉树
 */
public class IsBalance {

    public static boolean isBalance(TreeNode node) {
        boolean[] res = new boolean[1];
        res[0] = true;//
        getHeight(node, 1, res);
        return res[0];
    }

    public static int getHeight(TreeNode head, int level, boolean[] res) {
        if (head == null) {
            return level;
        }
        int lH = getHeight(head.left, level + 1, res);
        if (!res[0]) {
            return level;
        }
        int rH = getHeight(head.right, level + 1, res);
        if (!res[0]) {
            return level;
        }
        if (Math.abs(lH - rH) > 1) {
            res[0] = false;
        }
        return Math.max(lH, rH);
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
        System.out.println(isBalance(node));

    }
}
