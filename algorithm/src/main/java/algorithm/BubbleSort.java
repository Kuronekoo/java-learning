package algorithm;


import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 6, 1, 2, 8, 0};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        //获取队列长度
        int n = arr.length;
        //外循环 该循环执行n次
        for (int i = 0; i < n; i++) {
            //内循环，循环到队列尾的前一个，该循环执行其中的sort code第一次执行n-1次，最后一次执行0次，也就是一共执行了 0 + 1 + ... + n-1 次 所以sort code一共执行了 n(n-1)/2 次
            //当n趋向于无穷大时候 n^2 ->  n(n-1)/2 所以算法时间复杂度是O(n^2)
            for (int j = 0; j < n - i - 1; j++) {
                // -----------  sort code --------------
                //如果前一个数比后一个数大，
                //扫描次数和交换次数
                if (arr[j] > arr[j + 1]) {
                    //前一个数暂存
                    int tmp = arr[j];
                    //前一个数赋值后一个数
                    arr[j] = arr[j + 1];
                    //后一个数赋值前一个数，冒泡！
                    arr[j + 1] = tmp;
                }
                // -----------  sort code --------------
            }
        }
    }
}
 