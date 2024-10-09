package com.mingleHub.authsvc.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _1_TwoSum {
    public static void main(String[] args) {
        int [] nums = {3, 4, 5, 7};
        int target = 11;
        int [] ans = getTwoSumSolution1 (nums, target);
        int [] ans2 = getTwoSumSolution2 (nums, target);
        System.out.println(String.format("the sum of %s and %s is equal to %s", nums[ans[0]], nums[ans[1]], target));
        System.out.println(String.format("the sum of %s and %s is equal to %s", nums[ans2[0]], nums[ans2[1]], target));
    }

    /*
    this solution implemented by me and the time_complexity is O(N);
     */
    private static int[] getTwoSumSolution1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int [] answer = new int[2];

        if (nums == null || nums.length < 2) {
            return answer;
        }

        for (int i = 0; i <nums.length; i++) {
            int value = nums[i];

            if (map.containsKey(target - value)) {
                answer[0] = map.get(target - value);
                answer[1] = i;
                break;
            } else {
                map.put(nums[i], i);
            }
        }

        return answer;
    }

    /*
    the top most optimized solution is the leetcode
     */

    private static int[] getTwoSumSolution2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i <nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }

            map.put(target - nums[i], i);
        }

        return new int[]{};
    }
}
