package tree_base;

import sun.reflect.generics.tree.Tree;

import java.util.Stack;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.co
 * date: 2020/1/30 3:49
 * version: 1.0
 * description:分别用递归和非递归的方式实现二叉树先序、中序和后序遍历
 * 分析：
 * 先序：访问根节点、先序遍历左子树、先序遍历右子树
 * 中序：中序遍历左子树，访问根节点，中序遍历右子树
 * 后序：后序遍历左子树，后序遍历右子树，访问根节点
 * 因此，递归实现就很简单
 */
public class TreeOrder {
    //先序遍历
    public void preOrder(TreeNode root) {
        if (root == null)
            return;
        System.out.println(root.value + "访问根节点");
        preOrder(root.left);
        preOrder(root.right);
    }

    //中序遍历
    public void innerOrder(TreeNode root) {
        if (root == null)
            return;
        innerOrder(root.left);
        System.out.println(root.value + "访问根节点");
        innerOrder(root.right);
    }

    //后序遍历t
    public void posOrder(TreeNode root) {
        if (root == null)
            return;
        posOrder(root.left);
        posOrder(root.right);
        System.out.println(root.value + "访问根节点");
    }

    // 用递归实现的都可以使用栈实现
//  先看先序遍历
//1、申请一个栈stack，将头结点压入stack中
//2、从stack中弹出头结点，记为cur，打印cur的值，再将cur的右孩子压入栈中，再将cur的左孩子压入栈中
//3、不断重复2，直到stack为空
    public void preUncur(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.add(root);
            while (!stack.isEmpty()) {
                root = stack.pop();
                if (root.right != null)
                    stack.add(root.right);
                if (root.left != null)
                    stack.add(root.left);
            }
        }
    }

    //    中序遍历
//1、申请一个栈stack
//2、先把头结点压入栈中，再把他的左边界压入栈中
//3、重复2
    public void innerUncer(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.push(root);
            if (!stack.isEmpty() && root != null) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    System.out.println(root.value + "");
                    root = root.right;
                }
            }
        }
    }

    //    后序遍历
//    1、申请两个栈，将头结点压入栈stack中弹出stack中的顶点压入stack1中，
//    2、若头结点有孩子节点，则压入stack中，直到无左右孩子，此时
//    3、弹出stack栈中的顶点压入stack1中，重复2
    public void posUncer(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            Stack<TreeNode> stack1 = new Stack<TreeNode>();
            stack.push(root);
            if (!stack.isEmpty()) {
                stack1.push(stack.pop());
                if (root.left != null) {
                    stack.push(root.left);
                }
                if (root.right != null)
                    stack.push(root.right);
            }
            while (!stack1.isEmpty()) {
                System.out.println(stack1.pop().value + "");
            }
        }
    }
}
