package dynamic_programming;

/**
 * Create with: dynamic_programming
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/23 23:38
 * version: 1.0
 * description:
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * 先来看简单分析：
 * 状态地图：
 * 假设两步跳完
 * 第一步跳 1,2，。。。n 个台阶
 * 第二步 条 f(i-1),f(i-2),f(n-n)
 * f(i) = f(i-1) +f(i-2)+...f(0)
 * f(i-1) = f(i-2) +f(i-3)+...f(0)
 * f(i)=2*f(i-1); *
 * 初始化：f(1) =1
 * 返回结果 f(n)
 */
public class JumpFloorII {
    public static int jumpFloorII(int number) {
        if(number <=0){
            return 0;
        }
        //初始化
        int total = 1;
        for(int i=1;i<number;i++){
            //递推公式
            total = 2*total;
        }
        return total;
    }
}


