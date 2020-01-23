package stack_base;

import java.util.Stack;

/**
 * Create with: intermediate
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/16 0:35
 * version: 1.0
 * description:矩阵大小为MN，要求时间复杂度为O（MN）。
 * 给定一个整型矩阵map，其中的值只有0和1两种，求其中全是1的矩形区域中，最大的一个有多少个1。
 * <p>
 * 例如：
 * 1 0 1 1
 * 1 1 1 1
 * 1 1 1 0
 * <p>
 * 返回6.
 * 解答：矩阵的行数为M，以每一行做切割，统计以当前行作为底的情况下，每个位置上的1的“高度”，并计算以当前行为底的最大矩阵的大小。
 * 例如，遍历到第三行时，每个位置1的高度为{3,2,3,0}：
 */
public class MaxRectangle {
    public static void main(String[] args) {
        int[][] map = {{1, 0, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 0}};
        //int[][] map = {{1}, {1}, {1}, {0}};  //单行测试

        System.out.println(getMaxSubMatrix(map));
    }

    public static int getMaxSubMatrix2(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
            }
            maxArea = Math.max(maxRecFromBottom(height), maxArea);
        }
        return maxArea;
    }

    public static int getMaxSubMatrix(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int maxArea = 0; //记录矩形区域的面积
        int[] height = new int[map[0].length]; //记录矩形的高度
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1;  //计算出每个矩形的高度
            }
            maxArea = Math.max(maxRecFromBottom(height), maxArea);
        }
        return maxArea;
    }
    public static int maxRecFromBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] > height[i]) {//如果在右边找到一个较小的数，那最大面积应该在左边。
                // 对于被弹出去的元素来说，就找到了它左 右两边第一个比它小的元素；
                int mid = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int area = height[mid] * (i - left - 1); //减去一个 1 是因为去除现在 较低的高度
                maxArea = Math.max(maxArea, area);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) { // 全部元素遍历完后，栈可能非空，需继续处理；
            // 此时的栈是什么样子呢？数组最后一个元素一定在栈顶，栈中元素的右边没有比它小的元素（如果有它就被弹出了）；从栈顶到栈低 降序。
            int mid = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            int area = height[mid] * (height.length - left - 1);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }


}
