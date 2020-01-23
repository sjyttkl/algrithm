package base;

import java.util.Arrays;

/**
 * Create with: base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/15 10:44
 * version: 1.0
 * description:
 * 思想：每当一趟匹配过程中出现字符比较不等，不需要回溯i指针，
 * 而是利用已经得到的“部分匹配”的结果将模式向右“滑动”尽可能远 的一段距离后，继续进行比较。
 * * 时间复杂度O(n+m)
 */
public class KMP {
    public static void main(String[] args) {
        String str = "ababcabcacbab";
        char[] str_chars = str.toCharArray();
        String pattern = "abcac";
        char[] pattern_chars = pattern.toCharArray();
        int[] next = new int[pattern_chars.length];
        getNext(pattern_chars, next);
        System.out.println("next --->" + Arrays.toString(next));// next --->[-1,
        // 0, 0, 1, 1,
        // 2, 0, 0]
        int result = Kmp(str_chars, pattern_chars, next);
        System.out.println(result);

    }

    private static int Kmp(char[] str_chars, char[] pattern_chars, int[] next) {
        int i = 0;
        int j = 0;
        while (i <= str_chars.length - 1 && j <= pattern_chars.length - 1) {
            if (j == -1 || str_chars[i] == pattern_chars[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j < pattern_chars.length) {
            return -1;
        } else {
            return i - pattern_chars.length; // 返回模式串在主串中的头下标
        }
    }

    private static void getNext(char[] chars, int[] next) {
        int i = 0;
        int j = -1;
        next[0] = -1;
        // abcac
        while (i < next.length - 1) // 这里不能忘记要-1否则会出现错误
        {
            if (j == -1 || chars[i] == chars[j]) {
                ++i;
                ++j;
                //下面的这一对 if--else 是对传统的kmp进行改进的, 下面的 1 和2,取一个就行了
                //1,
                if (chars[i] != chars[j]) {
                    next[i] = j;
                } else {
                    next[i] = next[j];
                }
                //2,
//                next[i] = j;

            } else {
                j = next[j];
            }
        }

    }


}
