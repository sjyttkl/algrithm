package tree_base;

import sun.reflect.generics.tree.Tree;

import java.util.HashMap;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/12 12:26
 * version: 1.0
 * description:给定一棵二叉树的头结点head，求二叉树中符合搜索二叉树的最大拓扑结构(节点最多）的大小,返回节点数
 */
public class BSTTopologySize {
    //方法1，时间复杂度O(n^2)
    public static int getBSTTopologySize1(TreeNode h) {
        if (h == null) {
            return 0;
        }
        return preOrder(h);
    }

    //先序遍历
    private static int preOrder(TreeNode h) {
        // TODO Auto-generated method stub
        if (h == null) return 0;
        int max = getMaxSizeFromHead(h, h);//跟节点最大
        max = Math.max(preOrder(h.left), max);//左子树最多的节点
        max = Math.max(preOrder(h.right), max);//右子树最多的节点
        return max;
    }

    //求以h作为头节点的最大拓扑结构
    private static int getMaxSizeFromHead(TreeNode h, TreeNode cur) {
        // TODO Auto-generated method stub
        if (h != null && cur != null && isBSTNode(h, cur, cur.value)) {
            return 1 + getMaxSizeFromHead(h, cur.left) + getMaxSizeFromHead(h, cur.right);//计算节点
        }
        return 0;
    }

    //判断一个节点是否在以h为头结点的搜索二叉树上,是否满足搜索二叉树。
    private static boolean isBSTNode(TreeNode h, TreeNode n, int value) {
        if (h == null) {
            return false;
        }
        if (h == n) {
            return true;
        }
        return isBSTNode(h.value > value ? h.left : h.right, n, value);
    }
//==============第二种方式

    /**
     * 拓扑贡献记录 ：假设目前节点是cur， 头部节点是head，拓扑贡献记录是一对键值对（val，val），
     * 左边val代表cur节点的左子树中有多少个存在于以head为头节点的搜索二叉树上，右边val代表cur节点的右子树中有多少个存在于以head为头结点的搜索二叉树上。
     */
    private static class Record {
        int l;//该节点左子树贡献的节点个数
        int r;//该节点右子树贡献的节点个数

        public Record(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    //方法2,时间复杂度O(n),最差O(nlogn)
    public static int getBSTTopologySize2(TreeNode h) {
        // TODO Auto-generated method stub
        HashMap<TreeNode, Record> recordMap = new HashMap<TreeNode, Record>();
        return posOrder(h, recordMap);
    }

    //采用后续遍历,求出每个节点作为头节点时的最大拓扑结构
    private static int posOrder(TreeNode h, HashMap<TreeNode, Record> recordMap) {
        // TODO Auto-generated method stub
        if (h == null) return 0;
        int leftMax = posOrder(h.left, recordMap);
        int rightMax = posOrder(h.right, recordMap);
        modifyRecordMap(h.left, h.value, recordMap, true);//左子树
        modifyRecordMap(h.right, h.value, recordMap, false); //右子树
        int l = 0;
        int r = 0;
        if (h.left != null && recordMap.containsKey(h.left)) {
            Record left = recordMap.get(h.left);
            l = left.l + left.r + 1;//左子树贡献值
        }
        if (h.right != null && recordMap.containsKey(h.right)) {
            Record right = recordMap.get(h.right);
            r = right.l + right.r + 1;//右子树贡献值
        }
        recordMap.put(h, new Record(l, r));//当前节点的贡献值
        return Math.max(l + r + 1, Math.max(leftMax, rightMax));//判断当前节点贡献值，和左右子树那个大。
    }

    /**
     * @param cur       子树节点（当前节点）
     * @param h         父节点
     * @param recordMap 对当前节点的贡献值进行修改
     * @param b         true表示左子树，false代表右子树
     * @return
     */
    private static int modifyRecordMap(TreeNode cur, int h, HashMap<TreeNode, Record> recordMap, boolean b) {
        // TODO Auto-generated method stub
        if (cur == null || !recordMap.containsKey(cur)) {//当前节点不存在或者 recorMap没有记录（叶子节点）
            return 0;
        }
        int minus = 0;
        if ((b && cur.value > h) || (!b && cur.value < h)) {//不符合搜索二叉树的结构，左子树比父节点小，右子树比父节点大。
            Record remove = recordMap.remove(cur);//直接就不符合搜索树了，所以把整个节点删除了
            return remove.l + remove.r + 1;//节点不符合需要删除的贡献值，全部删除

        } else {//符合搜索二叉树的结构，左子树比父节点小，右子树比父节点大。
            minus = modifyRecordMap(b ? cur.right : cur.left, h, recordMap, b);//这里只需检查左子树的右边节点是否小于 父节点即可。判断是否符合搜索二叉树

            Record curRecord = recordMap.get(cur);
            if (b) {//右子树不符合
                curRecord.r -= minus;
            } else {//左子树不符合
                curRecord.l -= minus;
            }
            recordMap.put(cur, curRecord);
            return minus;
        }
    }

    public static void main(String[] args) {
        TreeNode TreeNode = new TreeNode(6);
        TreeNode.left = new TreeNode(1);
        TreeNode.right = new TreeNode(12);
        TreeNode.left.left = new TreeNode(0);
        TreeNode.left.right = new TreeNode(3);
        TreeNode.right.left = new TreeNode(10);
        TreeNode.right.right = new TreeNode(13);
        TreeNode.right.left.left = new TreeNode(4);
        TreeNode.right.left.right = new TreeNode(14);
        TreeNode.right.right.left = new TreeNode(20);
        TreeNode.right.right.right = new TreeNode(16);
        TreeNode.right.left.left.left = new TreeNode(2);
        TreeNode.right.left.left.right = new TreeNode(5);
        TreeNode.right.left.right.left = new TreeNode(11);
        TreeNode.right.left.right.right = new TreeNode(15);
        System.out.println(getBSTTopologySize1(TreeNode));
        System.out.println(getBSTTopologySize2(TreeNode));

    }
}