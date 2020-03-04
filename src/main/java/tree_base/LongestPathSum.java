package tree_base;

import java.util.HashMap;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/12 10:07
 * version: 1.0
 * description:在二叉树中找到累加和为指定值的最长路径长度
 * 路径是指只能从某个节点往下：每次最多选择一个节点或者不选所形成的的节点链
 * 额外空间复杂度为 O(h)，h 为二叉树的高度。
 */
public class LongestPathSum {

     public static int getMaxLength(TreeNode head, int sum) {
        HashMap<Integer, Integer> sumMap = new HashMap<Integer, Integer>();
        sumMap.put(0, 0); // important,
        return preOrder(head, sum, 0, 1, 0, sumMap);
    }
    public static int preOrder(TreeNode head, int sum, int preSum, int level,
                               int maxLen, HashMap<Integer, Integer> sumMap) {
        if (head == null) {
            return maxLen;
        }
        int curSum = preSum + head.value;
        if (!sumMap.containsKey(curSum)) {
            sumMap.put(curSum, level);
        }
        if (sumMap.containsKey(curSum - sum)) {
            maxLen = Math.max(level - sumMap.get(curSum - sum), maxLen);
        }
        maxLen = preOrder(head.left, sum, curSum, level + 1, maxLen, sumMap);
        maxLen = preOrder(head.right, sum, curSum, level + 1, maxLen, sumMap);
        if (level == sumMap.get(curSum)) { //这里注意：这说明这是在遍历的时候加上去的，需要剔除下。需要注意的是，在遍历完二叉树的子树要返回到cur的父节点是，需要将map中该节点的记录删去（如果之前插入的话），否则可能出现路径不是自顶向下的情况。
            sumMap.remove(curSum);
        }
        return maxLen;
    }
    public static void main(String[] args) {
        TreeNode head = new TreeNode(-3);
        head.left = new TreeNode(3);
        head.right = new TreeNode(-9);
        head.left.left = new TreeNode(1);
        head.left.right = new TreeNode(0);
        head.left.right.left = new TreeNode(1);
        head.left.right.right = new TreeNode(6);
        head.right.left = new TreeNode(2);
        head.right.right = new TreeNode(1);
        PrintBinaryTree.printTree(head);
        System.out.println(getMaxLength(head, 6));
        System.out.println(getMaxLength(head, -9));

    }
}
