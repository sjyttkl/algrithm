package dynamic_programming;

/**
 * Create with: dynamic_programming
 * author: songdongdong
 * E-mail: songdongdong@weidian.com
 * date: 2022/3/6 18:59
 * version: 1.0
 * description: 字符串的交替组成
 * 给定三个字符串str1、str2、aim，如果aim包含且仅包含来自
 * str1和str2的所有字符，且属于str1的字符之间保持其在str1中顺序，
 * 属于str2的字符也保持其在str2中的顺序，称aim是str1和str2的交错组成
 * 实现函数，判断aim是否是str1和str2的交错组成
 */
public class StrCross {
    public static void main(String[] args) {

        System.out.println(isCross1("AB","12","A12B"));
    }

    //空间复杂度O{M*N} ,时间复杂度O{M*N}
    public static boolean isCross1(String s1, String s2, String aim) {
        if (s1 == null || s2 == null || aim == null) {
            return false;
        }
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        char[] chaim = aim.toCharArray();
        //aim的长度一定是 M+Ns
        if (chaim.length != ch1.length + ch2.length) {
            return false;
        }
        boolean[][] dp = new boolean[ch1.length + 1][ch2.length + 1];
        dp[0][0] = true;
        for (int i = 1; i <= ch1.length; i++) {
            if (ch1[i - 1] != chaim[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }
        for (int j = 1; j <= ch2.length; j++) {
            if (ch2[j - 1] != chaim[j - 1]) {
                break;
            }
            dp[0][j] = true;
        }
        for (int i = 1; i <= ch1.length; i++) {
            for(int j=1 ;j<=ch2.length ;j++){
                if((ch1[i-1] == chaim[i+j-1] && dp[i-1][j])
                || (ch2[j-1] == chaim[i+j-1] && dp[i][j-1])){
                    dp[i][j] = true;
                }
            }
        }
        return dp[ch1.length] [ ch2.length];

    }
}
