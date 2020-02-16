package tree_base;

import sun.reflect.generics.tree.Tree;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/15 0:54
 * version: 1.0
 * description: 判断t1树中是否有与t2树拓扑结构完全相同的子树 时间复杂度：O（M+N)
 */
public class IsContainSubTree {

    public static boolean isSubTree(TreeNode t1, TreeNode t2) {
        String t1Str = serialByPre(t1);//先序遍历
        String t2Str = serialByPre(t2);
        return getIndexOf(t1Str, t2Str) != -1;
    }

    public static String serialByPre(TreeNode head) {
        if (head == null) {
            return "#!";
        }
        String res = head.value + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    // KMP
    public static int  getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si = 0;
        int mi = 0;
        int[] next = getNextArray(ms);//传入子串
        while (si < ss.length && mi < ms.length) {
            if (ss[si] == ms[mi]) {
                si++;
                mi++;
            } else if (next[mi] == -1) {
                si++;
            } else {
                mi = next[mi];
            }
        }
        return mi == ms.length ? si - mi : -1;
    }

    public static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;
        while (pos < next.length) {
            if (ms[pos - 1] == ms[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }


        return next;
    }
    public static void  main(String[] args)
    {
        TreeNode h1=new TreeNode(1);
        h1.left=new TreeNode(2);
        h1.right=new TreeNode(3);
        h1.left.left=new TreeNode(4);
        h1.left.right=new TreeNode(5);
        h1.right.left=new TreeNode(6);
        h1.right.right=new TreeNode(7);
        h1.left.left.right=new TreeNode(8);
        h1.left.right.left=new TreeNode(9);

        TreeNode h2=new TreeNode(2);
        h2.left=new TreeNode(4);
        h2.right=new TreeNode(5);
        h2.left.right=new TreeNode(8);
        //h2.right.left=new TreeNode(9);


        System.out.println(isSubTree(h1,h2));


    }
}
