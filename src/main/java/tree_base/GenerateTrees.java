package tree_base;


import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.List;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/23 16:40
 * version: 1.0
 * description: 统计和生成所有不同的二叉树
 * 给定一个整数N,若果N<1 代表空树结构，否则代表中序遍历的结果为{1，2,3,4.N}
 * 普通问题：统计可能组成的二叉树结构式多少？
 * 进阶问题：返回能组成二叉树结构的 头节点 和 可能能的结构
 */
public class GenerateTrees {

    public static void main(String args[]){
        System.out.println(numTrees(10));
        System.out.println(generateTrees(10));
    }
    //普通问题：计算能生成不同二叉树的个数,动态规划加速达到O(n2)
    public static int numTrees(int n) {
        if (n < 2) {
            return 1;
        }
        int[] num = new int[n + 1];
        num[0] = 1;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < i + 1; j++) {
                num[i] += num[j - 1] * num[i - j];
            }
        }
        return num[n];
    }

    //进阶
    public static List<TreeNode> generateTrees(int n) {
        return generate(1, n);
    }

    public static List<TreeNode> generate(int start, int end) {
        List<TreeNode> res = new LinkedList<TreeNode>();
        if (start > end) {
            res.add(null);
        }
        TreeNode head = null;
        for (int i = start; i < end + 1; i++) {
            head = new TreeNode(i);
            List<TreeNode> lSub = generate(start, i - 1);
            List<TreeNode> rSub = generate(i + 1, end);
            for (TreeNode l : lSub) {
                for (TreeNode r : rSub) {
                    head.left = l;
                    head.right = r;
                    res.add(cloneTree(head));
                }
            }
        }
        return res;
    }

    public static TreeNode cloneTree(TreeNode head) {
        if (head == null) {
            return null;
        }
        TreeNode res = new TreeNode(head.value);
        res.left = cloneTree(head.left);
        res.right = cloneTree(head.right);
        return res;
    }
}
