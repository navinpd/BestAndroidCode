package com.big.ds_algo.leetcode

class _198_HouseRobber_ {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(rob(intArrayOf(2, 1, 1, 2)))
        }

        fun rob(nums: IntArray): Int {
            if (nums.size >= 2)
                nums[1] = maxOf(nums[0], nums[1])
            for (i in 2 until nums.size) {
                nums[i] = maxOf(nums[i - 2] + nums[i], nums[i - 1])
            }
            return nums[nums.size - 1]
        }
    }

}