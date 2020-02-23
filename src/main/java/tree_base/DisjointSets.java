package tree_base;

import list_base.Node;
import sun.awt.image.ImageWatched;
import sun.reflect.generics.tree.Tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/23 13:57
 * version: 1.0
 * description:并查集（Disjoint Set）
 * 并查集（Union-find Sets）是一种非常精巧而实用的数据结构，它主要用于处理一些不相交集合的合并问题。
 * 一些常见的用途有求连通子图、求最小生成树的 Kruskal 算法和求最近公共祖先（Least Common Ancestors, LCA）等
 *
 * Tarjan算法 与并查集解决二叉树节点间最近公共祖先的批量查询问题
 *如果二叉树的 节点数为N, 查询语句的条数为M,整个处理过程的时间复杂度 要求达到O(N+M)
 */
public class DisjointSets {
    public HashMap<TreeNode, TreeNode> fatherMap;
    public HashMap<TreeNode, Integer> rankMap;

    public DisjointSets() {
        fatherMap = new HashMap<>();
        rankMap = new HashMap<>();
    }

    public void makeSets(TreeNode head) { //初始化
        fatherMap.clear();
        rankMap.clear();
        preOrderMake(head);
    }

    public void preOrderMake(TreeNode head) {
        if (head == null) {
            return;
        }
        fatherMap.put(head, head);
        rankMap.put(head, 0);
        preOrderMake(head.left);
        preOrderMake(head.right);
    }

    public TreeNode findFather(TreeNode n) { //寻找祖先节点:根据一个节点查找所在集合代表节点的
        TreeNode father = fatherMap.get(n);
        if (father != n) {
            father = findFather(father);
        }
        fatherMap.put(n, father);
        return father;
    }

    public void union(TreeNode a, TreeNode b) { //根据某一个节点查找所在集合的代表元素一级如果做到路径压缩的过程。
        if (a == null || b == null) {
            return;
        }
        TreeNode aFather = findFather(a);
        TreeNode bFather = findFather(b);
        if (aFather != bFather) {
            int aFrank = rankMap.get(aFather);
            int bFrank = rankMap.get(bFather);
            if (aFrank < bFrank) {
                fatherMap.put(aFather, bFather); //rank不变
            } else if (aFrank > bFrank) {
                fatherMap.put(bFather, aFather);//rank不变
            } else {
                fatherMap.put(bFather, aFather);// 当rank相等，则这里就比较随意
                rankMap.put(aFather, aFrank + 1);
            }

        }
    }

    public TreeNode[] tarJanQuery(TreeNode head, Query[] queries) {
        TreeNode[] ans = new Tarjan().query(head, queries);
        return ans;
    }
    public static void main(String [] args){
        // 下面已经建立了一棵与示例图中相同的二叉树
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(1);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(0);
        TreeNode o3 = root.left.right = new TreeNode(3);
        TreeNode o6 = root.right.left = new TreeNode(10);
        root.right.left.left = new TreeNode(4);
        TreeNode o1 = root.right.left.left.left = new TreeNode(2);
        root.right.left.left.right = new TreeNode(5);
        TreeNode o5 = root.right.left.right = new TreeNode(14);
        root.right.left.right.left = new TreeNode(11);
        TreeNode o2 = root.right.left.right.right = new TreeNode(15);
        root.right.right = new TreeNode(13);
        TreeNode o4 = root.right.right.left = new TreeNode(20);
        root.right.right.right = new TreeNode(16);

        PrintBinaryTree.printTree(root);
        Query [] queries = new Query[3];
        queries[0]  =new Query(o1,o2);
        queries[1]  =new Query(o3,o4);
        queries[2]  =new Query(o5,o6);
        DisjointSets dis = new DisjointSets();
        dis.makeSets(root);
        TreeNode[] result = dis.tarJanQuery(root,queries);
        for(int i = 0;i<result.length;i++){
            System.out.println(result[i]);
        }
    }
}

//Tarjan 类实现处理流程
class Tarjan {
    private HashMap<TreeNode, LinkedList<TreeNode>> queryMap;
    private HashMap<TreeNode, LinkedList<Integer>> indexMap;
    private HashMap<TreeNode, TreeNode> ancestorMap;
    private DisjointSets sets;

    public Tarjan() {
        queryMap = new HashMap<TreeNode, LinkedList<TreeNode>>();
        indexMap = new HashMap<TreeNode, LinkedList<Integer>>();
        ancestorMap = new HashMap<TreeNode, TreeNode>();
        sets = new DisjointSets();
    }

    public TreeNode[] query(TreeNode head, Query[] ques) {
        TreeNode[] ans = new TreeNode[ques.length];
        setQueries(ques, ans);
        sets.makeSets(head);
        setAnswers(head, ans);
        return ans;
    }

    public void setQueries(Query[] ques, TreeNode[] ans) {
        TreeNode o1 = null;
        TreeNode o2 = null;
        for (int i = 0; i != ans.length; i++) {
            o1 = ques[i].o1;
            o2 = ques[i].o2;
            if (o1 == o2 || o1 == null || o2 == null) {
                ans[i] = o1 != null ? o1 : o2;
            } else {
                if (!queryMap.containsKey(o1)) {
                    queryMap.put(o1, new LinkedList<TreeNode>());
                    indexMap.put(o1, new LinkedList<Integer>());
                }
                if (!queryMap.containsKey(o2)) {
                    queryMap.put(o2, new LinkedList<TreeNode>());
                    indexMap.put(o2, new LinkedList<Integer>());
                }
                queryMap.get(o1).add(o2);
                indexMap.get(o1).add(i);
                queryMap.get(o2).add(o1);
                indexMap.get(o2).add(i);
            }
        }
    }

    public void setAnswers(TreeNode head, TreeNode[] ans) {
        if (head == null) {
            return;
        }
        setAnswers(head.left, ans);
        sets.union(head.left, head);
        ancestorMap.put(sets.findFather(head), head);
        setAnswers(head.right, ans);
        sets.union(head.right, head);
        ancestorMap.put(sets.findFather(head), head);
        LinkedList<TreeNode> nList = queryMap.get(head);
        LinkedList<Integer> iList = indexMap.get(head);
        TreeNode node = null;
        TreeNode nodeFather = null;
        int index = 0;
        while (nList != null && nList.isEmpty()) {
            node = nList.poll();
            index = iList.poll();
            nodeFather = sets.findFather(node);
            if (ancestorMap.containsKey(nodeFather)) {
                ans[index] = ancestorMap.get(nodeFather);
            }
        }
    }
}