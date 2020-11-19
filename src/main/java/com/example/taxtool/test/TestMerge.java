package com.example.taxtool.test;

import cn.hutool.core.util.NumberUtil;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class TestMerge {

    final static Random RANDOM = new Random();

    public static int[] randomCreateArr(int min, int max, int size) {
        int[] arr = new int[size];
        IntStream.range(0, size).forEach(i ->
                arr[i] = (min + RANDOM.nextInt(max - min))
        );
        System.err.println(Arrays.toString(arr));
        return arr;
    }

    public static void main(String[] args) {
        int[] a = randomCreateArr(0, 50, 6);
//        int[] a = new int[]{6, 2, 7, 1, 9, 3, 0};
        quickSort(a);
        System.err.println(Arrays.toString(a));
//        System.err.println(Integer.toHexString(16));
    }

    public static void quickSort(int[] a) {
        sort(a, 0, a.length - 1);
    }

    public static void sort(int[] a, int min, int max) {
        if (min > max) {
            return;
        }
        int i, j, index;
        i = min;
        j = max;
        index = a[min];
        int lun = 1;
        while (i < j) {
            System.err.println("第 " + lun + "轮");
            System.err.println(String.format("i: %s, j: %s, index: %s", i, j, index));
            while (i < j && index <= a[j]) {
                j--;
            }
            if (i < j) {
                a[i] = a[j];
                i++;
            }
            System.err.println(Arrays.toString(a));

            while (i < j && index >= a[i]) {
                i++;
            }
            if (i < j) {
                a[j] = a[i];
                j--;
            }
            System.err.println(Arrays.toString(a));
            ++lun;
            System.err.println("");
            System.err.println("=====================================");
            System.err.println("");
        }
        a[i] = index;
        System.err.println(Arrays.toString(a));

        System.err.println();
        System.err.println(String.format("i: %s, j: %s, index: %s", i, j, index));
        System.err.println();

        sort(a, min, i - 1);
        sort(a, i + 1, max);

    }

}
