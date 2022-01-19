package dynamic_programming;

import java.util.Arrays;

/**
 * Create with: dynamic_programming
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/24 0:45
 * version: 1.0
 * description:
 * 给定一个三角形，从上到下求最小路径和。每一步你可以移动到下面行的相邻数字。
 * 例如，给定以下三角形
 * [
 * [2]，
 * [3,4]，
 * [6,5,7]，
 * [4,1,8,3]
 * ]]
 * 从顶部到底部的最小路径总和为11（即，2 + 3 + 5 + 1 = 11）。
 * 注意：
 * 奖励点，如果你能够使用只使用O（n）额外的空间，其中n是三角形中的总行数
 * 解题：
 * 方法一：
 * 求出从顶点到底部所有节点的路径，在选取最小的路径和.这里给的是下三角矩阵，
 * A[i][j] += Max{A[i-1][j-1],A[i-1][j]}，
 * 对应两个边界的情况：
 * A[i][j] += A[i-1][j]、
 * A[i][j] +=A[i-1][j-1]，这样从上向下，在求到最底部时候，找出最小的值。
 * <p>
 * 方法二：
 * 上面是自顶向下的，能否可以自底向上进行,，竟然也可以，这里还不要考虑两个边界的情况，当然上面的其实也可以不考虑的，可以认为是0，这里：triangle[i][j] +=min(triangle[i+1][j] , triangle[i+1][j+1])
 */
public class MinimumTotal {
    public static void main(String[] args) {
        int[][] triangle = {{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}};
        System.out.println(minimumTotal(triangle));
        int[][] triangle2 = {{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}};

        System.out.println(minimumTotal2(triangle2));
    }

    /**
     * @param triangle: a list of lists of integers.
     * @return: An integer, minimum path sum.
     */
//    自顶向下
    public static int minimumTotal(int[][] triangle) {
        // write your code here
        if (triangle.length == 1 && triangle[0].length == 1) {
            return triangle[0][0];
        }
        int minnum = Integer.MAX_VALUE;

        for (int i = 1; i < triangle.length; i++) {//第二行开始
            for (int j = 0; j < triangle[i].length; j++) {
                if (j == 0) {//左边界
                    triangle[i][j] += triangle[i - 1][j];
                } else if (j == triangle[i].length - 1) {//右边界
                    triangle[i][j] += triangle[i - 1][j - 1];
                } else {//中间
                    triangle[i][j] += Math.min(triangle[i - 1][j], triangle[i - 1][j - 1]);
                }
                if (i == triangle.length - 1)
                    minnum = Math.min(minnum, triangle[i][j]);
            }
        }
        return minnum;
    }

    /**
     * @param triangle: a list of lists of integers.
     * @return: An integer, minimum path sum.
     */
    //自底向上
    public static int minimumTotal2(int[][] triangle) {
        // write your code here
        if (triangle.length == 1 && triangle[0].length == 1)
            return triangle[0][0];
        int minnum = Integer.MAX_VALUE;
        for (int i = triangle.length - 2; i >= 0; i--) {
            for (int j = 0; j < triangle[i].length; j++) {
                triangle[i][j] += Math.min(triangle[i + 1][j], triangle[i + 1][j + 1]);
            }
        }
        minnum = triangle[0][0];
        return minnum;
    }
}
