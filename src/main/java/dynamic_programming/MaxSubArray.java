package dynamic_programming;

/**
 * Create with: dynamic_programming
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/3/5 18:26
 * version: 1.0
 * description:给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * 1、定义两个变量
 *
 * tempSum：存储之前的累加和
 * maxSum:   存储当前的最大和。
 * 2、遍历数组，当第i个数时，主要更新两个变量就可以：
 *
 * 判断前面累加和是否为负值，如果为负值，则累加和更新为当前值；否则，继续累加当前值
 * 判断累加和是否大于最大和，如果大于最大和，则更新为累加和；否则，继续保留之前的最大和。

 *
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

}
