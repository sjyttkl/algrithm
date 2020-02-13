package stack_base;

/**
 * Create with: intermediate
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/14 12:41
 * version: 1.0
 * description:
 *
 * 小和问题和逆序对问题  ,使用归并算法
 * 　　小和问题
 * 　　在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组的小和。
 * 　　例子：
 * 　　[1,3,4,2,5]
 * 　　1左边比1小的数，没有；
 * 　　3左边比3小的数，1；
 * 　　4左边比4小的数，1、3；
 * 　　2左边比2小的数，1；
 * 　　5左边比5小的数，1、3、4、2；
 * 　　所以小和为 1+1+3+1+1+3+4+2=16
 */
public class SmallSum {
    public static int smallSum(int[] arr){
        if (arr == null || arr.length < 2){
            return 0;
        }
        return mergeSort(arr, 0, arr.length-1);
    }
    public static int mergeSort(int[] arr, int l, int r){
        if (l == r){
            return 0;
        }
        int mid = l + ((r - l) >> 1);//获取中间数 int mid = (L + R) / 2;
        return mergeSort(arr, l, mid) + mergeSort(arr, mid+1, r) + merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int l, int m, int r){
        int[] help = new int[r - l + 1];  //设置help 的长度
        int i = 0;
        int p1 = l;
        int p2 = m+1;
        int res = 0;
        while (p1 <= m && p2 <= r){
            res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m){
            help[i++] = arr[p1++];
        }
        while (p2 <= r){
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++){
            arr[l + i] = help[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,4,2,5};
        System.out.println(smallSum(arr));
    }


}
