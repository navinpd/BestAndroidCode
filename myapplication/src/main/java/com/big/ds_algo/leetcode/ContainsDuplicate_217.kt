package com.big.ds_algo.leetcode

class ContainsDuplicate_217 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Ans is ${containsDuplicate(intArrayOf(1, 1, 1, 3, 3, 4, 3, 2, 4, 2))}")
        }

        fun containsDuplicate(nums: IntArray): Boolean {
            val hash = HashSet<Int>()
            for (i in nums.indices) {
                if (hash.contains(nums[i]))
                    return true
                else
                    hash.add(nums[i])
            }
            return false
        }
    }
}