package com.mingleHub.authsvc.leetcode.array;

public class Maximum_and_minimum_of_an_array_using_minimum_number_of_comparisons {
    public static void main(String[] args) {
        int [] arr = {3, 5, 4, 1, 9, -2, 5, 6 , 4 , -22, 435};
        int [] answer = getMaximumAndMinimumNumber (arr);
        System.out.println(String.format("the maximum number is %s, and minimum %s", answer[0], answer[1]));
    }

    private static int[] getMaximumAndMinimumNumber(int[] arr) {
        int maxNumber = Integer.MIN_VALUE;
        int minNumber = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            maxNumber = arr[i] > maxNumber ? arr[i] : maxNumber;
            minNumber = arr[i] < minNumber ? arr[i] : minNumber;
        }

        return new int[]{maxNumber, minNumber};
    }
}
