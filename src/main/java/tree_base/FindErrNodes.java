package tree_base;

import java.util.Stack;

import static tree_base.PrintBinaryTree.printInOrder;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/15 0:38
 * version: 1.0
 * description:
 *
 * 一棵二叉树原本是搜索二叉树，但是其中有两个节点调换了位置，使得这棵二叉树不再是搜索二叉树，请找到这两个错误节点并返回。
 * 已知二叉树中所有节点的 值都不一样，给定二叉树的头节点 head，返回一个长度为 2 的二叉树节点类型 的数组 errs，errs[0]表示一个错误节点，errs[1]表示另一个错误节点。
  *
 * 如果在原问题中得到了这两个错误节点，我们当然可以通过交换两个节点的节点 值的方式让整棵二叉树重新成为搜索二叉树。
 * 但现在要求你不能这么做，而是在 结构上完全交换两个节点的位置，请实现调整的函数。

 */
public class FindErrNodes {

    //找出二叉树中两个错误的节点
    public static TreeNode[] getTwoErrNodes(TreeNode head){
        TreeNode[] errs = new TreeNode[2];
        if(head == null){
            return errs;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode pre = null;
        while(!stack.isEmpty() || head != null){
            if(head != null){
                stack.push(head);
                head = head.left;
            }else{
                head = stack.pop();
                if(pre != null && pre.value > head.value){
                    errs[0] = errs[0] == null ? pre : errs[0];
                    errs[1] = head;
                }
                pre = head;
                head = head.right;
            }
        }
        return errs;
    }

    //找出错误节点的父类
    public static TreeNode[] getTwoErrParents(TreeNode head, TreeNode e1, TreeNode e2){
        TreeNode[] parents = new TreeNode[2];
        if(head == null){
            return parents;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(!stack.isEmpty() || head != null){
            if(head != null){
                stack.push(head);
                head = head.left;
            }else{
                head = stack.pop();
                if(head.left == e1 || head.right == e1){
                    parents[0] = head;
                }
                if(head.left == e2 || head.right == e2){
                    parents[1] = head;
                }
                head = head.right;
            }
        }
        return parents;
    }

    //恢复二叉树--需要考虑的很多
    public static TreeNode recoverTree(TreeNode head){
        TreeNode[] errs = getTwoErrNodes(head);
        TreeNode[] parents = getTwoErrParents(head, errs[0], errs[1]);
        TreeNode e1 = errs[0];
        TreeNode e1P = parents[0];
        TreeNode e1L = e1.left;
        TreeNode e1R = e1.right;
        TreeNode e2 = errs[1];
        TreeNode e2P = parents[1];
        TreeNode e2L = e2.left;
        TreeNode e2R = e2.right;
        if(e1 == head){ //e1 为头的情况
            if(e1 == e2P){
                e1.left = e2L;
                e1.right = e2R;
                e2.right = e1;
                e2.left = e1L;
            }else if(e2P.left == e2){
                e2P.left = e1;
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2L;
                e1.right = e2R;
            }else{
                e2P.right = e1;
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2L;
                e1.right = e2R;
            }
            head = e2;
        }else if(e2 == head){  //e2为头的情况
            if(e2 == e1P){
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2;
                e1.right = e2R;
            }else if(e1P.left == e1){
                e1P.left = e2;
                e1.left = e2L;
                e1.right = e2R;
                e2.left = e1L;
                e2.right = e1R;
            }else{
                e1P.right = e2;
                e1.left = e2L;
                e1.right = e2R;
                e2.left = e1L;
                e2.right = e1R;
            }
            head = e1;
        }else{  //  e1 、e2都不为根节点、
            if(e1 == e2P){ //e1 为e2 的父节点的情况
                if(e1P.left == e1){
                    e1P.left = e2;
                    e1.left = e2L;
                    e1.right = e2R;
                    e2.left = e1L;
                    e2.right = e1;
                }else{
                    e1P.right = e2;
                    e1.left = e2L;
                    e1.right = e2R;
                    e2.left = e1L;
                    e2.right = e1;
                }
            }else if(e2 == e1P){  // e2 为e1 父节点的情况
                if(e2P.left == e2){
                    e2P.left = e1;
                    e2.left = e1L;
                    e2.right = e1R;
                    e1.left = e2;
                    e1.right = e2R;
                }else{
                    e2P.right = e1;
                    e2.left = e1L;
                    e2.right = e1R;
                    e1.left = e2;
                    e1.right = e2R;
                }
            }else{
                if(e1P.left == e1){ //e1 和e2 既不是相互的父节点，也不是耕街道
                    if(e2P.left == e2){
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.left = e2;
                        e2P.left = e1;
                    }else{
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.left = e2;
                        e2P.right = e1;
                    }
                }else{
                    if(e2P.left == e2){
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.right = e2;
                        e2P.left = e1;
                    }else{
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.right = e2;
                        e2P.right = e1;
                    }
                }
            }
        }
        return head;
    }

    public static void printTree(TreeNode head){
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

}
