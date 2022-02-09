package Str_Char;

/**
 * Create with: Str_Char
 * author: songdongdong
 * E-mail: songdongdong@weidian.com
 * date: 2022/2/9 23:00
 * version: 1.0
 * description:
 * 字符串中数字子串的求和
 * 题目：给定一个字符串str,求其中全部数值串所代表的数值之和。
 * 要求：
 * 1.忽略小数点字符，例如:”A1.3”，将其看成1和3两个数字
 * 2.如果津贴数字子串的左侧出字符“-”,当连续出现数量为奇数的时候，则试后面数字为负，连续数值为偶数时，则视为正…
 * eg:“A-1BC--12”结果为->11
 */
public class NumSum {

    public static void main(String[] args) {
        System.out.println(numSum("A-1BC--12"));
    }

    public static int numSum(String str) {
        if (str == null) return 0;
        char[] chars = str.toCharArray();
        int res = 0;
        int num = 0;
        boolean posi = true;
        for (int i = 0; i < chars.length; i++) {
            int cur = chars[i] - '0';
            if (cur < 0 || cur > 9) {
                //之所以把num放到非num的判断里面是因为，到此为止这个num的
                //统计已经技术，例如123A-> num= (((1)*10)+2)*10 +3 = 123，
                //当计算到A的时候num正好统计出来...故可以计算res+=num...
                res += num;
                num = 0;
                if (chars[i] == '-') {
                    //当出现'-' 的时候就需要去判断这个'-' 到底是第一个'-' 还是第二个
                    //原则是第一次出现'-' 就令posi为false...出现两次就设为true...
                    if (i - 1 > -1 && chars[i - 1] == '-') {
                        //判断的时候，防止是'-' 开头的，则需要用i-1>-1判断...
                        posi = true;
                    } else {
                        posi = false;
                    }
                } else {
                    posi = true;
                }
            } else {
                num = num * 10 + (posi ? cur : -cur);
            }
        }
        res += num;
        return res;
    }
}
