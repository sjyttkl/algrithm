package base;

/**
 * Create with: base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/14 11:15
 * version: 1.0
 * description: 归并排序的细节讲解与复杂度分析
 * 　　时间复杂度O(N*logN)，额外空间复杂度O(N)
 */
public class MergeSort {
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        sortProcess(arr, 0, arr.length - 1);
    }

    public static void sortProcess(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = (L + R) / 2;
        sortProcess(arr, L, mid);//T(n/2)
        sortProcess(arr, mid + 1, R);//T(N/2)
        merge(arr, L, mid, R);//O(N)
    }

    public static void merge(int[] arr, int L, int mid, int R) {
        int[] help = new int[R - L + 1];//和arr一样长度的数组
        int i = 0;
        int p1 = L; //左侧区域的第一个数
        int p2 = mid + 1;//右侧区域的第一个数
        while (p1 <= mid && p2 <= R) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        //两个必有且只有一个越界
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (int i1 = 0; i1 < help.length; i1++) {
            arr[L + i1] = help[i1];
        }
    }

    public static void show(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int arr[] = {6, 4, 2, 5, 8, 9};
        mergeSort(arr);
        show(arr);
    }
}
