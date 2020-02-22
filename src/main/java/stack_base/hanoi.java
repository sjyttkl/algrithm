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
 * 小压大原则：小的只能在大的上面（汉诺塔的要求）；中间的过程也需要保持这种状态
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

    //递归方式一：
    public static int hanoiProblem1(int num, String left, String mid, String right) {
        if (num < 1) {
            return 0;
        }
        return hanoiProblem1_process(num, left, mid, right, left, right);
    }

    public static int hanoiProblem1_process(int num, String left, String mid, String right, String from, String to) {
        if (num == 1) {//最后一个的操作
            if (from.equals(mid) || to.equals(mid)) {
                System.out.println("Move 1 form " + from + "  to " + to);
                return 1;
            } else {
                System.out.println("Move 1 form " + from + "  to " + mid);
                System.out.println("Move 1 from " + mid + "  to " + to);
                return 2;
            }
        }
        if (from.equals(mid) || to.equals(mid)) { // 如果是从中间开始，这段代码才用得上
            //从中间开始，或者从后面开始
            System.out.println("真的用不上吗？");
            String another = (from.equals(left) || to.equals(left)) ? right : left;//判断最终目标 相当于判断to的位置
            int part1 = hanoiProblem1_process(num - 1, left, mid, right, from, another);
            int part2 = 1;
            System.out.println("Move " + num + " from " + from + "to" + to);
            int part3 = hanoiProblem1_process(num - 1, left, mid, right, another, to);
            return part1 + part2 + part3;
        } else {
            //这里表述最后两个的时候，的操作
            int part1 = hanoiProblem1_process(num - 1, left, mid, right, from, to);
            int part2 = 1;
            System.out.println("Move " + num + " from " + from + " to " + mid);//步奏2
            int part3 = hanoiProblem1_process(num - 1, left, mid, right, to, from);
            int part4 = 1;
            System.out.println("Move " + num + " from " + mid + " to " + to);
            int part5 = hanoiProblem1_process(num - 1, left, mid, right, from, to);
            return part1 + part2 + part3 + part4 + part5;


        }
    }

    public static void main(String args[]) {
        System.out.println("============递归方式====================");
        hanoiProblem1(3, "Left", "Mid", "Right");
        System.out.println("============非递归方式====================");
        hanoi(3);
    }
}
