package tree_base;

import sun.reflect.generics.tree.Tree;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/16 15:11
 * version: 1.0
 * description:二叉树中两个节点的最近公共祖先
 * 1: 递归版本
 * 思想：假设根结点为root，其中给定的两个结点分别为A和B，它们分别都不为null。
 * 如果当前结点p为null，那么直接返回null，如果当前结点p是给定的结点中的其中一个结点，
 * 那么直接返回当前结点p(如果p是根结点，程序一次就返回了，下面的递归也不会出现)。
 * 如果当前节点不是A和B中的一个，那么需要分别去查找p的左右子树，看看是否包含A或者B，查询左右子树后，如果查询左子树和查询右子树的结果都不为null，
 * 说明当前结点p就是最近的公共祖先。否则，如果查询左子树结果为null，那么返回查询右子树的结果。反之，返回查询左子树的结果。
 * <p>
 * 2.进阶：如果查询两个节点的最近公共祖先的操作十分频繁、想办法让单条查询的查询时间减少
 * <p>
 * 3 再进阶：给定二叉树的头节点head,同时给定所有想要进行的查询，二叉树的节点数量为N；查询条数为M,请在时间复杂度为O(N+M)，内返回所有查询的结果
 */
public class GetParent {

    //递归版本
    public static TreeNode getParent(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null || root == node1 || root == node2) {
            return root;
        }

        TreeNode left = getParent(root.left, node1, node2);
        TreeNode right = getParent(root.right, node1, node2);
        //如果左右子树都能找到，那么当前节点就是最近的公共祖先节点
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;//如果左子树上没有，那么返回右子树的查找结果 否则返回左子树的查找结果
    }

    public static void main(String args[]) {
        TreeNode node = new TreeNode(6);
        TreeNode o1 = null;
        TreeNode o2 = null;
        node.left = new TreeNode(1);
        node.right = new TreeNode(12);
        node.left.left = new TreeNode(0);
        node.left.right = new TreeNode(3);
        node.right.left = new TreeNode(10);
        node.right.right = new TreeNode(13);
        node.right.left.left = new TreeNode(4);
        node.right.left.right = new TreeNode(14);
        node.right.right.left = new TreeNode(20);
        node.right.right.right = new TreeNode(16);
        o1 = node.right.left.left.left = new TreeNode(2);
        o2 = node.right.left.left.right = new TreeNode(5);
        node.right.left.right.left = new TreeNode(11);
        node.right.left.right.right = new TreeNode(15);
        PrintBinaryTree.printTree(node);
        System.out.println(getParent(node, o1, o2).value);

        Record1 record1 = new Record1(node);
        System.out.println(record1.query(o1, o2).value);

        Record2 record2 = new Record2(node);
        System.out.println(record2.query(o1, o2).value);
//        System.out.println(getBSTTopologySize2(TreeNode));
    }
}

// 非递归版本
//进阶：如果查询两个节点的最近公共祖先的操作十分频繁、想办法让单条查询的查询时间减少
//结构一：建立二叉树中每个节点对应的父节点信息，是一张哈希表
class Record1 {
    private HashMap<TreeNode, TreeNode> map;

    public Record1(TreeNode head) {
        map = new HashMap<>();
        if (head != null) {
            map.put(head, null);
        }
        setMap(head);
    }

    private void setMap(TreeNode head) {
        if (head == null) {
            return;
        }
        if (head.left != null) {
            map.put(head.left, head);
        }
        if (head.right != null) {
            map.put(head.right, head);
        }
        setMap(head.left);
        setMap(head.right);
    }

    public TreeNode query(TreeNode o1, TreeNode o2) {
        HashSet<TreeNode> path = new HashSet<TreeNode>();
        while (map.containsKey(o1)) {
            path.add(o1);
            o1 = map.get(o1);
        }
        while (!path.contains(o2)) {//o2不在 父路径中，则继续往上找
            o2 = map.get(o2);
        }
        return o2;
    }
}

/*
结构二： 直接建立任意两个节点之间的最近公共祖先记录，便于以后查询时直接查。
1, 对二叉数中每颗子树都进行步骤2
2. 假设子树的头节点为h, h所有的后代节点 和 h节点的最近公共祖先都是h,记录下来
     h左子树 的每个节点 和 h 右子树 的每个节点的最近公共祖先都是 h记录下来。
    为了不重复保存，设计一个好的结构式实现的重点。
    时间复杂度O(n2) ,空间负责O(n2)   单词查询复杂度O(1)
 */
class Record2 {

    private HashMap<TreeNode, HashMap<TreeNode, TreeNode>> map;

    public Record2(TreeNode head) {
        map = new HashMap<TreeNode, HashMap<TreeNode, TreeNode>>();// 记录两个节点的公共祖先
        initMap(head);
        setMap(head);
    }

    public void initMap(TreeNode head) {//初始化  Map
        if (head == null) {
            return;
        }
        map.put(head, new HashMap<TreeNode, TreeNode>());
        initMap(head.left);
        initMap(head.right);
    }

    private void setMap(TreeNode head) {
        if (head == null) {
            return;
        }
        headRecord(head.left, head);//记录每个节点，和 顶点节点的公共祖先，为父类
        headRecord(head.right, head);//记录每个节点，和 顶点节点的公共祖先，为父类
        subRecord(head);//记录head下面  的公共祖先 为 head
        setMap(head.left);
        setMap(head.right);
    }

    private void headRecord(TreeNode n, TreeNode h) {
        if (n == null) {
            return;
        }
        map.get(n).put(h, h);
        headRecord(n.left, h);
        headRecord(n.right, h);
    }

    private void subRecord(TreeNode head) {
        if (head == null) {
            return;
        }
        preLeft(head.left, head.right, head);
        subRecord(head.left);
        subRecord(head.right);
    }

    private void preLeft(TreeNode l, TreeNode r, TreeNode h) {
        if (l == null) {
            return;
        }
        preRight(l, r, h);//

        preLeft(l.left, r, h);
        preLeft(l.right, r, h);
    }

    private void preRight(TreeNode l, TreeNode r, TreeNode h) {
        if (r == null) {
            return;
        }
        map.get(l).put(r, h);
        preRight(l, r.left, h);
        preRight(l, r.right, h);
    }

    public TreeNode query(TreeNode o1, TreeNode o2) {
        if (o1 == o2) {
            return o1;
        }
        if (map.containsKey(o1)) {
            return map.get(o1).get(o2);
        }
        if (map.containsKey(o2)) {
            return map.get(o2).get(o1);
        }
        return null;
    }


}
