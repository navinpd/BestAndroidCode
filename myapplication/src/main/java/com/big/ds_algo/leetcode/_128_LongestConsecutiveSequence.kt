package com.big.ds_algo.leetcode

class _128_LongestConsecutiveSequence {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(longestConsecutive(intArrayOf(0, 3, 7, 2, 5, 8, 4, 6, 0, 1)))
        }

        fun longestConsecutive(nums: IntArray): Int {
            val set = nums.toSet()
            var solution = 0
            val items = mutableMapOf<Int, Int>()
            nums.forEach { current ->

                if (!set.contains(current - 1)) {

                    if (!items.containsKey(current)) {
                        var count = 1
                        while (set.contains(current + count)) {
                            count++
                        }
                        items[current] = count
                    }
                }
            }

            items.keys.forEach {
                solution = maxOf(items[it]!!, solution)
            }

            return solution
        }

    }

}