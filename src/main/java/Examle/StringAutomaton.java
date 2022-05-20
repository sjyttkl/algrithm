package Examle;
import java.util.HashMap;

/**
 * Create with: Examle
 * author: songdongdong
 * E-mail: songdongdong@weidian.com
 * date: 2021/4/21 16:32
 * version: 1.0
 * description:https://blog.csdn.net/tyler_download/article/details/52549315
 */


public class StringAutomaton {
    private HashMap<Integer, HashMap<Character, Integer>> jumpTable = new HashMap<Integer, HashMap<Character, Integer>>();
    String P = "";
    private final int alphaSize = 3;
    public StringAutomaton(String p) {
        this.P = p;
        makeJumpTable();
    }

    private void makeJumpTable() {
        int m = P.length();
        for (int q = 0; q <= m; q++) {
            for (int k = 0; k < alphaSize; k++) {

                char c = (char)('a' + k);
                String Pq = P.substring(0, q) + c;


                int nextState = findSuffix(Pq);
                System.out.println("from state " + q + " receive input char " + c + " jump to state " + nextState);
                HashMap<Character, Integer> map = jumpTable.get(q);
                if (map == null) {
                    map = new HashMap<Character, Integer>();
                }

                map.put(c, nextState);
                jumpTable.put(q, map);
            }
        }
    }

    private int findSuffix(String Pq) {
        int suffixLen = 0;
        int k = 0;

        while(k < Pq.length() && k < P.length()) {
            int i = 0;
            for (i = 0; i <= k; i++) {
                if (Pq.charAt(Pq.length() - 1 - k + i) != P.charAt(i)) {
                    break;
                }
            }

            if (i - 1 == k) {
                suffixLen = k+1;
            }

            k++;
        }

        return suffixLen;
    }

    public int match(String T) {
        Integer q = 0;
        System.out.println("Begin matching...");

        for (int n = 0; n <= T.length(); n++) {
            HashMap<Character, Integer> map = jumpTable.get(q);
            int oldState = q;
            q = map.get(T.charAt(n));
            if (q == null) {
                return -1;
            }

            System.out.println("In state " + oldState + " receive input " + T.charAt(n) + " jump to state " + q);

            if (q == P.length()) {
                return q;
            }
        }

        return -1;
    }
}
