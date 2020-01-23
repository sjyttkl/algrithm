package stack_base;

import java.util.Stack;

/**
 * Create with: intermediate
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/15 18:28
 * version: 1.0
 * description:
 * 条件：不能直接从A到C或C到A，必须经过B。
 * 这道题我的第一反应是用栈模拟递归。然而，好像并没有“用栈模拟递归”这回事（也不知道这个印象怎么来的）。
 * 以树的遍历为例，要说也是递归模拟栈吧？
 * 解答：用3个栈来模拟3座塔。那么，每一步该选哪个栈呢？出栈后又该往哪个栈进呢？
 * 这里的关键是，有两个原则：
 * =========================================
 * 小压大原则：小的只能在大的上面（汉诺塔的要求）；
 * 相邻不可逆原则：X->Y和Y->X是互斥的；
 * 根据着两条原则，假如上一步是A->B，那么当前就不能是B->A，而B-C和C->B中只有一个可以选（根据B、C栈顶元素大小）。所以每一步的走法都是由上一步确定的。
 */
public class hanoi {
    public static void hanoi(int n) {
        Stack<Integer> stackA = new Stack<Integer>();
        Stack<Integer> stackB = new Stack<Integer>();
        Stack<Integer> stackC = new Stack<Integer>();
        int lastMove = 3; // 1, 2, 3, 4, 分别代表左至中、中至左、中至右、右至中；1/2互斥、3/4互斥。初始化为3或4都行，trick。
        int thisMove = 0;
        int moveCnt = 0;
        for (int i = n; i > 0; i--) {
            stackA.push(i);
        }
        while (stackC.size() != n) {
            moveCnt++;
            if (lastMove <= 2) { // 上一步是1或2，下一步只能是3或4；
                if (stackB.isEmpty()) {
                    thisMove = 4;
                } else if (stackC.isEmpty()) {
                    thisMove = 3;
                } else if (stackB.peek() < stackC.peek()) {
                    thisMove = 3;
                } else {
                    thisMove = 4;
                }
                if (thisMove == 3) {
                    System.out.println(String.format("Move %d B ==> C", stackB.peek()));
                    stackC.push(stackB.pop());
                } else {
                    System.out.println(String.format("Move %d C ==> B", stackC.peek()));
                    stackB.push(stackC.pop());
                }
            } else {
                if (stackA.isEmpty()) {
                    thisMove = 2;
                } else if (stackB.isEmpty()) {
                    thisMove = 1;
                } else if (stackA.peek() < stackB.peek()) {
                    thisMove = 1;
                } else {
                    thisMove = 2;
                }
                if (thisMove == 1) {
                    System.out.println(String.format("Move %d A ==> B", stackA.peek()));
                    stackB.push(stackA.pop());
                } else {
                    System.out.println(String.format("Move %d B ==> A", stackB.peek()));
                    stackA.push(stackB.pop());
                }
            }
            lastMove = thisMove;
        }
        System.out.println("Total move count: " + moveCnt);
    }

    public static void main(String args[])
    {
        hanoi(3);
    }
}
