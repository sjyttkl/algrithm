package base;

/**
 * Create with: base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/14 23:27
 * version: 1.0
 * description:快速排序 最好情况O(n*logn) 最坏情况O（n^2）平均时间复杂度O(n*logn)
 * https://blog.csdn.net/Handsome2013/article/details/82858209
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {1, 2, 7, 5, 4, 3, 9, 2, 7};
        System.out.print("排序前     ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "  ");
        }
        System.out.println();
        quickSort3(arr, 0, arr.length - 1);
        System.out.print("排序后     ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "  ");
        }

        System.out.println();
        System.out.println("============左程云方式的快排====需求1：给一个数组，和其中一个数，将大于这个数和小于这个数的分成两边，同时不能使用多余数组================");
        int[] arr2 = {1, 2, 7, 5, 4, 3, 9, 2, 7};
        int num = 7;//随意给个定值,最好是中位数
        System.out.print("排序前     ");
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + "  ");
        }
        group(arr2, num);
        System.out.println();
        System.out.print("排序后     ");
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
        System.out.println();
        System.out.println("============左程云方式的快排====需求2：给定一个整型数组，然后以最后一个数为基准，将大于基准值的放右边，小于基准值放到左边。最后数组的情况就是，在基准值两边，一边是全部大于基准值，一边是全部小于基准值;================");
        Integer[] arr3 = {1, 2, 7, 5, 4, 3, 9, 2, 7};
        System.out.print("排序前     ");
        for (int i = 0; i < arr3.length; i++) {
            System.out.print(arr3[i] + "  ");
        }
        System.out.println();
        partition4(arr3, 0, arr3.length - 1);
        System.out.print("排序后     ");
        for (int i = 0; i < arr3.length; i++) {
            System.out.print(arr3[i] + " ");
        }
        System.out.println();
        System.out.println("============左程云方式的快排====题目三：下边讲解我们的改进快排================");
        Integer[] arr4 = {1, 2, 7, 5, 4, 3, 9, 2, 7};
        System.out.print("排序前     ");
        for (int i = 0; i < arr4.length; i++) {
            System.out.print(arr4[i] + "  ");
        }
        System.out.println();
        quickSort4(arr4, 0, arr.length - 1);
        System.out.print("排序后     ");
        for (int i = 0; i < arr4.length; i++) {
            System.out.print(arr4[i] + " ");
        }
    }
    /**
     * 这是普通的快速排序法
     *
     * @param arr
     * @param low
     * @param high
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {//找出分割点，然后左分割，右分割
            int patition = partition(arr, low, high);
            quickSort(arr, low, patition - 1);
            quickSort(arr, patition + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int num = arr[low];// 我们取第一个作为基准值，而且基准值开始是在最低位
        while (low < high) {
            while (low < high && arr[high] > num) {
                high--;
            }
            swap(arr, low, high);//交换后，基准值跑到高位较小的位置了
            while (low < high && arr[low] <= num) {
                low++;
            }
            swap(arr, low, high);//交换后，基准值又跑到低位较大位置了

        }
        return low;

    }

    /**
     * 细微的改进版本的快速排序
     *
     * @param arr
     * @param small
     * @param big
     */
    public static void quickSort2(int[] arr, int small, int big) {
        // 来一个终止条件
        if (small >= big) {// 这个终止条件，不能是small==big，是因为在下边的左边来一波和右边来一波中，可能存在small大于big的
            return;
        }
        // 首先是找出分割点
        int mid = partition(arr, small, big);
        // 左边来一波
        quickSort2(arr, small, mid - 1);
        // 右边来一波
        quickSort2(arr, mid + 1, big);
    }

    public static int partition2(int[] arr, int small, int big) {
        int num = arr[small];
        while (small < big) {
            while (small < big && num <= arr[big]) {
                big--;
            }
            swap(arr, small, big);
            while (small < big && num > arr[small]) {
                small++;
            }
            swap(arr, small, big);
        }
        return small;
    }

    //改进版本：就是添加了排除相等部分的重复排序：区别就是在左边和右边开始的时候，取的边界不一样
    public static void quickSort3(int[] arr, int low, int high) {
        if (low < high) {
            int[] patition = patition3(arr, low, high);
            quickSort(arr, low, patition[0] - patition[1]);
            quickSort(arr, patition[0] + 1, high);
        }
    }

    public static int[] patition3(int[] arr, int low, int high) {
        int num = arr[low];// 我们取第一个作为基准值
        int equal = 0;// 这个用来记录等于基准值的数量，至少是1
        int result[] = new int[2];
        while (low < high) {
            while (low < high && arr[high] > num) {
                high--;
            }
            swap(arr, low, high);
            while (low < high && arr[low] <= num) {
                if (arr[low] == num) {
                    equal++;
                }
                low++;
            }

            swap(arr, low, high);

        }
        result[0] = low;
        result[1] = equal;
        return result;

    }

    //二：左程云方式的快排
    //其实就是多了一部分，多了指出相等值的那部分，第二次就不需要再去重复排那些相等值的过程
    //首先来看一个问题：我们快排第一步是找一个基准值 ，然后右边值都大于基准值，左边值都小于基准值。
    //题目一：我随意给定一个数组和一个给定值，大于该值放右边，其他放左边，同时不能使用多余数组
