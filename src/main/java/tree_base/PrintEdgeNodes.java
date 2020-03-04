package tree_base;

import sun.reflect.generics.tree.Tree;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/5 0:40
 * version: 1.0
 * description:打印二叉树的边界节点
 * 给定一棵二叉树的头节点head，
 * 按照两种标准分别实现二叉树边界节点的逆时针打印
 * 标准一
 *     头节点为边界节点
 *     叶节点为边界节点
 *    如果节点在其所在的层中是最左或者最右边，那么也是边界节点
 * 标准二 
 *   头节点作为边界节点
 *   叶节点位边界节点
 *   树左边界延伸下去的路径为边界节点
 *   树有边界延伸下去的路径为边界节点
 * ————————————————
 * 下面借鉴了左神讲述的内容进行模拟了一下，具体过程，左神推敲如下（我也是直接搬的左神的思路，注明）：
 * 标准一：
 * 1、得到二叉树每一层上最左边和最右边的节点
 * 2、从上到下打印所有层中的最左节点
 * 3、先序遍历二叉树，打印那些不属于某一层最左或最右的节点
 * 4、从上到下打印所有层中的最右节点
 * 标准二：
 * 1、从头节点开始往下寻找，只要找到第一个既有左孩子又有右孩子的节点，直接进入步骤2
 * ，记为h。在这个过程中，找过的节点直接打印。
 * 2、h的左子树先进入步骤3的打印过程；h的右子树再进入步骤4的打印过程，最后返回
 * 3、打印左边界的延伸路径以及h左子树上所有的叶节点
 * 4、打印右边界的延伸路径以及h右子树上所有的叶节点
 * 按照标准1打印：1，2，4，7，11，13，14，15，16，12，10，6，3
 * 按照标准2打印: 1，2，4，7，13，14，15，16，10，6，3
 * --------------------------------------------------------------------
 * <p>
 * 时间复杂度 O(n) 空间复杂度O(h)  h为树的高度，详细请看图片
 */
public class PrintEdgeNodes {
    //按照标准一，打印节点
    public static void printEdge1(TreeNode head) {
        if (head == null) {
            return;
        }
        int height = getHeight(head, 0);//获得树的高度
        TreeNode[][] edgeMap = new TreeNode[height][2];
        setEdgeMap(head, 0, edgeMap);
        //打印左边界节点
        for (int i = 0; i != edgeMap.length; i++) {
            System.out.print(edgeMap[i][0].value + " ");
        }
        //打印叶子节点
        printLeafNotInMap(head, 0, edgeMap);
        //打印右节点但不是左节点的

        for (int i = edgeMap.length - 1; i != -1; i--) {
            if (edgeMap[i][0] != edgeMap[i][1]) {//这是防止头节点被打印了两次
                System.out.print(edgeMap[i][1].value + " ");
            }
        }
        System.out.println();
    }

    public static int getHeight(TreeNode h, int l) {
        if (h == null) {
            return l;
        }
        return Math.max(getHeight(h.left, l + 1), getHeight(h.right, l + 1));
    }

    public static void setEdgeMap(TreeNode h, int l, TreeNode[][] edgeMap) {
        if (h == null) {
            return;
        }
        edgeMap[l][0] = edgeMap[l][0] == null ? h : edgeMap[l][0];
        edgeMap[l][1] = h;
        setEdgeMap(h.left, l + 1, edgeMap);
        setEdgeMap(h.right, l + 1, edgeMap);
    }

    public static void printLeafNotInMap(TreeNode h, int l, TreeNode[][] m) {
        if (h == null) {
            return;
        }
        if (h.left == null && h.right == null && h != m[l][0] && h != m[l][1]) {
            System.out.print(h.value + " ");
        }
        printLeafNotInMap(h.left, l + 1, m);
        printLeafNotInMap(h.right, l + 1, m);
    }
    //按照标准二打印节点
    public static void printEdge2(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        if (head.left != null && head.right != null) {
            printLeftEdge(head.left, true);//先打印左子树
            printRightEdge(head.right, true);//再打印右子树
        } else {
            printEdge2(head.left != null ? head.left : head.right);//对于根节点来讲，如果缺少左子树，或者右子树，则继续往下走
        }
        System.out.println();
    }

    public static void printLeftEdge(TreeNode h, boolean print) {//print表示是叶子节点
        if (h == null) {
            return;
        }
        if (print || (h.left == null && h.right == null)) {//不存在 子树，则应该是叶子节点
            System.out.print(h.value + " ");
        }
        printLeftEdge(h.left, print);//打印左子树，
        printLeftEdge(h.right, print && h.left == null ? true : false);//若要打印右子树，则需要判断是否存在左子树，如果左子树不为空为空，则不打印右子树
    }

    public static void printRightEdge(TreeNode h, boolean print) {
        if (h == null) {
            return;
        }
        printRightEdge(h.left, print && h.right == null ? true : false);//若存在右子树，说明下面所有的左子树都不能打印
        printRightEdge(h.right, print);
        if (print || (h.left == null && h.right == null)) {//不存在 子树，则应该是叶子节点
            System.out.print(h.value + " ");
        }
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.right = new TreeNode(4);
        head.right.left = new TreeNode(5);
        head.right.right = new TreeNode(6);
        head.left.right.left = new TreeNode(7);
        head.left.right.right = new TreeNode(8);
        head.right.left.left = new TreeNode(9);
        head.right.left.right = new TreeNode(10);
        head.left.right.right.right = new TreeNode(11);
        head.right.left.left.left = new TreeNode(12);
        head.left.right.right.right.left = new TreeNode(13);
        head.left.right.right.right.right = new TreeNode(14);
        head.right.left.left.left.left = new TreeNode(15);
        head.right.left.left.left.right = new TreeNode(16);

        printEdge1(head);
        printEdge2(head);

    }

}
