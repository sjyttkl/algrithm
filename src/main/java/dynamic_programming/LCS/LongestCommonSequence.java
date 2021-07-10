package dynamic_programming.LCS;

import java.util.Arrays;

/**
 * Create with: dynamic_programming
 * author: songdongdong
 * E-mail: songdongdong@weidian.com
 * date: 2021/7/10 14:56
 * version: 1.0
 * description:  最长公共子序列（longest common sequence）
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
public class LongestCommonSequence {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 5, 3, 6, 4, 8, 9, 7};
        lis1(arr);
//        System.out.println(Arrays.toString(getdp1(arr)));
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
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > len) {
                len = dp[i];
                index = i;
            }
        }
        int[] lis = new int[len];
        lis[--len] = arr[index];
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
}
