package dynamic_programming;

import static dynamic_programming.Fibonacci.matrixPower;

/**
 * Create with: dynamic_programming
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/3/8 17:44
 * version: 1.0
 * description:
 * <p>
 * 如果台阶只有1级，方法只有一种，如果台阶只有2级，方法有2种；
 * 如果台阶只有N级，最后跳上第N级的情况，要么是从N-2台阶直接跨2级台阶，要么是从N-1级台阶跨1 级台阶
 */
public class Jump {
    public int f1(int n) {
        if (n < 1) {
            return n;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        return f1(n - 1) + f1(n - 2);
    }

    public int f2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int res = 2;
        int pre = 1;
        int tmp = 0;
        for (int i = 3; i <= n; i++) {
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }

    public int f3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];

    }
}
