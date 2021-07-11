package dynamic_programming.LCS;

import java.util.Arrays;

/**
 * Create with: dynamic_programming
 * author: songdongdong
 * E-mail: songdongdong@weidian.com
 * date: 2021/7/10 14:56
 * version: 1.0
 * description:  最长递增子序列（longestOrdersequence）
 * 即一个给定的序列的子序列，就是将给定序列中零个或多个元素去掉之后得到的结果
 * 注意区分字序列和字串的关系：https://blog.csdn.net/hrn1216/article/details/51534607
 * <p>
 * 给定数组arr，返回arr的最长递增子序列（表示要保持原来串中的顺序）。
 * <p>
 * arr=[2,1,5,3,6,4,8,9,7] 返回的最长递增子序列为[1,3,4,8,9]
 * <p>
 * 要求：
 * 如果arr的长度为N，请实现 时间复杂度为O(NlogN)的方法
 */
public class LongestOrderSequence {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 5, 3, 6, 4, 8, 9, 7};
        System.out.println("案例:" + Arrays.toString(arr));
        lis1(arr);
        System.out.println(" -- generateLIS -- ");
        lis2(arr);
    }

    //时间复杂度为）O(N^2)
    public static int[] getdp1(int[] arr) {
        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;//以当前位置结尾的字串长度默认 为1
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    //dp存放的是到当前 位置 最大长度（每个位置的人值需要依赖上一个值，因为长度是递增的）
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        System.out.println("getdp1: " + Arrays.toString(dp));
        return dp;
    }

    public static int[] generateLIS(int[] arr, int[] dp) {
        int len = 0;
        int index = 0;
        //这里是获得最大递增序列的位置，和index
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > len) {
                len = dp[i];
                index = i;
            }
        }
        int[] lis = new int[len];//这里是存储
        lis[--len] = arr[index];//这里是存储有序子序列
        for (int i = index; i >= 0; i--) {
            if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {
                lis[--len] = arr[i];
                index = i;
            }
        }
        System.out.println("generateLIS: " + Arrays.toString(lis));

        return lis;
    }

    public static int[] lis1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] dp = getdp1(arr);
        return generateLIS(arr, dp);
    }

    //时间复杂度：O(NlogN)，这里使用二分法来优化的
    public static int[] getdp2(int[] arr) {
        int[] dp = new int[arr.length];
        int[] ends = new int[arr.length]; //保存最长递增子序列，记录记录有效序列，减少循环次数。比如：ends[3]=7 表示长度为4的递增序列中，最小结尾数变成了7
        ends[0] = arr[0];
        dp[0] = 1;
        int right = 0; //right 为ends 有效的index 区域 ，其中0-right 为有效区，right->length为无效区域
        int l = 0;
        int r = 0;
        int m = 0;
        System.out.println("ends: "+Arrays.toString(ends));
        System.out.println("dp:   "+Arrays.toString(dp));
        for (int i = 1; i < arr.length; i++) {
            l = 0;
            r = right;
            while (l <= r) { //使用二分法找到最大那个数
                m = (l + r) / 2;
                if (arr[i] > ends[m]) {//表示：有效区 小于当前值，则递增序列可以扩展m+1, l 就是有效长度
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            //这里的l 找到 比arr[i]的位置
            right = Math.max(right, l);//表示需要扩展或者收缩的空间，
            ends[l] = arr[i];
            System.out.println("ends: "+Arrays.toString(ends));
            dp[i] = l + 1;
            System.out.println("dp:   "+Arrays.toString(dp));

        }
        System.out.println("getdp2: " + Arrays.toString(dp));
        return dp;
    }

    public static int[] lis2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] dp = getdp2(arr);
        return generateLIS(arr, dp);
    }
}
