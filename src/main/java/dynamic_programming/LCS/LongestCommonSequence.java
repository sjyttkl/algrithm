package dynamic_programming.LCS;

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

    }

    //时间复杂度为）O(N^2)
    public int[] getdp1(int[] arr) {
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
        return dp;
    }

}
