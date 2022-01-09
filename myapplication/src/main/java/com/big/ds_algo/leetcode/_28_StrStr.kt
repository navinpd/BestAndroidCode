package com.big.ds_algo.leetcode

class _28_StrStr {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Position is ${strStr("aaaaa","aba")}")
        }

        fun strStr(haystack: String, needle: String): Int {
            return haystack.indexOf(needle)
        }
    }

}