package com.big.ds_algo.leetcode

class _139_WordBreak_ {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(wordBreak("applepenapple", listOf("apple", "pen")))
        }

        fun wordBreak(s: String, wordDict: List<String>): Boolean {
            val dp = BooleanArray(s.length + 1)
            dp[0] = true
            var set = mutableSetOf<String>()
            wordDict.forEach {
                set.add(it)
            }
            for (hi in 1..s.length) {
                for (low in 0..hi) {
                    if(dp[low] && set.contains(s.substring(low, hi))) {
                        dp[hi] = true
                        break
                    }
                }
            }
            return dp[s.length]
        }
    }
}