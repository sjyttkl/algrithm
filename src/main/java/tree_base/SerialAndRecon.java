package tree_base;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/11 0:29
 * version: 1.0
 * description:
 */

import sun.awt.image.ImageWatched;
import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化
 * 解决思路：节点和节点之间使用! 来分隔，空节点使用#来表示，为什么需要使用#来分隔？
 * 假如所有的节点都是相同的值，且不使用分隔符号，结构不同也会序列化为相同的结果
 */
public class SerialAndRecon {

    /**
     * 返回以head为根节点的二叉树的先序序列化后的字符串
     *
     * @param head
     * @return
     */
    public static String serialByPre(TreeNode head) {
        if (head == null) {
            return "#!";
        }
        String res = head.value + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    public static TreeNode reconByPreString(String preStr) {
        String[] values = preStr.split("!");
        Queue<String> queue = new LinkedList<String>();
        for (int i = 0; i != values.length; i++) {
            queue.offer(values[i]);
        }
        return reconPreOrder(queue);
    }

    public static TreeNode reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(value));
        node.left = reconPreOrder(queue);
        node.right = reconPreOrder(queue);
        return node;
    }
    //-===========================层次遍历===================================
    public static String serialByLevel(TreeNode head) {
        if (head == null) {
            return "#!";
        }
        String res = head.value + "!";
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            if (head.left != null) {
                res += head.left.value + "!";
                queue.offer(head.left);
            } else {
                res += "#!";
            }
            if (head.right != null) {
                res += head.right.value + "!";
                queue.offer(head.right);
            } else {
                res += "#!";
            }
        }
        return res;
    }
    public static TreeNode reconByLevelString(String levelStr) {
        String[] values = levelStr.split("!");
        int index = 0;
        TreeNode head = generateNodeByString(values[index++]);
        Queue<TreeNode> queue = new LinkedList();
        if (head != null) {
            queue.offer(head);
        }
        TreeNode node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNodeByString(values[index++]);
            node.right = generateNodeByString(values[index++]);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return head;

    }

    //生成节点
    public static TreeNode generateNodeByString(String val) {
        if (val.equals("#")) {
            return null;
        }
        return new TreeNode(Integer.valueOf(val));
    }

    public static void main(String args[]) {
        TreeNode head = null;
        head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.right.right = new TreeNode(5);


        PrintBinaryTree.printTree(head);
        String pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);

        System.out.print("reconstruct tree by pre-order, ");
        PrintBinaryTree.printTree(head);

    }
}
