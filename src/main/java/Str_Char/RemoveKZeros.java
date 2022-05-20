package Str_Char;

/**
 * Create with: Str_Char
 * author: songdongdong
 * E-mail: songdongdong@weidian.com
 * date: 2022/2/9 23:03
 * version: 1.0
 * description:去掉字符串中连续出现K个0的子串
 * 给定一个字符串str，和一个整数k, 如果str中正好有连续K 个'0'字符出现，把连续的 k 个 '0'去掉，返回处理后的子串。
 * 　　【解题思路】
 * <p>
 * 　　1. 定义两个变量，count表示'0'连续出现的次数，start表示连续出现的开始位置，
 * <p>
 * 　　2. 将去掉连续0 的时机放在了当前字符不是 0 的情况
 * <p>
 * 　　3. 因此对于最后可能以 0 结尾，这时没有去掉，因此最后应该对count进行进行检查是否等于k
 * <p>
 * 　　其时间复杂度是O(N),空间复杂度是 O(1)
 */
public class RemoveKZeros {
    public static void main(String[] args) {

        System.out.println(removeKZeros("A00B", 2));
    }

    public static String removeKZeros(String str, int k) {
        if (str == null || k < 1) {
            return str;
        }

        char[] chars = str.toCharArray();
        int start = -1;
        int count = 0;
        for (int i = 0; i < chars.length; i++) {

            //首先当前字符是0
            if (chars[i] == '0') {
                count++;
                start = start == -1 ? i : start;
            } else {
                //当前字符不是0，数量已经到了k，进行移除操作
                if (count == k) {
                    while (count-- != 0) {
                        chars[start++] = 0; //这个0 不是字符'0'，字符'0'的阿西克码是48, 这个0 的阿西克码就是0，'A00B'-->'A  B'再转换成字符串就是'AB'
                    }
                }
                //当前字符不是0，数量不是k，说明是其他字符
                count = 0;
                start = -1;
            }
        }

        //对于最后以 0 结尾的，需要再次进行判断，因为我们判断数量是否到达k 是在当前元素不是 0 的情况
        if (count == k) {
            while (count-- != 0) {
                chars[start++] = 0;
            }
        }
        return  String.valueOf(chars);
    }
}
