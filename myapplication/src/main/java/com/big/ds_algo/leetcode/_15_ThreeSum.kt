package com.big.ds_algo.leetcode

class _15_ThreeSum {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(threeSum(intArrayOf(-3, -3, 6, -1, 0, 1, 2, -1, -4)))
            //-3,-2,-1,0,1,2,3
        }

        fun threeSum(nums: IntArray): List<List<Int>> {
            nums.sort()
            val solution = mutableListOf<List<Int>>()
            for (i in nums.indices) {
                if (i > 0 && nums[i] == nums[i - 1])
                    continue
                var start = i + 1
                var end = nums.size - 1
                while (start < end) {
                    if (nums[i] + nums[start] + nums[end] == 0) {
                        solution.add(listOf(nums[start], nums[i], nums[end]))
                        start++
                        while (nums[start] == nums[start - 1] && start < end) {
                            start++
                        }
                    } else if (nums[i] + nums[start] + nums[end] > 0) {
                        end--
                    } else {
                        start++
                    }
                }
            }
            return solution
        }
    }

}