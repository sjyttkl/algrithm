package dynamic_programming;

/**
 * Create with: dynamic_programming
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/23 23:29
 * version: 1.0
 * description:
 * <p>
 * 状态：f(i) 表示第 i 个斐波那契数
 * 初始化：f(1) =1 ,f(2) = 1;
 * 状态递推： f(i) = f(i-1) + f(i-2)
 * 返回结果 f(n)
 */
public class Fibonacci {

    public static void main(String[] args) {
        System.out.println(fibonacci3(20));
    }

    //普通递归求解,时间复杂度O(n2),内存会溢出
    public static int fibonacci(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    //时间复杂度O(n)
    public static int fibonacci2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        int res = 1;
        int pre = 1;
        int tmp = 0;
        for (int i = 3; i <= n; i++) {
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }

    //利用矩阵乘法，能达到时间负责度O(logn)
    public static int fibonacci3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] base = {{1, 1,}, {1, 0}};//状态矩阵，相当于转移方程
        int[][] res = matrixPower(base, n - 2);//这里减去2 ，是因为前两个已经返回了。
        return res[0][0] + res[1][0]; //最终的结果就是两个数字相加
    }


    //求矩阵 m 的 p 次方
    public static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        //先把res设为单位矩阵，相当于整数中的 1
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;//对角线上全是 1
        }
        int[][] tmp = m;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {//p连续右移，只有当2的次方上是1时才需要累乘，当2次方不是1的时候，则 直接把对应的结果相乘就行了。
                res = muliMatrix(res, tmp);
            }
            tmp = muliMatrix(tmp, tmp);//p连续右移，只有当2的次方上是0时 只需要把模板进行累乘就行了。
        }
        return res;
    }

    public static int[][] muliMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

}