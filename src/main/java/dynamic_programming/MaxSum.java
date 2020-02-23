package dynamic_programming;

import java.util.ArrayList;

/**
 * Create with: dynamic_programming
 * author: songdongdong
 * E-mail: songdongdong@jd.com
 * date: 2020/2/23 23:55
 * version: 1.0
 * description:
 * 对于一个有正有负的整数数组，请找出总和最大的连续数列。
 * 给定一个int数组A和数组大小n，请返回最大的连续数列的和。保证n的大小小于等于3000。
 *
 * 测试样例：
 * [1,2,3,-6,1]
 * 返回：6
 * 分析：
 *
 * {6,-3,7,-15,1,22}
 * 状态：f(i) 以 array[i]结尾的最大公共子序列
 * 递推：
 * 前一个元素构成的子序列：{6}
 * 前两个元素构成的子序列{6,-3} {-3}
 * 前三个元素构成的子序列{6，-3,-2} {-3，-2}{-2}
 * f(i) = f(i-1) + array[i]
 * f(i ) = max(f(i-1)+array[i],array[i])
 * 初始化；
 * f(0) = array[0]
 * 返回结果max(f(i),reslt)
 */
public class MaxSum {

    public int getMaxSum(ArrayList<Integer> list,int n){
        if(list.isEmpty()){
            return 0;
        }
        int [] f = new int[n];
        //初始化
        f[0] = list.get(0);
        for(int i=1;i<list.size();i++){
            //状态地推
            f[i] = Math.max(f[i-1]+list.get(i),f[i-1]);
        }
        //输出结果
        int result = f[0];
        for(int i=1;i<f.length;i++){
            result = Math.max(result,f[i]);
        }
        return result;

    }
}
