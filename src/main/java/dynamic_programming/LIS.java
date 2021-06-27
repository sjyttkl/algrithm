package dynamic_programming;

import java.util.Arrays;

/**
 * Create with: dynamic_programming
 * author: songdongdong
 * E-mail: songdongdong@weidian.com
 * date: 2021/6/27 11:50
 * version: 1.0
 * description:最长递增子序列
 * 给定一个长度为N的数组，找出一个最长的单调自增子序列（
 * 不一定连续，但是顺序不能乱）。例如：给定一个长度为6的数组A{5， 6， 7， 1， 2， 8}，则其最长的单调递增子序列为{5，6，7，8}，长度为4.
 */
public class LIS {
    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 3, 6, 4, 8, 9, 7};
        //时间复杂度 是 O(N^2)
        System.out.println(Arrays.toString(lis1(arr)));
        //时间复杂度 是 O(NlogN)
        System.out.println(Arrays.toString(lis2(arr)));
    }

    //第一种方式：时间复杂度 是 O(N^2)
    public static int[] lis1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] dp = getdp1(arr);
        return generateLIS(arr, dp);
    }

    //这里dp数组 存的是到当前位置的最大长度（每个位置的值需要依赖前一个值，并且需要是递增的序列）
    public static int[] getdp1(int[] arr) {
        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

        }
        return dp;
    }

    public static int[] generateLIS(int[] arr, int[] dp) {
        int len = 0;
        int index = 0;
        //取得最大长度递增子序列的对应位置
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > len) {
                len = dp[i];
                index = i;
            }
        }

        int[] lis = new int[len]; //创建最大递增子序列 长度的人数组
        lis[--len] = arr[index]; //最后位置上，设置该最大值
        for (int i = index; i >= 0; i--) {
            if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {
                lis[--len] = arr[i];
                index = i;
            }
        }
        return lis;
    }
    //第二种方式：时间复杂度O(NlogN)
    public static int[] lis2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] dp = getdp2(arr);
        return generateLIS(arr, dp);
    }

    //使用二分法 ，
    public static int[] getdp2(int[] arr) {
        int[] dp = new int[arr.length];
        int[] ends = new int[arr.length];
        ends[0] = arr[0];
        dp[0] = 1;
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        for (int i = 1; i < arr.length; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = (l + r) / 2;
                if (arr[i] > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right,l );
            ends[l] = arr[i];
            dp[i] = l + 1;
        }
        return dp;

    }


}
