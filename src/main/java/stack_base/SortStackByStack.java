package stack_base;

import java.util.Stack;

/**
 * Create with: stack_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/17 13:07
 * version: 1.0
 * description: 用一个栈实现另外一个栈的排序
 */
public class SortStackByStack {
    public static void sortStackByStack(Stack<Integer> stack) {
        Stack<Integer> help = new Stack<>();
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            while (!help.isEmpty() && help.peek() < cur) {
                stack.push(help.pop());
            }
            help.push(cur);
        }
        while (!help.isEmpty()) {
            stack.push(help.pop());
        }
    }


}
