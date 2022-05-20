package Str_Char;

/**
 * Create with: Str_Char
 * author: songdongdong
 * E-mail: songdongdong@weidian.com
 * date: 2022/2/12 23:04
 * version: 1.0
 * description:
 */
public class IsRotation {
    public static void main(String[] args) {

    }

    public boolean isRoatation(String a, String b) {
        if (a == null || b == null || a.length() != b.length()) {
            return false;
        }
        String b2 = a + b;
//        return Kmp(b2,a)!=-1 ;  //getIndexOf -> kmp algorithm
        return false;
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
//                if (chars[i] != chars[j]) {
//                    next[i] = j;
//                } else {
//                    next[i] = next[j];
//                }
                //2,
                next[i] =  j;

            } else {
                j = next[j];
            }
        }

    }

}
