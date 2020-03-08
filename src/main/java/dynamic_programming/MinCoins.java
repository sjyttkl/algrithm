package dynamic_programming;

/**
 * Create with: dynamic_programming
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/3/8 19:29
 * version: 1.0
 * description: 换钱的最少货币数
 * 给定数组arr, arr中所有的值都为正数且不重复。每个值代表一中面值的货币，
 * 每种面值的货币可以使用任意张，再给定一个整数aim代表要找的钱数，求组成aim的最少货币数。
 * <p>
 * 思路
 * 思路
 * 举例子arr=[5,2,3], aim=20
 * 不使用当前货币arr[i]的最小张数，dp[i-1][j]
 * 适应一张当前货币arr[i]的最小张数，dp[i-1][j-arr[i]] + 1
 * 使用两张当前货币arr[i]的最小张数，dp[i-1][j-2*arr[i]] + 2
 * 如果j-arr[i] < 0,说明发生越界了，arr[i]数值太大，一个就超过了j的值，所以令dp[i][j]=dp[i-1][j]; 如果不越界，就选arr[i]，货币数是dp[i][j-arr[i]]+1;
 * <p>
 * 1)第一行的元素表示，只是用arr[0]的货币，能找开的钱数，只能找开arr[0]的倍数，其他的都找不开，统一设置为max
 * 2）第一列，是想要找的钱数0，需要多少张货币，不需要货币，全部设置为0.
 * dp[i-1][j]表示不使用当前货币，找开总钱数j需要多少张
 */
public class MinCoins {

    public static void main(String[] args) {
        int[] m = {5, 2, 3};
        //方法1
        System.out.println(minCoins1(m, 20));//4
        System.out.println(minCoins1(m, 0));//0
        System.out.println(minCoins1(new int[]{5, 3}, 2));//-1
        //方法2
        System.out.println(minCoins2(m, 20));//4
        System.out.println(minCoins2(m, 0));//0
        System.out.println(minCoins2(new int[]{5, 3}, 2));//-1
    }

    //换钱的最小货币数
    //方法1，经典动态规划，复杂度都为O(N×aim)
    public static int minCoins1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return -1;
        }
        int n = arr.length;
        int max = Integer.MAX_VALUE;
        int[][] dp = new int[n][aim + 1];
        //dp[i][j]的意义：任意使用arr[0...n]组成j所需的最小张数
        //赋值dp的第一行，表示只能使用arr[0]的情况下，找某个钱数的最小张数
        //如arr[0]=2,能找开2，4，6，8... 所以dp[0][2]=1,dp[0][4]=2...其他为max
        for (int j = 1; j <= aim; j++) {
            dp[0][j] = max;
            if (j - arr[0] >= 0 && dp[0][j - arr[0]] != max) {
                //aim>arr[0]，且j是arr[0]的倍数
                dp[0][j] = dp[0][j - arr[0]] + 1;
            }
        }
        int left = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= aim; j++) {
                left = max;
                if (j - arr[i] >= 0 && dp[i][j - arr[i]] != max) {
                    left = dp[i][j - arr[i]] + 1;
                }
                dp[i][j] = Math.min(left, dp[i - 1][j]);
                //一般情况，dp[i][j]=min{ dp[i-1][j], dp[i][j-arr[i]]+1 }
            }
        }
        return dp[n - 1][aim] != max ? dp[n - 1][aim] : -1;
    }

    //方法2，空间压缩，时间复杂度为O(N×aim),空间复杂度为O(aim)
    public static int minCoins2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return -1;
        }
        int n = arr.length;
        int max = Integer.MAX_VALUE;
        int[] dp = new int[aim + 1];
        for (int j = 1; j <= aim; j++) {
            dp[j] = max;
            if (j - arr[0] >= 0 && dp[j - arr[0]] != max) {
                dp[j] = dp[j - arr[0]] + 1;
            }
        }
        int left = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= aim; j++) {
                left = max;
                if (j - arr[i] >= 0 && dp[j - arr[i]] != max) {
                    left = dp[j - arr[i]] + 1;
                }
                dp[j] = Math.min(dp[j], left);
            }
        }
        return dp[aim] != max ? dp[aim] : -1;
    }

}
