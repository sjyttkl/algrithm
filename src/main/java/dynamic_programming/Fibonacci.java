package dynamic_programming;

/**
 * Create with: dynamic_programming
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/23 23:29
 * version: 1.0
 * description:
 *
 * 状态：f(i) 表示第 i 个斐波那契数
 * 初始化：f(1) =1 ,f(2) = 1;
 * 状态递推： f(i) = f(i-1) + f(i-2)
 * 返回结果 f(n)
 */
public class Fibonacci {

    public static void main(String[] args) {
        System.out.println(fibonacci(5));
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
}
