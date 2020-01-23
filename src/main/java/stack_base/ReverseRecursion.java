package stack_base;

import java.util.Stack;

/**
 * Create with: intermediate
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/15 17:32
 * version: 1.0
 * description:如何仅用递归函数和栈操作逆序一个栈
 * 要求：只有一个待逆转的栈，不能有其他数据结构。
 * 可以从两个角度来再现这个递归过程：1，自底向上；2，递归栈。
 */
public class ReverseRecursion {

    public static int getAndRemoveLastElement(Stack<Integer> stack) {
        int top = stack.pop();
        if (stack.isEmpty()) {
            return top;
        }
        int last = getAndRemoveLastElement(stack);
        stack.push(top);
//       System.out.println(top);
        return last;
    }

    public static void reverse(Stack<Integer> stack)
    {
        if(stack.isEmpty())
        {
            return ;
        }
        int i = getAndRemoveLastElement(stack);  // 1   2  3   4
        reverse(stack);
        stack.push(i);
    }

    public static void main(String args[]) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        reverse(stack);
        System.out.println();
        while(!stack.isEmpty())
        {
            System.out.print(stack.pop()+" ");
        }
    }

}
