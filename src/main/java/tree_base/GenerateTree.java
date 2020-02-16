package tree_base;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/16 1:14
 * version: 1.0
 * description:给定一个有序数组arr，已知其中没有重复值，用这个有序数组生成一棵平衡搜索二叉树，并且该搜索二叉树中序遍历的结果与arr一致
 *
 * 平衡二叉树：平衡二叉树也叫自平衡二叉搜索树（Self-Balancing Binary Search Tree），
 * 所以其本质也是一颗二叉搜索树，不过为了限制左右子树的高度差，避免出现倾斜树等偏向于线性结构演化的情况，
 * 所以对二叉搜索树中每个节点的左右子树作了限制，左右子树的高度差称之为平衡因子，树中每个节点的平衡因子绝对值不大于 ，
 * 此时二叉搜索树称之为平衡二叉树。
 *
 */
public class GenerateTree {
    //通过有序数组生成平衡搜索二叉树
    public static TreeNode generateTree(int[] arr){
        if(arr==null){
            return null;
        }
        return generate(arr,0,arr.length-1);
    }

    private static TreeNode generate(int[] arr, int start, int end) {
        if(start>end){
            return null;
        }
        //有序数组最中间的数生成搜索二叉树的头结点
        //该数左边的数生成左子树
        //该数右边的数生成右子树
        int mid=(start+end)/2;
        TreeNode head=new TreeNode(arr[mid]);
        head.left=generate(arr,0,mid-1);
        head.right=generate(arr,mid+1,end);
        return head;

    }
}
