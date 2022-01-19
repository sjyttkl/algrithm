package dynamic_programming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create with: dynamic_programming
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/24 0:09
 * version: 1.0
 * description:
 * 给定字符串s和单词词典.，确定s是否可以分割为一个或多个字典单词的空分序列。
 * 例如，给定
 * s =”leetcode”,
 * dict =[“leet”, “code”].
 * 返回true，因为“leetcode”可以被分割为“leet code”。
 * 分析：
 * dict={"leet","code"}
 * s = "leetcode
 * s的前五个能够成功分割。
 * i=4,j=0---->>leet*f(i) --->ture
 * 字符串分割
 * 状态 f(i) 前1个字符能不能被字典分割，
 * 地推公式：
 * f(j) && 从 第 j+1到第 i 个字符能不能再dict中找到
 * 初始化 f(0) true 空状态
 * f(1) 第一个字符
 */
public class WordBreak {
    public static void main(String[] args) {
        String str = "leetcode";
        ArrayList list = new ArrayList<String>();
        list.add("leet");
        list.add("code");
        System.out.println(wordBreak(str, (ArrayList) list));
    }

    public static boolean wordBreak(String str, ArrayList dict) {
        if (str.isEmpty() || dict.isEmpty()) {
            return false;
        }
        int n = str.length();
        boolean[] can_break = new boolean[n + 1];
        for (int i = 0; i < can_break.length; i++) {
            can_break[i] = false;
        }
        //初始化
        can_break[0] = true;
        //地推
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (can_break[j] && dict.contains(str.substring(j, i - j))) {
                    can_break[i] = true;
                    break;
                }
            }

        }
        return can_break[n];
    }
}
