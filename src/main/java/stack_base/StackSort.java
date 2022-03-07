package stack_base;

import java.util.Stack;

/**
 * Create with: intermediate
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/15 18:15
 * version: 1.0
 * description:用一个栈实现另一个栈的排序
 */
public class StackSort {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();
        s.push(3);
        s.push(2);
        s.push(5);
        s.push(4);
        s.push(1);
        System.out.println("排序前：  ");
        System.out.println(s);
        s = sortStackbyStack(s);
        System.out.println("排序后：  ");
        System.out.println(s);

    }

    public static Stack<Integer> sortStackbyStack(Stack<Integer> stack) {
        Stack<Integer> help = new Stack<Integer>();
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            while (!help.isEmpty() && cur > help.peek()) { //每次找到比help数大的值
                stack.push(help.pop());
            }
//    		 help.push(stack.pop());  这个地方要是写了就pop了2次，显然不正确
            help.push(cur);
        }
        while (!help.isEmpty()) {
            stack.push(help.pop());
        }
        return stack;
    }

}
