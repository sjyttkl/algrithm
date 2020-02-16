package tree_base;

import sun.reflect.generics.tree.Tree;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/16 1:24
 * version: 1.0
 * description: 二叉树中找一个节点的后继节点
 * 第一种方式：中序遍历，然后直接查询某个节点后的一个节点（后继节点），时间复杂度O(n), 空间复杂度O(n)
 * 第二种方式：最优解法：如果node节点和 其后继节点的实际距离只有L,那么时间负责度O(L),空间复杂度O(1)
 */
public class SuccessorNode {
    public static TreeNode getSuccessorNode(TreeNode node) {
        if (node == null)
            return node;
        if (node.right != null)//当存在右子树，那当前节点后继节点，一定是右子树最左边的节点。
            return getLeftMost(node.right);
        else {
            TreeNode parent = node.parent;
            //当不存在右子树的时，那要看当前节点是否在当前节点的父节点的左孩子上。
            // 如果是则，当前父节点为后继节点，如果是右孩子，就向上继续寻找后继节点，例如：找到当前节点s , p = s.parent 。若 s为p的左孩子，则p为 node的后继节点
            while (parent != null && parent.left != node) {
                node = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }

    public static TreeNode getLeftMost(TreeNode node) {
        if (node == null)
            return null;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

}
