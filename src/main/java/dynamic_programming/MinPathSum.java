package dynamic_programming;

/**
 * Create with: dynamic_programming
 * author: songdongdong
 * E-mail: songdongdong@jd.com
 * date: 2020/3/8 19:24
 * version: 1.0
 * description:矩阵的最小路径和
 * 题目：
 * 给定一个矩阵m，从左上角开始每次只能向右或者向下走，最后达到右下角的位置，路径上所有的数字累加起来就是路径和，返回所有的路径中最小的路径和。
 * 举例：
 * <p>
 * 1 3 5 9
 * 8 1 3 4
 * 5 0 6 1
 * 8 8 4 0
 * <p>
 * 路径1，3，1，0，6，1，0是所有路径中路径和最小的，所以返回12。
 */
public class MinPathSum {

    //时间复杂度O（M*N) 空间复杂度O(M*N)
    public static int getMinPathSum1(int nums[][]) {
        if (nums == null || nums.length == 0 || nums[0] == null || nums[0].length == 0) {
            return 0;
        }
        int row = nums.length;
        int col = nums[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = nums[0][0];
        for (int i = 1; i < row; i++) {//第一列
            dp[i][0] = dp[i - 1][0] + nums[i][0];
        }
        for (int j = 1; j < col; j++) { //第一行
            dp[0][j] = dp[0][j - 1] + nums[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + nums[i][j];
            }
        }
        return dp[row - 1][col - 1];

    }

    /**
     * 借助于m*1或者1*n的辅助空间,这里利用了压缩空间的方式
     * 时间复杂度：O(M*N) ,空间复杂度O(min{M,N})
     *
     * @return
     */
    public static int getMinPathSum2(int nums[][]) {
        if (nums == null || nums.length == 0 || nums[0] == null || nums[0].length == 0) {
            return 0;
        }
        int more = Math.max(nums.length, nums[0].length); //得到行与列的较大值为more
        int less = Math.min(nums.length, nums[0].length); //得到行与列的较小值为less
        boolean rowmore = more == nums.length; //行数是不是大于列数
        int arr[] = new int[less]; //辅助空间仅为行或者列的最小值
        arr[0] = nums[0][0];
        for (int i = 1; i < less; i++) {
            arr[i] = arr[i - 1] + (rowmore ? nums[0][i] : nums[i][0]);
        }
        for (int i = 1; i < more; i++) {
            arr[0] = arr[0] + (rowmore ? nums[i][0] : nums[0][i]);
            for (int j = 1; j < less; j++) {
                arr[j] = Math.min(arr[j - 1], arr[j]) + (rowmore ? nums[i][j] : nums[j][i]);
            }
        }
        return arr[less - 1];
    }

}
