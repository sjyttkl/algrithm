package base;

/**
 * Create with: base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/14 11:09
 * version: 1.0
 * description:插入排序的细节讲解与复杂度分析
 * 　　时间复杂度O(N^2)，额外空间复杂度O(1)
 */
public class InsertionSort {


    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void show(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int arr[] = {6, 4, 2, 5, 8, 9};
        insertionSort(arr);
        show(arr);
    }
}
