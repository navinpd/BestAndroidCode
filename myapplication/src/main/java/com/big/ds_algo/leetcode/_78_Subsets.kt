package com.big.ds_algo.leetcode

class _78_Subsets {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            subsets(intArrayOf(1, 2, 3)).forEach {
                print(it.toString())
            }
        }

        fun subsets(nums: IntArray): List<List<Int>> {
            val sol = mutableListOf<List<Int>>()

            fun repeatMe(items: List<Int>, pos: Int) {
                if (pos == nums.size) {
                    sol.add(items.toList())
                    return
                }
                val ans = mutableListOf<Int>()
                ans.addAll(items)
                repeatMe(ans.toList(), pos + 1)
                ans.add(nums[pos])
                repeatMe(ans.toList(), pos + 1)
            }
            repeatMe(emptyList(), 0)

            return sol.toList()
        }
    }

}