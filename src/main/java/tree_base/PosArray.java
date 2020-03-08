package tree_base;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/15 23:04
 * version: 1.0
 * description:根据后序数组重建搜索二叉树
 * 给定一个整型数组arr，已知其中没有重复值，判断arr是否可能是节点值类型为整型的搜索二叉树后序遍历的结果。
 * 进阶：
 * 如果整型数组arr中没有重复值，且已知是一棵搜索二叉树的后 序遍历结果，通过数组arr重构二叉树。
 * <p>
 * 题目一：
 * 已知给定一个无重复元素的数组，判断数组是否是二叉树后序遍历的结果。
 * 1、已知后序遍历的顺序为左右中，则数组的最后一位元素为二叉树的头结点，且比该节点小的为左子树，比该节点大的为右子树；
 * 2、找到比最后一位小的最右节点，并找到比最后一位大的最左节点；如果最右节点的下一个节点为最后节点，则没有右子树，
 * 或者最左节点为开始节点，则没有左子树；这两种情况直接进行下一步，即最后位置向前移一位；
 * 3、如果最右节点加1不等于最左节点，直接返回false；
 * 4、最终通过递归，判断左子树和右子树是否分别为二叉搜索树。
 * <p>
 * 进阶版：
 * 要重构二叉树，只需要找到后序遍历中的左右子树分界点，并递归构建整颗树。
 */
public class PosArray {
    public static boolean isPosArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        return isPost(arr, 0, arr.length - 1);
    }

    public static boolean isPost(int[] arr, int start, int end) {
        if (start == end) {
            return true;
        }
        int lessRight = -1;
        int moreLeft = end;
        for (int i = start; i < end; i++) {
            if (arr[i] < arr[end]) {//小于最大值的最右节点。
                lessRight = i;
            } else {//大于等于最大值的 最左节点
                moreLeft = moreLeft == end ? i : moreLeft;
            }
        }
        if (lessRight == -1 || moreLeft == end) { // lessRight 表示没有左子树，  moreLeft == end 没有右子树
            return isPost(arr, start, end - 1);//尝试看看，是否存在 组成 搜索二叉树的情况，end-1 后尝试搜索下。
        }
        if (lessRight != moreLeft - 1) {//小于最大值的最右节点  的下一个节点为： 大于最大值的最左节点，则符合要求，否则不符合要求
            return false;
        }
        return isPost(arr, start, lessRight) && isPost(arr, moreLeft, end - 1);//左右子树，各自递归计算
    }

    //进阶方式：如果整型数组arr中没有重复值，且已知是一棵搜索二叉树的后 序遍历结果，通过数组arr重构二叉树。
    //  要重构二叉树，只需要找到后序遍历中的左右子树分界点，并递归构建整颗树。
    public static TreeNode posArrayToBST(int[] arr) {
        if (arr == null|| arr.length ==0) {
            return null;
        }
        return posToArray(arr, 0, arr.length - 1);
    }
    public static TreeNode posToArray(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode head = new TreeNode(arr[end]);
        int lessRight = -1;
        int moreLeft = end;
        for (int i = start; i < end; i++) {
            if (arr[i] < arr[end]) {
                lessRight = i;
            } else {
                moreLeft = moreLeft == end ? i : moreLeft;
            }
        }
        head.left = posToArray(arr, start, lessRight);
        head.right = posToArray(arr, moreLeft, end - 1);
        return head;
    }

    public static void main(String args[]) {
        int arr[] = {1, 4, 3, 6, 8, 7, 5};
        int arr1[] = {2, 1, 3, 6, 5, 7, 4};
        System.out.println(isPosArray(arr));
        PrintBinaryTree.printTree(posArrayToBST(arr1));


    }

}
