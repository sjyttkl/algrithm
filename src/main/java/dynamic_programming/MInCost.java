package dynamic_programming;

/**
 * Create with: dynamic_programming
 * author: songdongdong
 * E-mail: songdongdong@weidian.com
 * date: 2022/3/6 13:05
 * version: 1.0
 * description: 最小编辑代价
 */
public class MInCost {
    public static void main(String[] args) {
        String str1 = "ab12cd3";
        String str2 = "abcdf";
        int dc = 3; //删除代价
        int ic = 5; //插入代价
        int rc = 2; //替换代价

        System.out.println(minCost1(str1, str2, ic, dc, rc));
        System.out.println(minCost2(str1, str2, ic, dc, rc));

    }

    /**
     * 时间负复杂度O（M*N） 空间复杂度O（M*N）
     *
     * @param str1
     * @param str2
     * @param ic   插入代价
     * @param dc   删除代价
     * @param rc   替换代价
     * @return
     */
    public static int minCost1(String str1, String str2, int ic, int dc, int rc) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int row = chs1.length + 1; //
        int col = chs2.length + 1; //
        int[][] dp = new int[row][col]; //生成（M+1）（N+1） 的矩阵
        for (int i = 1; i < row; i++) {
            dp[i][0] = dc * i;
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = ic * j;
        }
        //先行，再列
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (chs1[i - 1] == chs2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1]; //相等，
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + rc; //不相等，替换代价
                }
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic); //插入
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc); //删除

            }
        }

        return dp[row - 1][col - 1];

    }

    //空间压缩法
    public static int minCost2(String str1, String str2, int ic, int dc, int rc) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        char[] longs = chs1.length >= chs2.length ? chs1 : chs2;
        char[] shorts = chs1.length < chs2.length ? chs1 : chs2;
        if (chs1.length < chs2.length) {
            int tmp = ic;
            ic = dc;
            dc = tmp;
        }
        int[] dp = new int[shorts.length + 1];
        for (int i = 1; i <= shorts.length; i++) {
            dp[i] = i * ic;
        }

        for (int i = 1; i <= longs.length; i++) {
            int pre = dp[0];
            dp[0] = dc * i;
            for (int j = 1; j <= shorts.length; j++) {
                int tmp = dp[j];
                if (longs[i - 1] == shorts[j - 1]) {
                    dp[j] = pre;
                } else {
                    dp[j] = pre + rc;
                }
                dp[j] = Math.min(dp[j], dp[j - 1] + ic);
                dp[j] = Math.min(dp[j], tmp + dc);
                pre =tmp;
            }
        }
        return dp[shorts.length];
    }
}
