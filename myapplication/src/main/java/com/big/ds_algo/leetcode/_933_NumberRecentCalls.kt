package com.big.ds_algo.leetcode

class _933_NumberRecentCalls {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            print(ping(1))
            print(ping(100))
            print(ping(3001))
            print(ping(3002))
        }

        var list = mutableListOf<Int>()

        fun ping(t: Int): Int {
            list.add(0, t)
            var count = 0
            list.onEach {
                if (t - it <= 3000) {
                    count++
                }
            }
            list.removeAll { t - it > 3000 }
            return count
        }
    }
}