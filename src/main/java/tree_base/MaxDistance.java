package tree_base;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/23 15:35
 * version: 1.0
 * description: 二叉树节点间的最大距离问题
 * https://www.iteye.com/blog/caoxyemail-2129866
 */
public class MaxDistance {
    public static  int maxDistanc(TreeNode head) {
        int[] record = new int[1];
        return posOrder(head, record);
    }
    public static  int posOrder(TreeNode head, int[] record) {
        if (head ==null){
            record[0] = 0;
            return 0;
        }
        int lMax = posOrder(head.left, record); //左孩子上的最大距离
        int maxfromleft = record[0];
        int rMax = posOrder(head.right, record); //右孩子上的最大距离
        int maxFromRight = record[0];
        int curNodeMax = maxfromleft + maxFromRight + 1; // 这里记录的 跨 h 节点的最大距离。因为又可以能树没有右子树，只有左子树，这个节点就不一定是最大的了
        record[0] = Math.max(maxfromleft, maxFromRight) + 1;  //记录当前节点的最大距离，左孩子的最大距离和右孩子的最大距离中 最大的那个，选择最大的一个作为 距离 h(父节点) 最远的距离返回
        return Math.max(Math.max(lMax, rMax), curNodeMax); //返回最大的一个
    }

    public static void main(String[] args) {
        // 下面已经建立了一棵与示例图中相同的二叉树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        PrintBinaryTree.printTree(root);
        System.out.println(maxDistanc(root));
    }
}

