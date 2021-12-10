package com.big.ds_algo.leetcode

class GenerateParentheses_22_Revisit {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            println(generateParenthesis(3))
        }

        val list = mutableListOf<String>()
        var length = 0
        fun generateParenthesis(n: Int): List<String> {
            length = n
            recursiveCall("", 0, 0)
            return list
        }

        fun recursiveCall(v: String, oCount: Int, cCount: Int) {
            if (v.length == length * 2) {
                list.add(v)
                return
            }
            if (oCount < length) {
                recursiveCall("$v(", oCount + 1, cCount)
            }
            if (cCount < oCount) {
                recursiveCall("$v)", oCount, cCount + 1)
            }
        }
    }
}