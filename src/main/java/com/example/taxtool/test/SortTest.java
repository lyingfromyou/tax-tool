package com.example.taxtool.test;

import org.apache.poi.ss.formula.functions.T;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class SortTest {

    static Random RANDOM = new Random();

    public static int[] randomIntArr(int min, int max, int length) {
        int[] arr = new int[length];
        IntStream.range(0, length).forEach(index ->
                arr[index] = RANDOM.nextInt(min + (min + max))
        );
        return arr;
    }

    public static void main(String[] args) {
        int[] ints = randomIntArr(10, 30, 5);
        System.err.println(Arrays.toString(ints));
        quickSort(ints);
        System.err.println(Arrays.toString(ints));


    }

    public static void quickSort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int min, int max) {
        if (min > max) {
            return;
        }

        int index, i, j;
        index = arr[min];
        i = min;
        j = max;
        try {
            while (i < j) {
                while (i < j && index <= arr[j]) {
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
        } catch (Exception e) {

            System.err.println();
        }
        arr[i] = index;
        sort(arr, min, i -1);
        sort(arr, i + 1, max);


    }


    public static void doSort(int[] arr, int min, int max) {
        if (min > max) {
            return;
        }
        int index, i, j;
        index = arr[min];
        i = min;
        j = max;


    }

}
