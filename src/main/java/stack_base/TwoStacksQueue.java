package stack_base;

import java.util.Stack;

/**
 * Create with: stack_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/17 12:51
 * version: 1.0
 * description: 两个栈组成一个队列
 */
public class TwoStacksQueue {
    public Stack<Integer> stackpush;
    public Stack<Integer> stackPop;

    public TwoStacksQueue() {
        stackPop = new Stack<>();
        stackpush = new Stack<>();
    }

    public void add(int pushInt) {
        stackpush.push(pushInt);
    }

    //出队列
    public int poll() {
        if (stackPop.empty() && stackpush.empty()) {
            throw new RuntimeException("Queue is empty!");
        } else if (stackPop.empty()) {
            while (!stackpush.empty()) {
                stackPop.push(stackpush.pop());
            }
        }
        return stackPop.pop();
    }

    public int peek() {
        if (stackPop.empty() && stackpush.empty()) {
            throw new RuntimeException("Queue is empty!");
        } else if (stackPop.empty()) {
            while (!stackpush.empty()) {
                stackPop.push(stackpush.pop());
            }
        }
        return stackPop.peek();
    }
}
