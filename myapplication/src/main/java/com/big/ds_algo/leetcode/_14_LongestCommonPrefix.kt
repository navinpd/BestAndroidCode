package com.big.ds_algo.leetcode

class _14_LongestCommonPrefix {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(longestCommonPrefix(arrayOf("Navin", "Nav", "Naves", "Navzen")))
        }

        fun longestCommonPrefix(strs: Array<String>): String {
            strs.sort()
            var begin = strs[0]
            var last = strs[strs.size - 1]
            if (begin.length > last.length) {
                val temp = begin
                begin = last
                last = temp
            }
            var count = 0
            for (i in begin.indices) {
                if (begin[i] == last[i])
                    count++
                else
                    break
            }
            return begin.substring(0, count)
        }
    }

}