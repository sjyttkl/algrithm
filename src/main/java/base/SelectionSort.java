package base;

/**
 * Create with: base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/14 11:02
 * version: 1.0
 * description:选择排序的细节讲解与复杂度分析
 * 时间复杂度O(N^2)，额外空间复杂度O(1)
 */
public class SelectionSort {
    public static void selectionSort(int[] arr)
    {
        if(arr==null || arr.length <2)
        {
            return ;
        }
        for(int i=0;i<arr.length-1;i++)
        {
            int minIndex = i;
            for(int j=i+1;j<arr.length;j++)
            {
                minIndex = arr[j]<arr[minIndex]? j:minIndex;
            }
            swap(arr,i,minIndex);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = 0;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void show(int [] arr)
    {
        for (int i = 0; i < arr.length; i++){
          System.out.print(arr[i]+" ");
  }
    }

    public static void main(String [] args)
    {
        int arr[] = {6,4,2,5,8,9};
        selectionSort(arr);
        show(arr);
    }

}
