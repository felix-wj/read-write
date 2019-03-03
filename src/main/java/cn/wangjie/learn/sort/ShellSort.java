package cn.wangjie.learn.sort;

/**
 * @program: read-write
 * @description:
 * @author: WangJie
 * @create: 2019-03-02 15:36
 **/
public class ShellSort {
    /**
     * 希尔排序的一趟插入
     *
     * @param arr 待排数组
     * @param d   增量
     */
    public static void shellInsert(int[] arr, int d) {
        for (int i = d; i < arr.length; i++) {
            int j = i - d;
            //记录要插入的数据
            int temp = arr[i];
            //从后向前，找到比其小的数的位置
            while (j >= 0 && arr[j] > temp) {
                //向后挪动
                arr[j + d] = arr[j];
                j -= d;
            }
            //存在比其小的数
            if (j != i - d) {
                arr[j + d] = temp;
            }

        }
    }

    public static void shellSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int d = arr.length / 2;
        while (d >= 1) {
            shellInsert(arr, d);
            d /= 2;
        }
    }
}
