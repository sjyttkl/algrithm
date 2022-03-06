package dynamic_programming.LCS;

/**
 * Create with: dynamic_programming.LCS
 * author: songdongdong
 * E-mail: songdongdong@weidian.com
 * date: 2021/7/11 12:09
 * version: 1.0
 * description: 最长公共子串问题
 * <p>
 * <p>
 * 给定两个字串 str1 和str2， 返回两个字符串的最长公共子串
 * <p>
 * 举例： str1="1AB2345CD" , str2="12345EF", 返回："2345"
 * <p>
 * 要求：如果str1 长度为M，str2长度为 N，实现时间复杂度O（M*N),空间复杂度为O（1）的方法
 */
public class longestCommonSubstring {
    public static void main(String[] args) {
        String a = "1AB2345CD";
        String b = "12345EF";
        System.out.println(lcst1(a, b));
        System.out.println(lcst2(a, b));
    }

    //动态规划方法，空间复杂度为O（M*N）
    public static String lcst1(String str1, String str2) {
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        int[][] dp = getdp(chars1, chars2);
        int end = 0;
        int max = 0;
        for (int i = 0; i < chars1.length; i++) {
            for (int j = 0; j < chars2.length; j++) {
                if (dp[i][j] > max) {
                    end = i;
                    max = dp[i][j]; // 长度
                }
            }


        }
        return str1.substring(end - max + 1, end + 1);
    }


    //构建dp矩阵
    public static int[][] getdp(char[] str1, char[] str2) {
        int[][] dp = new int[str1.length][str2.length];
        for (int i = 0; i < str1.length; i++) {
            if (str1[i] == str2[0]) {
                dp[i][0] = 1;
            }
        }
        for (int j = 1; j < str2.length; j++) {
            if (str1[0] == str2[j]) {
                dp[0][j] = 1;
            }
        }

        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                if (str1[i] == str2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }
        return dp;

    }


    //最优 ，空间复杂度为 O(1)
    public static String lcst2(String str1, String str2) {
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int row = 0; //斜线开始位置的行
        int col = chars2.length - 1; //斜线开始位置的列
        int maxLength = 0;//记录最大长度
        int endIndex = 0;//最大长度更新时，需要记录子串的结尾位置
        while (row < chars1.length) { //每行进行遍历
            int i = row;
            int j = col;
            int len = 0;
            // 从（i，j）开始右下方遍历
            while (i < chars1.length && j < chars2.length) {
                if (chars1[i] != chars2[j]) {
                    len = 0;
                } else {
                    len++;
                }
                //记录最大值，以及结束字符的位置
                if (len > maxLength) {
                    endIndex = i;
                    maxLength = len;
                }
                i++; //斜线 行
                j++; //斜线 列
            }
            if (col > 0) {//斜线开始位置的列先向左移动
                col--;
            } else {//列移动到最左之后，行向下移动
                row++;
            }

        }

        return str1.substring(endIndex - maxLength + 1, endIndex + 1);

    }


}
