package tree_base;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Create with: tree_base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/3/5 1:15
 * version: 1.0
 * description:  哈夫曼树
 */
public class HuffmanTree {
    // 构建一颗哈夫曼树
    public Map<Character, String> encode(Map<Character, Integer> frequencyForChar){
        PriorityQueue<Huff_Node> priorityQueue = new PriorityQueue<>();
        for (Character ch : frequencyForChar.keySet()){
            priorityQueue.add(new Huff_Node(ch,frequencyForChar.get(ch)));
        }
        while (priorityQueue.size() != 1){ // 构建小根堆
            Huff_Node left = priorityQueue.poll();
            Huff_Node right = priorityQueue.poll();
            priorityQueue.add(new Huff_Node(left, right, left.freq + right.freq));
        }
        return encode(priorityQueue.poll());
    }

    public Map<Character, String> encode(Huff_Node root){
        HashMap<Character, String> hashMap = new HashMap<>();
        encode(root, "", hashMap);
        return hashMap;
    }

    public void encode(Huff_Node root, String encoding, HashMap<Character,String> hashMap) {
        if (root.isLeaf){ // 已经到叶子节点，递归结束
            hashMap.put(root.ch, encoding);
            return;
        }
        encode(root.left, encoding + "0" ,hashMap);     // 编码左节点
        encode(root.right, encoding + "1", hashMap);    // 编码右节点
    }

    // 测试结果是否正确
    public static void main(String[] args){
        Map<Character, Integer> frequencyForChar = new HashMap<>();
        frequencyForChar.put('a', 10);
        frequencyForChar.put('b', 20);
        frequencyForChar.put('c', 40);
        frequencyForChar.put('d', 80);

        HuffmanTree huffmanCode = new HuffmanTree();
        Map<Character, String> encode = huffmanCode.encode(frequencyForChar);
        for (Character ch : encode.keySet()){
            System.out.println(ch + " : " + encode.get(ch));
        }
    }

}
