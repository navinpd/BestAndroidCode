package com.big.ds_algo.leetcode

class _209_MinSizeSubArraySum_ {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            print(minSubArrayLen(15, intArrayOf(1,2,3,4,5)))
        }

        fun minSubArrayLen(target: Int, nums: IntArray): Int {
            var minCount = Int.MAX_VALUE
            var start = 0
            var end = 0
            var currentSum = nums[0]
            while (end <= nums.size - 1) {

                if (currentSum < target) {
                    end++
                    if (end < nums.size)
                        currentSum += nums[end]
                } else {
                    if ((end - start + 1) < minCount) {
                        minCount = end - start + 1
                        currentSum -= nums[start]
                        start++
                    } else {
                        currentSum -= nums[start]
                        start++
                    }
                }
            }

            if (minCount == Int.MAX_VALUE)
                minCount = 0

            return minCount
        }
    }

    fun nextGreaterElement(a1: IntArray, a2: IntArray) = IntArray(a1.size).apply {
        a1.forEachIndexed {i, n ->
            this[i] = a2.slice(a2.indexOf(n)..a2.lastIndex).find {
                it > n
            } ?: -1
        }
    }

}