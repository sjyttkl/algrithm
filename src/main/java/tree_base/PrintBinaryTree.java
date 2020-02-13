package tree_base;

import sun.reflect.generics.tree.Tree;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/5 1:07
 * version: 1.0
 * description:如何较为直观的打印一个二叉树
 * ^  表示左上方距离最近的是父节点；v 表示距离左下方最近的值是跟节点
 * 我们给右结点加上了"V",给左结点加上了“^”,给头结点加上了“H”
 */
public class PrintBinaryTree {
    public static void printTree(TreeNode head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    /**
     * 规定打印时这个字符占用的长度一致,缺多少位就补上多少位的空格
     *
     * @param num
     * @return
     */
    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    /**
     * 打印树数据的核心
     *
     * @param head   头结点
     * @param height 树的高度
     * @param to     字符表示 H代表头 v代表右结点，^代表左结点
     * @param len    长度
     */
    public static void printInOrder(TreeNode head, int height, String to, int len) {
        //保证结点空时退出递归
        if (head == null) {
            return;
        }
        //先递归遍历右结点，找到右结点就输出加上符号V和固定空格的字符
        printInOrder(head.right, height + 1, "v", len);
        //获得该结点对应的字符，VnumV,表示右结点
        String val = to + head.value + to;
        //计算需要补多少位的空格
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        //输出补位空格的字符
        System.out.println( getSpace(height * len) +val);//这里需要输出 根据不同的高度进行打印相应的空格长度。
        //递归遍历左结点，如果不为空则打印字符 ^num^
        printInOrder(head.left, height + 1, "^", len);
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(-222222222);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(Integer.MIN_VALUE);
        head.right.left = new TreeNode(55555555);
        head.right.right = new TreeNode(66);
        head.left.left.right = new TreeNode(777);
        printTree(head);

        head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.right.left = new TreeNode(5);
        head.right.right = new TreeNode(6);
        head.left.left.right = new TreeNode(7);
        printTree(head);

        head = new TreeNode(1);
        head.left = new TreeNode(1);
        head.right = new TreeNode(1);
        head.left.left = new TreeNode(1);
        head.right.left = new TreeNode(1);
        head.right.right = new TreeNode(1);
        head.left.left.right = new TreeNode(1);
        printTree(head);


    }
}