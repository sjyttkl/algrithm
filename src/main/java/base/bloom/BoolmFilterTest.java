package base.bloom;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.List;

/**
 * Create with: base.bloom
 * author: songdongdong
 * E-mail: songdongdong@weidian.com
 * date: 2022/5/20 15:01
 * version: 1.0
 * description:https://my.oschina.net/zhongwenhao/blog/4270751
 *
 * 布隆过滤器添加元素
 *      将要添加的元素给 k 个哈希函数
 *      得到对应于位数组上的 k 个位置
 *      将这 k 个位置设为 1
 * 布隆过滤器查询元素
 *      将要查询的元素给 k 个哈希函数
 *      得到对应于位数组上的 k 个位置
 *      如果 k 个位置有一个为 0，则肯定不在集合中
 *      如果 k 个位置全部为 1，则可能在集合中
 */
public class BoolmFilterTest {
    private static final int size = 1000000;

    private static final BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size);

    public static void main(String[] args) {
        test1();
        test2();
    }


    public static void test1(){
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }

        long startTime = System.nanoTime(); // 获取开始时间
        //判断这一百万个数中是否包含29999这个数
        if (bloomFilter.mightContain(29999)) {
            System.out.println("命中了");
        }
        long endTime = System.nanoTime();   // 获取结束时间
        System.out.println("程序运行时间： " + (endTime - startTime) + "纳秒");
    }

    public static void test2() {
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }
        List<Integer> list = new ArrayList<Integer>(1000);
        // 故意取10000个不在过滤器里的值，看看有多少个会被认为在过滤器里
        for (int i = size + 10000; i < size + 20000; i++) {
            if (bloomFilter.mightContain(i)) {
                list.add(i);
            }
        }
        System.out.println("误判的数量：" + list.size());
    }

}
