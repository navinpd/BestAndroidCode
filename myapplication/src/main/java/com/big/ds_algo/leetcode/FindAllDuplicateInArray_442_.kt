package com.big.ds_algo.leetcode

import kotlin.math.abs

class FindAllDuplicateInArray_442_ {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(findDuplicates(intArrayOf(4, 3, 2, 7, 8, 2, 3, 1)))
        }

        fun findDuplicates(nums: IntArray): List<Int> {
            val ans = mutableListOf<Int>()
            for (i in nums.indices) {
                val idx = abs(nums[i]) - 1
                if (nums[idx] > 0) {
                    nums[idx] *= -1
                } else {
                    ans.add(idx + 1)
                }
            }
            return ans.toList()
        }
    }

}