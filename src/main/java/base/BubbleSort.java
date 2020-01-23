package base;

/**
 * Create with: base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/14 9:48
 * version: 1.0
 * description:冒泡排序的细节讲解与复杂度分析
 * 　　时间复杂度O(N^2)，额外空间复杂度O(1)
 */
public class BubbleSort {

    public static void bubbleSort(int[] arr)
    {
        if (arr == null || arr.length <2){
            return ;
        }
        for (int i=0;i<arr.length-1;i++)
        {
            for (int j = 0;j<arr.length-i-1;j++)
            {
                if (arr[j] > arr[j+1])
                {
                    swap(arr,j,j+1);
                }
            }
        }
    }
    public static void swap(int[] arr,int i,int j)
    {
        int temp;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void show(int [] arr)
    {
        for(int i=0;i<arr.length;i++)
        {
            System.out.print(arr[i]+" ");
        }
    }
    public static void main(String [] args)
    {
        int arr[] = {6,4,2,5,8,9};
        bubbleSort(arr);
        show(arr);
    }
}
