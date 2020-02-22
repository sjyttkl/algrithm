package stack_base;

import java.util.LinkedList;

/**
 * Create with: intermediate
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/16 1:08
 * version: 1.0
 * description:  最大值减去最小值    结果要  小与或等于num的子数组的数量
 * 数组长度N    时间复杂度O(N)
 */
public class MaxMinusMin {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
        int n = 5;// sc.nextInt();
        int num = 2;// sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        System.out.println(getNum(arr, num));
    }

    public static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        LinkedList<Integer> qmin = new LinkedList<Integer>();
        int L = 0, R = 0;
        int cnt = 0;
        while (L < arr.length) {//这里虽然是两个循环，但是却是左右滑动，所以时间复杂度是O(N)
            while (R < arr.length) {
                // 生成最大值更新结构
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                    qmax.pollLast();//剔除这个元素
                }
                qmax.addLast(R);
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[R]) {
                    qmin.pollLast();
                }
                qmin.addLast(R);
                if (arr[qmax.peekFirst()] - arr[qmin.peekFirst()] > num) {
                    break;//一旦找到右边 最大值，则R不动，继续滑动左边的值
                }
                R++;
            }//while
            cnt += R - L; //记录这次 子数组的长度。
            L++;//左边窗口向左走一步
            if (qmax.peekFirst() < L) {//防止最大值位置 超过 左边窗口超过  右边窗口
                qmax.pollFirst();
            }
            if (qmin.peekFirst() < L) {//防止最小值位置 超过 左边窗口超过  右边窗口
                qmin.peekFirst();
            }
        }
        return cnt;
    }
}
