package dynamic_programming;

import static dynamic_programming.Fibonacci.matrixPower;

/**
 * Create with: dynamic_programming
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/3/8 17:53
 * version: 1.0
 * description:生小牛:
 * 题目：成熟的母牛每年生1头小母牛，并且所有牛永远不会死。
 * 第一年有一只成熟的母牛，从第二年开始母牛开始生小母牛。
 * 每只小母牛三年后成熟又可以生小母牛。给定整数N，求N年后牛的数量。
 * <p>
 * 分析:
 * 第N-1年的牛会毫无损失的活到第N年。
 * 第N-3年出生的牛，到第N年恰好成为成熟的牛，这以后出生的牛在第N年都没有生育能力。
 * 这意味着在第N年，成熟的牛的数量就是第N-3年牛的数量，就是这一年生产小牛的数量。则有： c(n) = c(n-1) + c(n-3)
 * 其中,  c(1) = 1
 * c(2) = 2
 * c(3) = 3
 */
public class Cow {
    public static void main(String[] args) {
        System.out.println(c1(5));
    }

    //时间复杂度O(2^n)
    public static int c1(int n) {
        if (n < 1) {
            return n;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }

        return c1(n - 1) + c1(n - 3);
    }

    //时间负责度O(n)
    public int c2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int res = 3;
        int pre = 2;
        int prepre = 1;
        int tmp1 = 0;
        int tmp2 = 0;
        for (int i = 4; i <= n; i++) {
            tmp1 = res;
            tmp2 = pre;
            res = res + prepre;
            pre = tmp1;
            prepre = tmp2;
        }
        return res;
    }

    //时间复杂度O（logn)
    public int c3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int[][] base = {{1, 1, 0}, {0, 0, 1}, {1, 0, 0}};
        int[][] res = matrixPower(base, n - 3);
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
    }
}
