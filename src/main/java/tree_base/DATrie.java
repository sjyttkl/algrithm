package tree_base;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Create with: tree_base
 * author: songdongdong
 * E-mail: songdongdong@weidian.com
 * date: 2021/4/21 16:19
 * version: 1.0
 * description:  https://blog.csdn.net/huoji555/article/details/104786615/
 *
 *
 * Trie树本质是一个确定的有限状态自动机(DFA)，核心思想是空间换时间，
 * 利用字符串的公共前缀来降低查询时间的开销以达到提高效率的目的。但由于Trie树的稀疏现象严重，空间利用率较低为了让Trie树实现占用较少的空间，
 * 同时还要保证查询的效率，最后提出了用2个线性数组来进行Trie树的表示，即双数组Trie(Double Array Trie).
 */

public class DATrie {
    private final int ARRAY_SIZE = 655350;  //数组大小
    private final int BASE_ROOT = 1;        //base根节点状态
    private final int BASE_NULL = 0;        //base空闲状态
    private final int CHECK_ROOT = -1;      //check根节点状态
    private final int CHECK_NULL = -2;      //check空闲状态
    private TrieNode base[];
    private int check[];


    /**
     *  @Description: DATrie节点
     */
    public class TrieNode {
        private int transferRatio; //转移基数
        private boolean isLeaf = false; //是否为叶子节点
        private Character label = null; //节点标识即插入的字符本身
        private int value = -1; //当该节点为叶子节点时关联的字典表中对应词条的索引号

        public int getTransferRatio() {
            return transferRatio;
        }

        public void setTransferRatio(int transferRatio) {
            this.transferRatio = transferRatio;
        }

        public boolean isLeaf() {
            return isLeaf;
        }

        public void setLeaf(boolean leaf) {
            isLeaf = leaf;
        }

        public Character getLabel() {
            return label;
        }

        public void setLabel(Character label) {
            this.label = label;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }



    /**
     *  @Description: 构造DATrie
     */
    public void build(List<String> words) {
        init();

        boolean shut = false;
        for (int idx = 0; idx < words.size(); idx++)
        {
            int startState = 0;
            char chars[] = words.get(idx).toCharArray();

            if (shut == false) {
                TrieNode node = insert(startState, getCode(chars[0]), (chars.length == 1), idx);
                node.setLabel(chars[0]);
            } else {
                for (int j=1; j<chars.length; j++) {
                    startState = transfer(startState, getCode(chars[j-1]));
                    TrieNode node = insert(startState, getCode(chars[j]), (chars.length == j+1), idx);
                    node.setLabel(chars[j]);
                }
            }

            if (idx == words.size()-1 && shut == false) {
                idx = -1;   //因为开始的时候还有一个加的过程
                shut = true;
            }

        }
    }



    /**
     *  @Description: 查询匹配项(正向匹配)
     */
    public List<Integer> match(String keyWord) {
        List<Integer> result = new ArrayList<Integer>();
        int startState, endState;

        char chars[] = keyWord.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            startState = 0;
            for (int j = i; j < chars.length; j++) {
                endState = transfer(startState, getCode(chars[j]));
                if (base[endState].getTransferRatio() != BASE_NULL && check[endState] == startState) { //节点存在于 Trie 树上
                    if (base[endState].isLeaf()) {
                        if (!result.contains(base[endState].getValue())) {
                            result.add(base[endState].getValue());
                        }
                    }
                    startState = endState;
                } else {
                    break;
                }
            }
        }

        return result;
    }



    /**
     *  @Description: 打印DATrie
     */
    public void printTrie() {
        System.out.println();
        System.out.printf("%5s", "idx");
        for (int i = 0; i < ARRAY_SIZE; i++) {
            if (base[i].getTransferRatio() != BASE_NULL) {
                System.out.printf("%7d\t", i);
            }
        }
        System.out.println();
        System.out.printf("%5s", "char");
        for (int i = 0; i < ARRAY_SIZE; i++) {
            if (base[i].getTransferRatio() != BASE_NULL) {
                System.out.printf("%7c\t", base[i].getLabel());
            }
        }
        System.out.println();
        System.out.printf("%5s", "base");
        for (int i = 0; i < ARRAY_SIZE; i++) {
            if (base[i].getTransferRatio() != BASE_NULL) {
                System.out.printf("%7d\t", base[i].getTransferRatio());
            }
        }
        System.out.println();
        System.out.printf("%5s", "check");
        for (int i = 0; i < ARRAY_SIZE; i++) {
            if (base[i].getTransferRatio() != BASE_NULL) {
                System.out.printf("%7d\t", check[i]);
            }
        }
        System.out.println();
        System.out.printf("%5s", "leaf");
        for (int i = 0; i < ARRAY_SIZE; i++) {
            if (base[i].getTransferRatio() != BASE_NULL) {
                System.out.printf("%7s\t", base[i].isLeaf() ? "是" : "否");
            }
        }
        System.out.println();
        System.out.printf("%5s", "idx");
        for (int i = 0; i < ARRAY_SIZE; i++) {
            if (base[i].getTransferRatio() != BASE_NULL) {
                System.out.printf("%7d\t", base[i].getValue());
            }
        }
        System.out.println();
    }



