package stack_base;

import java.util.Stack;

/**
 * Create with: stack_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/2/17 12:40
 * version: 1.0
 * description: 设计一个有getMin功能的栈
 */
public class GetMin {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public GetMin() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public void push(int newNum) {
        if (this.stackMin.isEmpty()) {
            this.stackMin.push(newNum);
        } else if (newNum <= this.getmin()) {
            this.stackMin.push(newNum);
        }

        this.stackData.push(newNum);
    }

    public void push2(int newNum) {
        if (this.stackMin.isEmpty()) {
            this.stackMin.push(newNum);
        } else if (newNum <= this.getmin()) {
            this.stackMin.push(newNum);
        } else {
            int newMin = this.stackMin.peek();
            this.stackMin.push(newMin);
        }
        this.stackData.push(newNum);
    }

    public int pop2() {
        if (this.stackData.isEmpty()) {
            throw new RuntimeException("Your stack is empty");
        }
        this.stackMin.pop();
        return this.stackData.pop();
    }

    public int pop() {
        if (this.stackData.isEmpty()) {
            throw new RuntimeException("Your stack is empty");
        }
        int value = this.stackData.pop();
        if (value == this.getmin()) {
            this.stackMin.pop();
        }
        return value;
    }

    public int getmin() {
        if (this.stackMin.isEmpty()) {
            throw new RuntimeException("Your stack is empty");
        }
        return this.stackMin.peek();
    }

}
