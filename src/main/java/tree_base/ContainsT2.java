package tree_base;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/15 0:31
 * version: 1.0
 * description:
 * 先序遍历树1，判断树1以每个节点为根的子树是否包含树2的拓扑结构。
 * 时间复杂度：O(M*N)
 * 注意区分判断总体包含关系、和判断子树是否包含树2的函数
 */
public class ContainsT2 {
    public static void main(String args[]) {
        //test
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        n1.left = n2;
        n1.right = n3;
        n3.left = n4;

        TreeNode n5 = new TreeNode(3);
        TreeNode n6 = new TreeNode(4);
        n5.left = n6;

        if (contains(n1, n5)) {
            System.out.print("contains");
        } else {
            System.out.print("not contains");
        }
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
