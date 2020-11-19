package com.example.taxtool.test;

public class QuickSortTest {


    public static void main(String[] args) {

    }


    public static void quickSort(int[] arr) {
        doSort(arr, 0, arr.length - 1);
    }


    public static void doSort(int[] arr, int min, int max) {
        if (min > max) {
            return;
        }
        int index, i, j;
        i = min;
        j = max;
        index = arr[min];
        while (i < j) {
            while (i < j && index < arr[j]) {
                j--;
            }
            if (i < j) {
                arr[i] = arr[j];
                i++;
            }
            while (i < j && index >= arr[i]) {
                i++;
            }
            if (i < j) {
                arr[j] = arr[i];
                j--;
            }
        }
        arr[i] = index;
        doSort(arr, min, i - 1);
        doSort(arr, i + 1, max);
    }

}
