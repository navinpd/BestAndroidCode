package com.big.ds_algo.leetcode

class StrStr_28 {

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