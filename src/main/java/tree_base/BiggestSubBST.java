package tree_base;

import sun.reflect.generics.tree.Tree;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/12 12:06
 * version: 1.0
 * description:
 * 给定一棵二叉树的头节点head，已知其中所有节点的值都不一样，找到含有节点最多的搜索二叉子树，并返回这棵子树的头节点。
 * 例如，下图中，右树就是左树的最大搜索子树。
 *
 * 解题思路—后序遍历：后序遍历二叉树，若当前结点的左右子树都符合搜索二叉树，就返回当前结点；
 * 若有左子树（右子树）不符合， * 则返回右子树根节点（左子树根节点）。需要设置全局变量来保存当前子树的最大值、最小值以及节点数。
 *
 * 注意搜索二叉树的特性
 */
public class BiggestSubBST {
    public static TreeNode biggestSubBST(TreeNode head) {
        // 记录节点数，当前子树的最大值，最小值
        int[] record = new int[3];
        return posOrder(head, record);
    }

    public static TreeNode posOrder(TreeNode head, int[] record) {
        if (head == null) {
            // 当遍历到叶子节点时
            record[0] = 0;
            record[1] = Integer.MAX_VALUE;
            record[2] = Integer.MIN_VALUE;
            return null;
        }
        int value = head.value;// 头节点的值
        TreeNode left = head.left;// 左子树的头节点
        TreeNode right = head.right;// 右子树的头节点
        TreeNode lBST = posOrder(left, record);
        int lSize = record[0];
        int lMin = record[1];
        int lMax = record[2];
        TreeNode rBST = posOrder(right, record);
        int rSize = record[0];
        int rMin = record[1];
        int rMax = record[2];
        // 保存当前子树中的最大值与最小值，不是只保存左子树的最大值与右子树的最小值
        // 因为在递归的过程中，不知道当前是左子树还是右子树
        record[1] = Math.min(lMin, value);
        record[2] = Math.max(rMax, value);
        // left == lBST && right == rBST 这个判断条件特别重要
        // 只有当左右两个子树的根节点是当前节点的左右子节点，当前节点才能作为根节点加入
        if (left == lBST && right == rBST && lMax < value && value < rMin) {
            record[0] = lSize + rSize + 1;
            return head;
        }
        record[0] = Math.max(lSize, rSize);
        // 若左右子树不能合并，返回当前最长的那个子树的根节点
        return lSize > rSize ? lBST : rBST;
    }

    public static void main(String[] args) {
        // 下面已经建立了一棵与示例图中相同的二叉树
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(1);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(10);
        root.right.left.left = new TreeNode(4);
        root.right.left.left.left = new TreeNode(2);
        root.right.left.left.right = new TreeNode(5);
        root.right.left.right = new TreeNode(14);
        root.right.left.right.left = new TreeNode(11);
        root.right.left.right.right = new TreeNode(15);
        root.right.right = new TreeNode(13);
        root.right.right.left = new TreeNode(20);
        root.right.right.right = new TreeNode(16);

        PrintBinaryTree.printTree(root);
        TreeNode sub = biggestSubBST(root);
        PrintBinaryTree.printTree(sub);
    }

}