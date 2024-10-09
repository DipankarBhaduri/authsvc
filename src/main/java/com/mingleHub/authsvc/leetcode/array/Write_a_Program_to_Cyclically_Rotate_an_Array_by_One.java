package com.mingleHub.authsvc.leetcode.array;

import java.util.Arrays;

public class Write_a_Program_to_Cyclically_Rotate_an_Array_by_One {
    public static void main(String[] args) {
        int [] arr = {2, 4, 5, 6, 8, 9};
        int [] ans = getRotateArrayBy1(arr);
        System.out.println(String.format("the rotate array is :: %s", Arrays.toString(ans)));
    }

    private static int[] getRotateArrayBy1(int[] arr) {
        int value = arr[arr.length - 1];
        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = value;
        return arr;
    }
}
