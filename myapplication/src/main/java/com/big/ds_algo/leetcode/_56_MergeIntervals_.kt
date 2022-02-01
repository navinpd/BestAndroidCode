package com.big.ds_algo.leetcode

import java.util.*

class _56_MergeIntervals_ {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            merge(
                listOf(
                    intArrayOf(1, 3),
                    intArrayOf(2, 2),
                    intArrayOf(3, 3),
                    intArrayOf(5, 7),
                    intArrayOf(4, 6),
                ).toTypedArray()
            ).forEach {
                it.forEach { print(" $it") }
                println()
            }

        }

        fun merge(intervals: Array<IntArray>): Array<IntArray> {
            val pairs = mutableListOf<Pair<Int, Int>>()
            for (interval in intervals) {
                val pair = Pair(interval[0], interval[1])
                pairs.add(pair)
            }
            pairs.sortBy { it.first }

            val stack = Stack<Pair<Int, Int>>()
            stack.push(pairs[0])
            for (i in 1 until pairs.size) {
                var p1 = stack.pop()
                val p2 = pairs[i]

                if (p1.second < p2.first) {
                    stack.push(p1)
                    stack.push(p2)
                } else {
                    p1 = p1.copy(second = maxOf(p2.second, p1.second))
                    stack.push(p1)
                }
            }
            val solution = mutableListOf<IntArray>()
            for (i in stack) {
                val hi = intArrayOf(i.first, i.second)
                solution.add(hi)
            }
            return solution.toTypedArray()
        }
    }

}