package com.big.ds_algo.leetcode

class _90_Subset_II {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            subsetsWithDup(intArrayOf(1, 2, 2)).forEach {
                print(it.toString())
            }
        }

        fun subsetsWithDup(nums: IntArray): List<List<Int>> {
            val sol = mutableSetOf<Set<Int>>()

            fun repeatMe(items: List<Int>, pos: Int) {
                if (pos == nums.size) {
                    sol.add(items.toSet())
                    return
                }
                val ans = mutableListOf<Int>()
                ans.addAll(items)
                repeatMe(ans.toList(), pos + 1)
                ans.add(nums[pos])
                repeatMe(ans.toList(), pos + 1)
            }
            repeatMe(emptyList(), 0)
            val solution = mutableListOf<List<Int>>()
            sol.forEach {
                solution.add(it.toList())
            }

            return solution
        }

    }

}