package com.dream.tree.heap;


import java.util.PriorityQueue;

/**
 * @author fanrui
 * 215. 数组中的第K个最大元素(静态数据)
 * LeetCode 215： https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 */
public class KthLargestOfStaticData {


    // 思路一：小顶堆实现，时间复杂度 O(n * lg n)
    public int findKthLargest(int[] nums, int k) {

        if (nums == null || nums.length == 0 || k == 0) {
            return -1;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);

        for (int i = 0; i < nums.length; i++) {
            if (minHeap.size() < k) {
                minHeap.offer(nums[i]);
            } else if (minHeap.peek() < nums[i]) {
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        return minHeap.peek();
    }

    // 思路二：利用快排思想，时间复杂度 O（n）
    private int[] nums;
    private int k;

    public int findKthLargest2(int[] nums, int k) {
        this.nums = nums;
        this.k = nums.length - k;
        return select(0, nums.length - 1);
    }

    private int select(int left, int right) {
        int[] pivot_index = partition(left, right, medium(left, right));
        if (k >= pivot_index[0] && k <= pivot_index[1]) {
            return nums[k];
        } else if (k < pivot_index[0]) {
            return select(left, pivot_index[0] - 1);
        } else {
            return select(pivot_index[0] + 1, right);
        }
    }

    // 荷兰国旗问题，搞出三部分数据
    private int[] partition(int left, int right, int pivot_index) {
        if (left != pivot_index) {
            swap(left, pivot_index);
        }
        int pivot = nums[left];
        int less = left - 1;
        int l = left + 1;
        int more = right + 1;
        while (l < more) {
            if (nums[l] < pivot) {
                swap(++less, l++);
            } else if (nums[l] > pivot) {
                swap(--more, l);
            } else {
                l++;
            }
        }
        return new int[]{less + 1, l - 1};
    }

    // 数组中取三个数，便于 取三个数的中位数用于 pivot
    private int medium(int left, int right) {
        int medium = (left + right) / 2;
        if (nums[left] <= nums[medium] && nums[medium] <= nums[right]) {
            return medium;
        } else if (nums[medium] <= nums[left] && nums[left] <= nums[right]) {
            return left;
        } else {
            return right;
        }
    }

    private void swap(int index1, int index2) {
        int tmp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = tmp;
    }
}
