package Str_Char;

/**
 * Create with: Str_Char
 * author: songdongdong
 * E-mail: songdongdong@weidian.com
 * date: 2022/2/9 22:56
 * version: 1.0
 * description: 判断两个字符串是否互为变形词
 * 算法分析：
 * 1.首先进行非空判断和字符串长度的比较；
 * 2.将两个字符串分别转换成字符数组；
 * 3.增加一个统计的数组，统计第一个字符数组各个字符的数值；
 * 4.遍历第二个数组，比较第二个字符数组中各个字符的数值与第一个数组的统计值。
 */
public class isDeformation {
    public static void main(String[] args) {
        String a1 = "123";
        String a2 = "2331";
        System.out.println(isDeformation(a1, a2));
    }

    public static boolean isDeformation(String str1, String str2) {

        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }

        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int[] map = new int[256];

        for (int i = 0; i < chars1.length; i++) {
            map[chars1[i]]++;
        }

        for (int i = 0; i < chars2.length; i++) {
            if (map[chars2[i]]-- == 0) {//这里是先判断再执行递减操作，当"=="成立时，说明chars2中当前元素比chars1中多
                return false;
            }
        }
        return true;
    }
}
