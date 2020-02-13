package tree_base;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/11 0:57
 * version: 1.0
 * description: 二叉树神级遍历方式
 * <p>
 * 给定一棵二叉树的头节点head,完成二叉的先序丶中序丶和后序遍历，如果二叉树的节点为N，要求时间复杂度为O(N),空间复杂度为O（1);
 * 实际使用递归函数来完成遍历都是使用了栈函数，空间复杂度为O(h),h为二叉树的高度。那么我们应该怎么来解决空间复杂度问题呢？
 * 答案就是使用Morris遍历。Morris实质上就是避免使用栈结构，而是下层到上层，下层的空闲指针指向上层的某个节点，从而完成了下层到上层的移动
 */
public class TreeOrderGod {
    //中序遍历中序遍历二叉树，空间复杂度O(1)
    public static void morrisIn(TreeNode head) {
        if (head == null) {
            return;//如果头节点为空退出
        }
        TreeNode cur1 = head;//记录头节点
        TreeNode cur2 = null;   //用来比较判断
        while (cur1 != null) {
            cur2 = cur1.left;//找到左孩子，记录起来
            if (cur2 != null) {//如果左孩子有数据
                while (cur2.right != null && cur2.right != cur1) {//判断左孩子的右孩子不为空或指向的不是自己
                    cur2 = cur2.right; //这里就找到了头结点的左孩子的最右孩子
                }
                if (cur2.right == null) {//如果右孩子的数据为空，证明已经是最右了
                    cur2.right = cur1;//将这个最右的孩子的指针指回头节点
                    cur1 = cur1.left;//然后头节点向左孩子移动，这样是为了先找出最左的叶子节点
                    continue; //退出继续进入步骤1
                } else {
                    cur2.right = null; //将叶子节点的右节点置空, 其实即使把 增加的指针删除
                }
            }
            System.out.print(cur1.value + " ");//如果不符合以上条件证明已经找到要输出的节点，先输出
            cur1 = cur1.right;
        }
        System.out.println();
    }

    //先序遍历
    public static void morrisPre(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur1 = head;
        TreeNode cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    System.out.print(cur1.value + " ");
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            } else {
                System.out.print(cur1.value + " ");
            }
            cur1 = cur1.right;
        }
        System.out.println();
    }

    //后续遍历
    public static void morrisPos(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur1 = head;
        TreeNode cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                    printEdge(cur1.left);
                }
            }
            cur1 = cur1.right;
        }
        printEdge(head);
        System.out.println();
    }

    public static void printEdge(TreeNode head) {
        TreeNode tail = reverseEdge(head);
        TreeNode cur = tail;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    public static TreeNode reverseEdge(TreeNode from) {
        TreeNode pre = null;
        TreeNode next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(4);
        head.left = new TreeNode(2);
        head.right = new TreeNode(6);
        head.left.left = new TreeNode(1);
        head.left.right = new TreeNode(3);
        head.right.left = new TreeNode(5);
        head.right.right = new TreeNode(7);
        PrintBinaryTree.printTree(head);
        morrisIn(head);
        morrisPre(head);
        morrisPos(head);
        PrintBinaryTree.printTree(head);

    }

}

