package com.big.ds_algo.leetcode


class BestTimeToBuyAndSellStock_121 {
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            println(maxProfit(intArrayOf(7, 6, 4, 3, 1)))
        }

        fun maxProfit(prices: IntArray): Int {
            var relative = Int.MAX_VALUE
            var solution = 0
            for (i in prices.indices) {
                relative = minOf(relative, prices[i])
                val currentDiff = prices[i] - relative
                solution = maxOf(solution, currentDiff)

            }
            return solution
        }

    }

}