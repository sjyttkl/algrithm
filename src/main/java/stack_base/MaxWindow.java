package stack_base;

import com.sun.scenario.effect.impl.state.LinearConvolveKernel;
import sun.awt.image.ImageWatched;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Create with: intermediate
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/15 19:56
 * version: 1.0
 * description:生成窗口最大值数组
 *有一个整型数组arr和一个大小为w的窗口从数组的最左边滑动到最右边，一共可产生n-w+1个窗口的最大值，请返回这个最大值数组。
 *
 * 要求：O（N）实现。
 * 维护最大值可以用堆，但堆怎么在窗口移动过程中移除掉被窗口划过的值？
 * 这里介绍了一种简单但性质强大的结构：有序栈（自己起的名字）。即将一系列值依次入栈，但在入栈过程中要始终保持栈的有序性，不合格的元素要弹出来（过程很像插入排序）。
 https://www.jianshu.com/p/c6b26f3a97b6
 */
public class MaxWindow {
    public static void main(String[] args) {
        int [] arr = new int[]{4,2,1,4};
        System.out.println(Arrays.toString(getMaxWindow(arr,2)));
    }
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || w > arr.length) {
            return null;
        }
        int[] result = new int[arr.length - w + 1];
        LinkedList<Integer> queue = new LinkedList<Integer> ();//有序栈

        for (int i = 0; i < arr.length; i++) {
            while (!queue.isEmpty() && arr[queue.peekLast()] < arr[i]) {//保持最大
                queue.pollLast();
            }
            queue.addLast(i);
            if (queue.peekFirst() <= i - w) { //判断是否超过窗口了。如果第一个位置超过了窗口，则剔除队列
                queue.pollFirst();
            }
            if (i >= w - 1) {//主要是防止刚开始，窗口不完成的情况。
                result[i - w + 1] = arr[queue.peekFirst()];//当前窗口最大值。
            }
        }
        return result;
    }
}