    /**
     *  @author: Ragty
     *  @Date: 2020/3/5 18:49
     *  @Description: 根据起始状态和转移技术插入新节点并返回插入的节点
     *  @param startState 起始状态
     *  @param offset  状态偏移量
     *  @param isLeaf  是否为叶子节点
     *  @param idx 当前节点在词典中的索引号
     */
    private TrieNode insert(int startState, int offset, boolean isLeaf, int idx) {
        int endState = transfer(startState, offset); //状态转移

        if (base[endState].getTransferRatio() != BASE_NULL && check[endState] != startState) { //已被占用
            do {
                endState += 1;
            } while (base[endState].getTransferRatio() != BASE_NULL);

            base[startState].setTransferRatio(endState - offset); //改变父节点转移基数

        }

        if (isLeaf) {
            base[endState].setTransferRatio(Math.abs(base[startState].getTransferRatio())*-1); //叶子节点转移基数标识为父节点转移基数的相反数
            base[endState].setLeaf(true);
            base[endState].setValue(idx); //为叶子节点时需要记录下该词在字典中的索引号
        } else {
            if (base[endState].getTransferRatio() == BASE_NULL) { //未有节点经过
                base[endState].setTransferRatio(Math.abs(base[startState].getTransferRatio())); //非叶子节点的转移基数一定为正
            }
        }
        check[endState] = startState;//check中记录当前状态的父状态

        return base[endState];
    }



    /**
     *  @Description: 根据起始状态和转移基数返回结束状态
     */
    private int transfer(int startState, int offset) {
        return Math.abs(base[startState].getTransferRatio())+offset; //状态转移
    }


    /**
     *  @Description: 获取base数组的下标
     */
    private int getCode(char c) {
        return (int)c;//这里必须大于0
    }



    /**
     *  @Description: 初始化DATrie（base，check数组全部初始化）
     */
    private void init() {
        base = new TrieNode[ARRAY_SIZE];
        check = new int[ARRAY_SIZE];

        for (int i = 0; i < ARRAY_SIZE; i++) {
            TrieNode node = new TrieNode();
            node.setTransferRatio(BASE_NULL);
            base[i] = node;
            check[i] = CHECK_NULL;
        }

        TrieNode root = new TrieNode();
        root.setTransferRatio(BASE_ROOT);
        base[0] = root;
        check[0] = CHECK_ROOT;
    }


    public static void main(String[] args) {

        List<String> words = new ArrayList<String>();
        words.add("清华");
        words.add("清华大学");
        words.add("清新");
        words.add("中华");
        words.add("中华人民");
        words.add("华人");
        words.add("学生");
        words.add("大学生");
        words.add("wo");
        words.add("shi");
        words.add("human");
        words.add("this");
        words.add("is");
        words.add("ragty");
        words.add("pump");
        words.add("it");
        words.add("up");
        words.add("中国");
        words.add("人名");
        words.add("中国人民");
        words.add("人民");
        words.add("java");
        words.add("java学习");



        //构建 Trie 树
        DATrie daTrie = new DATrie();
        daTrie.build(words);
        daTrie.printTrie();

        String keyWord = "清华大学生都是华人";
        List<Integer> result = daTrie.match(keyWord);
        System.out.println();
        System.out.println("输入语句为："+keyWord);

        //打印匹配结果
        System.out.println();
        System.out.printf("Match: {");
        for (int i = 0; i < result.size(); i++) {
            if (i == 0) {
                System.out.printf("%s", words.get(result.get(i)));
            } else {
                System.out.printf(", %s", words.get(result.get(i)));
            }
        }
        System.out.printf("}");
        System.out.println();
    }

}

