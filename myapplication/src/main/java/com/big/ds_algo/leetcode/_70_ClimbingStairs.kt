package com.big.ds_algo.leetcode

class _70_ClimbingStairs {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(climbStairs(5))
        }

        fun climbStairs(n: Int): Int {
            val listItems = mutableListOf<Int>()
            listItems.add(1)
            listItems.add(1)
            for (i in 2..n) {
                listItems.add(listItems[i - 2] + listItems[i - 1])
            }
            return listItems[listItems.size - 1]
        }
    }

}