package com.big.ds_algo.leetcode

class _784_LetterCasePermutation {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(letterCasePermutation("a1b2"))
        }

        fun letterCasePermutation(s: String): List<String> {
            val upperCaseSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toSet()
            val set = mutableSetOf<String>()
            fun makeNewCombination(str: String, pos: Int) {
                if (pos == str.length) {
                    set.add(str)
                    return
                }
                val item = str[pos]
                if (upperCaseSet.contains(item)) {
                    makeNewCombination(
                        str.substring(0, pos) + item.toLowerCase() + str.substring(
                            pos + 1
                        ), pos + 1
                    )
                    makeNewCombination(str, pos + 1)
                } else {
                    makeNewCombination(str, pos + 1)
                }
            }
            makeNewCombination(s.toUpperCase(), 0)
            return set.toList()
        }
    }

}