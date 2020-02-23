package stack_base;

import sun.reflect.generics.tree.Tree;
import tree_base.PrintBinaryTree;
import tree_base.TreeNode;

import java.util.*;

/**
 * Create with: stack_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/18 11:02
 * version: 1.0
 * description:构造数组的MaxTree
 * 一个数组的MaxTree定义：
 * <p>
 * 数组必须没有重复元素
 * MaxTree是一棵二叉树，数组的每一个值对应一个二叉树节点
 * 包括MaxTree树在内且在其中的每一棵子树上，值最大的节点都是树的头
 * 给定一个没有重复元素的数组arr，写出生成这个数组的MaxTree的函数，要求如果数组长度为N，则时间负责度为O(N)、额外空间负责度为O(N)。
 */
public class MaxTree {
    public static void popStackSetMap2(Stack<TreeNode> stack,HashMap<TreeNode,TreeNode> map){
        TreeNode curnode = stack.pop();
        if(stack.isEmpty()){
            map.put(curnode,null);
        }else
        {
            map.put(curnode,stack.peek());
        }
    }

    // 3,4,5,2,1
    public static TreeNode getMaxTree(int[] arr) {
        TreeNode[] nArr = new TreeNode[arr.length];
        for (int i = 0; i != arr.length; i++) {
            nArr[i] = new TreeNode(arr[i]);
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        HashMap<TreeNode, TreeNode> lBigMap = new HashMap<TreeNode, TreeNode>();//获取往左最大数
        HashMap<TreeNode, TreeNode> rBigMap = new HashMap<TreeNode, TreeNode>();//获取往右最大数
        for (int i = 0; i != arr.length; i++) {
            TreeNode curNode = nArr[i];
            while ((!stack.isEmpty()) && stack.peek().value < curNode.value) {
                popStackSetMap(stack, lBigMap);
            }
            stack.push(curNode);
        }
        while (!stack.isEmpty()) {
            popStackSetMap(stack, lBigMap); //剩下的数据：一定是降序 。例如 3 2 1 。所以 1 左边最大的数就是 2
        }
        for (int i = nArr.length - 1; i != -1; i--) { //获取右边最大数，需要从最右边开始
            TreeNode curNode = nArr[i];
            while ((!stack.isEmpty()) && stack.peek().value < curNode.value) {
                popStackSetMap(stack, rBigMap);//右边来了一个比左边还大的数，则这个左边最大的数据需要出栈
            }
            stack.push(curNode);
        }
        while (!stack.isEmpty()) {
            popStackSetMap(stack, lBigMap);//剩下的数据：一定是升序 。例如 123 。所以 1 右边最大的数就是 2
        }
        // 这需要获取每个数左边第一个最大值，和右边第一个最大值
        TreeNode head = null;
        for (int i = 0; i != nArr.length; i++) {
            TreeNode curNode = nArr[i];
            TreeNode left = lBigMap.get(curNode);//获取这个节点左边最大值
            TreeNode right = rBigMap.get(curNode); //获取这个节点右边最大值
            if (left == null && right == null) {
                head = curNode; //根节点
            } else if (left == null) { //如果左边不存在最大值
                if (right.left == null) { //右边根节点的左子树
                    right.left = curNode;
                } else {
                    right.right = curNode;
                }
            } else if (right == null) { //如果右边不存在最大值
                if (left.left == null) { //左边跟节点的左子树
                    left.left = curNode;
                } else {
                    left.right = curNode;
                }
            } else {
                TreeNode parent = left.value < right.value ? left : right; //根节点会以 左右两边最大值中，较小的那个为根节点
                if (parent.left == null) {
                    parent.left = curNode;//连接
                } else {
                    parent.right = curNode;
                }
            }
        }
        return head;
    }

    public static void popStackSetMap(Stack<TreeNode> stack, HashMap<TreeNode, TreeNode> map) {
        TreeNode popNode = stack.pop();
        if (stack.isEmpty()) {
            map.put(popNode, null);//当前数字，左边或者右边第一个最大的数据，如果没有则null
        } else {
            map.put(popNode, stack.peek());
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 1, 2};
        TreeNode head = getMaxTree(arr);
        PrintBinaryTree.printTree(head);


    }

}
