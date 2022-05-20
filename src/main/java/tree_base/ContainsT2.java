package tree_base;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/15 0:31
 * version: 1.0
 * description:   给定彼此独立的两颗树头结点分别是t1和t2，判断t1树是否包含t2树全部的拓扑结构
 * 先序遍历树1，判断树1以每个节点为根的子树是否包含树2的拓扑结构。
 * 时间复杂度：O(M*N)
 * 注意区分判断总体包含关系、和判断子树是否包含树2的函数
 */
public class ContainsT2 {
    public static void main(String args[]) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.left.left = new TreeNode(4);
        node.left.left.left = new TreeNode(8);
        node.left.left.right = new TreeNode(9);
        node.left.right = new TreeNode(5);
        node.left.right.left = new TreeNode(10);
        node.right = new TreeNode(3);
        node.right.left = new TreeNode(6);
        node.right.right = new TreeNode(7);

        TreeNode node1 = new TreeNode(2);
        node1.left = new TreeNode(4);
        node1.left.left = new TreeNode(8);
        node1.right = new TreeNode(5);
        boolean contains = contains(node, node1);
        System.out.println(contains);
    }

    public static boolean contains(TreeNode t1, TreeNode t2) {
        return check(t1, t2) || contains(t1.left, t2) || contains(t1.right, t2);
    }

    public static boolean check(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return true;
        }
        if (t1 == null || t1.value != t2.value) {
            return false;
        }
        return check(t1.left, t2.left) && check(t1.right, t2.right);
    }

}
