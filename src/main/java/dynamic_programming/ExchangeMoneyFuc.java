package dynamic_programming;

/**
 * Create with: dynamic_programming
 * author: songdongdong
 * E-mail: songdongdong@weidian.com
 * date: 2021/2/3 21:25
 * version: 1.0
 * description: 换钱的方法数 参考：https://blog.csdn.net/qq_34342154/article/details/77122125  、 https://blog.csdn.net/junjunba2689/article/details/78823907
 * <p>
 * 给定数组arr，所有元素都为正数且不重复。每个值代表一种面值的货币，每种面值的货币可以使用任意张，再给定一个整数aim代表要找的钱数，求换钱有多少种方法。
 * <p>
 * 例如
 * arr = [5, 10, 25, 1], aim = 0，返回1
 * arr = [5, 10, 25, 1], aim = 15，返回6   方法数：3*5,5+10,10*1+5,15*1,2*5+5*1,10+5*1
 * arr = [3, 5]，aim = 2，返回0
 * <p>
 * 这道题的经典之处在于它可以体现暴力递归、记忆搜索、动态规划之间的关系，并可以在动态规划的基础上再进行一次优化。
 * <p>
 * 首先介绍暴力递归的方法。如果arr = [5, 10, 25, 1]，aim = 1000，分析过程如下：
 * <p>
 * 用０张５元的货币，让[10, 25, 1]组成剩下的1000，最终方法数记为res1。
 * 用１张５元的货币，让[10, 25, 1]组成剩下的995，最终方法数记为res2。
 * 用２张５元的货币，让[10, 25, 1]组成剩下的990，最终方法数记为res3。
 * ……
 * 用201张５元的货币，让[10, 25, 1]组成剩下的0，最终方法数记为res201。
 * 那么res1 + res2 + res3 + …… +res201的值就是中的方法数。根据如上的分析过程定义递归函数process1(arr, index, aim)它的含义是如果用arr[index..N-1]这些面值的钱组成aim，返回总的方法数。最坏情况下时间复杂度为O(aim^N)，N表示数组的长度。
 */
public class ExchangeMoneyFuc {
    public static void main(String[] args) {
        System.out.println(coins1(new int[]{5, 10, 25, 1}, 15));//6
        System.out.println(coins1(new int[]{3, 5}, 2));//0

        //动态规划方式计算，非最优
        System.out.println(coins2(new int[]{5,10,25,1},15));//6
        System.out.println(coins2(new int[]{3,5},2));//0

    }


    //换钱的方法数
    public static int coins1(int[] arr, int aim) {
        //方法一：暴力递归，时间复杂度最差为O（aim^N）
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process1(arr, 0, aim);
    }

    public static int process1(int[] arr, int index, int aim) {
        int res = 0;
        if (index == arr.length) {//因递归中有index+1,所以最大为arr.length
            res = aim == 0 ? 1 : 0;//aim=0,只有一种方法，就是所有面值都不用
        } else {
            for (int i = 0; arr[index] * i <= aim; i++) {
                res += process1(arr, index + 1, aim - arr[index] * i);
                //对于arr[5,10,25,1],aim=1000
                //用0张5，让[5,10,25,1]组成剩下的1000，方法res1
                //用1张5，让[5,10,25,1]组成剩下的995，方法res2
                //用200张5，让[5,10,25,1]组成剩下的0，方法res201
                //res=res1+....+res201
            }
        }
        return res;
    }

    //方法二：动态规划，非最优，时间复杂度为O（N×aim）
    public static int coins2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];
        //dp[i][j]表示使用arr[0...i]组成j的方法数
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;//矩阵第一列，组成0的方法：1种
        }
        for (int j = 0; arr[0] * j <= aim; j++) {
            dp[0][arr[0] * j] = 1;//矩阵第一行，arr[0]组成arr[0]*j的方法：1种
        }
        //求一般位置的dp[i][j],由两者叠加
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                dp[i][j] = dp[i - 1][j];//不用arr[i]组成j
                dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;//先用一张arr[i],剩下arr[0...i]组成j-arr[i]
            }
        }
        return dp[arr.length - 1][aim];//arr[0...N]组成aim的方法数
    }
}