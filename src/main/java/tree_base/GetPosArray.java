package tree_base;

import java.util.HashMap;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/23 16:39
 * version: 1.0
 * description:通过先序和中序数组生成后续数组
 * 题目：已知一颗二叉树所有的节点值都不同，给定这颗树正确的先序和中序数组，不要重建整颗树，而是通过这两个数组直接生成正确的后续数组
 */
public class GetPosArray {
    //生成后序数组
    public static int[] GetPosArray(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }
        int len = pre.length;
        int[] pos = new int[len];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < len; i++) {
            map.put(in[i], i);
        }
        setPos(pre, 0, len - 1, in, 0, len - 1, pos, len - 1, map);
        return pos;
    }

    // 从右往左依次填好后序数组s
    // si为后序数组s该填的位置
    // 返回值为s该填的下一个位置
    public static int setPos(int[] p, int pi, int pj, int[] n, int ni, int nj,
                             int[] s, int si, HashMap<Integer, Integer> map) {
        if (pi > pj) {
            return si;
        }
        s[si--] = p[pi];
        int i = map.get(p[pi]);
        //递归调用
        si = setPos(p, pj - nj + i + 1, pj, n, i + 1, nj, s, si, map);
        return setPos(p, pi + 1, pi + i - ni, n, ni, i - 1, s, si, map);
    }

    //打印数组内容
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 5, 3, 6, 7};
        int[] in = {4, 2, 5, 1, 6, 3, 7};
        int[] pos = GetPosArray(pre, in);
        printArray(pos);
    }

}
