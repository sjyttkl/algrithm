package base;

import java.util.Arrays;

/**
 * Create with: base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/15 10:37
 * version: 1.0
 * description:时间复杂度：堆排序的时间复杂度为O(nlog2n)。
 * 算法稳定性：排序前后相同元素间的相对位置可能会发生改变，
 * 堆排序是一种不稳定的排序算法。
 */
public class HeapSort {
    public static void main(String args[])
    {
        HeapSort hs = new HeapSort();
        int[] array =     { 87, 45, 78, 32, 17, 65, 53, 9, 122 };
        System.out.println("开始之前" + Arrays.toString(array));
        System.out.print("构建大根堆：");
        hs.toString(hs.buildMaxHeap(array));
        System.out.print("\n" + "删除堆顶元素：");
        hs.toString(hs.deleteMax(array));
        System.out.print("\n" + "插入元素63:");
        hs.toString(hs.insertData(array, 63));
        System.out.print("\n" + "大根堆排序：");
        hs.toString(hs.heapSort(array));
        System.out.println("结束之后" + Arrays.toString(array));
    }


    // 插入操作:向大根堆array中插入数据data----->因为是插入一个元素，之前的堆是排序好，的所以不用像djustDownToUp方法那样，需要左右调整，甚至还需要上下调整
    public int[] insertData(int[] array, int data)
    {
        array[array.length - 1] = data; // 将新节点放在堆的末端
        int k = array.length - 1; // 需要调整的节点
        int parent = (k - 1) / 2; // 双亲节点
        while (parent >= 0 && data > array[parent])
        {
            array[k] = array[parent]; // 双亲节点下调
            k = parent;
            if (parent != 0)
            {
                parent = (parent - 1) / 2; // 继续向上比较
            } else
            { // 根节点已调整完毕，跳出循环
                break;
            }
        }
        array[k] = data; // 将插入的结点放到正确的位置 [9, 17, 32, 45, 53, 63, 65, 78, 87] [87, 45, 78, 63, 17, 65, 53, 9, 32]


        // 2, 第二种方式直接用 这个进行 建堆，就行了
        //buildMaxHeap(array);


        return array;
    }


    // 删除堆顶元素操作
    public int[] deleteMax(int[] array)
    {
        // 将堆的最后一个元素与堆顶元素交换，堆底元素值设为-99999
        array[0] = array[array.length - 1];
        array[array.length - 1] = -99999;
        // 对此时的根节点进行向下调整
        adjustDownToUp(array, 0, array.length);
        return array;
    }

    // 堆排序
    public int[] heapSort(int[] array)
    {
        array = buildMaxHeap(array); // 初始建堆，array[0]为第一趟值最大的元素
        for (int i = array.length - 1; i > 1; i--)
        {
            int temp = array[0]; // 将堆顶元素和堆低元素交换，即得到当前最大元素正确的排序位置
            array[0] = array[i];
            array[i] = temp;
            adjustDownToUp(array, 0, i); // 整理，将剩余的元素整理成堆
        }
        return array;
    }

    // 构建大根堆：将array看成完全二叉树的顺序存储结构
    private int[] buildMaxHeap(int[] array)
    {
        // 从最后一个节点array.length-1的父节点（array.length-1-1）/2开始，直到根节点0，反复调整堆//
        // 因为是从0开始的，所以要减去2，否则只要减去1就行了
        for (int i = (array.length - 2) / 2; i >= 0; i--)// [9, 17, 32, 45, 53, 63, 65, 78, 87]
        {
            adjustDownToUp(array, i, array.length);
        }
        return array;
    }

    // 将元素array[k]自下往上逐步调整树形结构
    private void adjustDownToUp(int[] array, int k, int length)
    {
        int temp = array[k];
        for (int i = 2 * k + 1; i < length - 1; i = 2 * i + 1)
        { // i为初始化为节点k的左孩子，沿节点较大的子节点向下调整 // 因为是从0开始的，所以其左还在需要+1.
            if (i <= length - 1 && array[i] < array[i + 1])
            { // 取节点较大的子节点的下标
                i++; // 如果节点的右孩子>左孩子，则取右孩子节点的下标
            }
            if (temp >= array[i])
            { // 根节点 >=左右子女中关键字较大者，调整结束
                break;
            } else
            { // 根节点 <左右子女中关键字较大者
                array[k] = array[i]; // 将左右子结点中较大值array[i]调整到双亲节点上
                k = i; // 【关键】修改k值，以便继续向下调整
            }
        }
        array[k] = temp; // 被调整的结点的值放人最终位置
    }

    public void toString(int[] array)
    {

        System.out.println("-->" + Arrays.toString(array));
    }

}