//    通常我们选取最后一个或者第一个值为我们的基准值。下边我们选取最后一个值作为基准值，而且如果我们使用了把中间值拿过来放着后边不做比较，那么我们比较次数会更少一点
    public static void group(int[] arr, int num) {
        int p = 0;
        int small = 0;// 小数的边界
        int big = arr.length - 1;// 大数的边界
        while (small < big) {
            if (arr[p] < num) {
                swap(arr, small, p);
                small++;
                p++;
            } else {
                swap(arr, big, p);
                big--;
            }
        }
    }

    //题目二：给定一个整型数组，然后以最后一个数为基准，将大于基准值的放右边，小于基准值放到左边。最后数组的情况就是，在基准值两边，一边是全部大于基准值，一边是全部小于基准值;
    //我们使用三个指针来区分三块，一块是小于区域，中间是等于区域，右边是大于区域。开始的时候，小于和大于区域都是在数组外边，
    // 要是存在一个符合要求的，就放到相应区域，同时该区域长度就增加1。如果是有一个小于的，首先交换当前值和less后边那个值，
    // 那么小于的区域的指针就++less,同时也会推着中间部分往右边跑，所以当前指针cur++;如果是满足大于区域，
    // 就把当前cur指向的数和大于那边的区域的边界more前边的那个数值交换，同时大于区域增加，就是--more,但是这里我们的cur就不变化。
    public static Integer[] partition4(Integer[] arr, int L, int R) {

        // 使用三个指针来将我们的数据分成三类，
        int cur = L;// 这个指向当前数据
        int less = L - 1;// 小数的前面一个
        int more = R + 1;// 大数的后边一个
        int num = arr[arr.length - 1];// 以最后一个值为基准来排出大于和小于
        Integer[] arr1 = new Integer[2];
        while (cur < more) {
            // System.out.println(cur);
            if (arr[cur] > num) {
                swap(arr, cur, --more);
            } else if (arr[cur] < num) {
                swap(arr, cur++, ++less);// less位置增加一个，就会推着cur向后
            } else {
                cur++;
            }
        }
        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i]);
        }
        arr1[0] = less + 1;//等于num的左边界
        arr1[1] = more - 1;//等于num的右边界
        return arr1;
    }


    //题目三：下边讲解我们的改进快排：
    //快排就是不断细分，对一个小部分都进行上边相同的操作。
    //开始分为两边，然后对两边分别再分两边，一直分到最小，就是分成单个为止。
    public static void quickSort4(Integer[] arr, int L, int R) {
        if (L < R) {
            Integer[] p = partition4(arr, L, R);// 返回的是相同数值区域的两个边界
            quickSort4(arr, L, p[0] - 1);//p[0]是等于部分的左边界
            quickSort4(arr, p[1] + 1, R);//p[1]是等于部分的右边界

        }
    }

    private static void swap(int[] arr, int low, int high) {
        int temp = arr[low];
        arr[low] = arr[high];
        arr[high] = temp;

    }

    // 交换数组位置
    private static void swap(Integer[] arr, Integer m, Integer n) {
        int temp = arr[m];
        arr[m] = arr[n];
        arr[n] = temp;
    }


}
