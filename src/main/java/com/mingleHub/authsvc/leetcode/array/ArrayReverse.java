package com.mingleHub.authsvc.leetcode.array;

import java.util.Arrays;

public class ArrayReverse {
    public static void main(String[] args) {
        int [] arr = {3, 5, 6, 7, 8, 9, 10, 13, 15, 17, 19};
        int [] ans = getReversedArray(arr);
        System.out.println(String.format("the reversed array is : %s", Arrays.toString(ans)));
    }

    private static int[] getReversedArray(int[] arr) {
        for (int i = 0; i < arr.length / 2; i ++) {
            int value = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = arr[i];
            arr[i] = value;
        }
        return arr;
    }
}