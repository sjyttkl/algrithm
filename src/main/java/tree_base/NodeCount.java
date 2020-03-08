package tree_base;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/23 16:43
 * version: 1.0
 * description:计算完全二叉树节点数。
 * 不使用遍历方法，优化时间复杂度到O(H^2)。
 * 高度为h的满二叉树节点数为2^h-1。
 * 设计递归函数search(Node root,int h,int H)，返回当前节点roo为根的完全二叉树的节点数。h代表该节点在的高度，高度从1计算，H代表原始二叉树的高度始终不变。
 * t判断当前节点的右子树的最左边的节点的高度。
 * -若和该树的高度一致，则说明左子树为满二叉树，节点数=由上式得到的左子树节点数+本节点数1+递归得到的右子树节点数。
 * 若高度不一致，则说明右子树为满二叉树，节点数=递归得到的左子树节点数+1+上式得到的右子树节点数。
 * 递归时间复杂度的估计：想实际情形，每层的节点只招一个作为根节点算子树节点数，找当前节点右子树的最左节点需要H，故时间复杂度为O(N).
 */
public class NodeCount {
    public static int nodeCnt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return search(root, 1, mostLeftNodeH(root, 1));
    }

    public static int search(TreeNode root, int h, int H) {
        if (h == H) {  // 说明已经到最后一层了，那么就是node就是叶节点
            return 1;
        }
        // node的右子树高度已经到底，说明node的左树是满二叉树
        // 因此该树的节点数 = 左边满二叉树(2^(h - level) - 1) + node节点 + node的右节点数
        if (mostLeftNodeH(root.right, h + 1) == H) {//
            return (1 << (H - h)) + search(root.right, h + 1, H);//
        } else {
            // 说明左子树比右子树高一层，那么node右树就是满二叉树
            // 因此该树的节点数为：
            // 右边满二叉树(2^(h - level - 1) - 1) + node节点 + node的左节点数
            return (1 << (H - h - 1)) + search(root.left, h + 1, H);
        }
    }
    /// 求node节点的左子树高度
    /// node代表当前节点
    /// level为node节点的高度
    public static int mostLeftNodeH(TreeNode root, int level) {//从原始root
        TreeNode TreeNode = root;
        while (TreeNode != null) {
            ++level;
            TreeNode = TreeNode.left;
        }
        return level - 1;//
    }

    public static void main(String args[]) {

        // 下面已经建立了一棵与示例图中相同的二叉树
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(1);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(13);
        PrintBinaryTree.printTree(root);
        System.out.println(nodeCnt(root));

    }
}
