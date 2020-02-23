package tree_base;

import java.util.HashMap;
import java.util.Map;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/23 16:31
 * version: 1.0
 * description:
 */
public class ReConstructBinaryTree {

    //先序和中序去建立二叉树
        public static TreeNode preInToTree(int[] pre, int[] in) {
            if (pre == null || in == null) {
                return null;
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < in.length; i++) {
                map.put(in[i], i);
            }
            return preIn(pre, 0, pre.length - 1, in, 0, in.length - 1, map);
        }

        public static TreeNode preIn(int[] pre, int pi, int pj, int[] in, int ni, int nj, Map<Integer, Integer> map) {
            if (pi > pj) {
                return null;
            }
            TreeNode head = new TreeNode(pre[pi]);
            int index = map.get(pre[pi]);
            head.left = preIn(pre, pi + 1, pi + index - ni, in, ni, index - 1, map);
            head.right = preIn(pre, pi + index - ni + 1, pj, in, index + 1, nj, map);
            return head;
        }

        //后续和中序去建立二叉树
        public static TreeNode posInToTree(int[] pos, int[] in) {
            if (pos == null || in == null) {
                return null;
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < in.length; i++) {
                map.put(in[i], i);
            }
            return posIn(in, 0, in.length - 1, pos, 0, pos.length - 1, map);
        }

        public static TreeNode posIn(int[] in, int ni, int nj, int[] pos, int si, int sj, Map<Integer, Integer> map) {
            if (si > sj) {
                return null;
            }
            TreeNode head = new TreeNode(pos[sj]);
            int index = map.get(pos[sj]);
            head.left = posIn(in, ni, index - 1, pos, si, si + index - ni - 1, map);
            head.right = posIn(in, index + 1, nj, pos, si + index - ni, sj - 1, map);
            return head;
        }

    //后续和先序去建立二叉树
        public static TreeNode prePosToTree(int[] pre, int[] pos) {
            if (pre == null || pos == null) {
                return null;
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < pos.length; i++) {
                map.put(pos[i], i);
            }
            return prePos(pre, 0, pre.length - 1, pos, 0, pos.length - 1, map);
        }

        public static TreeNode prePos(int[] pre, int pi, int pj, int[] pos, int si, int sj, Map<Integer, Integer> map) {

            TreeNode head = new TreeNode(pos[sj--]);
            if (pi == pj) {
                return head;
            }
            int index = map.get(pre[++pi]);
            head.left = prePos(pre, pi, pi + index - si, pos, si, index, map);
            head.right = prePos(pre, pi + index - si + 1, pj, pos, index + 1, sj, map);
            return head;
        }
    }



