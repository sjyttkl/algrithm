package dynamic_programming;

/**
 * Create with: dynamic_programming
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/23 23:48
 * version: 1.0
 * description:
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 * 注意；这里的n 是相同的；比如：请问用8个2x1的小矩形去无重复的覆盖一个2x8的大矩形。总共有多少种方法
 * 首先来看分析：https://blog.csdn.net/fjswcjswzy/article/details/107978585
 * 状态递推：
 * 第一步竖着放一个f(i-1)
 * 第一步横着放一个f(i-2)
 * f(i) = f(i-1) + f(i-2)
 * 初始化；
 * f(0) = 0
 * f(1) = 1
 * f(2) =2
 */
public class RectCover {
    public static void main(String[] args) {
        System.out.println(rectCover(3));
    }
    public static int rectCover(int number) {
        if (number <= 0) {
            return 0;
        }
        if (number == 1) {
            return 1;
        }
        if (number == 2) {
            return 2;
        }
        int res = 2;
        int pre = 1;
        int tmp = 0;
        for (int i = 3; i <= number; i++) {
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }
}

