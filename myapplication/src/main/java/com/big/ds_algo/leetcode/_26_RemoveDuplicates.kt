package com.big.ds_algo.leetcode

class _26_RemoveDuplicates {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var array = intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)
            println(removeDuplicates(array))
            array.forEach {
                println(it)
            }
        }


        fun removeDuplicates(nums: IntArray): Int {
            var count = 1
            var startPoint = 0
            if (nums.isEmpty())
                return 0
            else if (nums.size == 1)
                return 1
            for (i in 1 until nums.size) {
                if (nums[i - 1] != nums[i]) {
                    count++
                    nums[++startPoint] = nums[i]
                }
            }
            return count
        }

    }
}