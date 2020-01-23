package base;

import java.util.Arrays;

/**
 * Create with: base
 * author: sjyttkl
 * E-mail: 695492835@qq.com
 * date: 2020/1/15 10:29
 * version: 1.0
 * description:希尔排序的时间复杂度为：平均为：O(N^3/2)         最坏： O(N^2)
 */
public class Shell_sort {
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        int[] x = new int[]	{ 6, 2, 4, 1, 5, 9 };
        System.out.println("开始之前" + Arrays.toString(x));
        shell_sort(x, x.length);
        System.out.println("开始之后" + Arrays.toString(x));
    }

    public static void shell_sort2(int [] unsorted ,int len){
        int group ,i ,j ,temp;
        for(group = len/2;group>0 ;group/=2){
            for(i = group;i<len;i++){
                for(j = i-group;j>=0;j-=group){
                    if(unsorted[j]> unsorted[j+group]){

                    }
                }
            }
        }
    }
    private static void shell_sort(int[] unsorted, int len)
    {
        int group, i, j, temp;
        //取增量
        for (group = len / 2; group > 0; group /= 2)
        {
            //无须序列
            for (i = group; i < len; i++)
            {
                //有序序列
                for (j = i - group; j >= 0; j -= group)
                {
                    if (unsorted[j] > unsorted[j + group])
                    {
                        temp = unsorted[j];
                        unsorted[j] = unsorted[j + group];
                        unsorted[j + group] = temp;
                        System.out.println("--->" + Arrays.toString(unsorted));
                    }
                }
            }
        }

    }


}
