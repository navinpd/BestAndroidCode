package com.big.ds_algo.leetcode

import java.util.*

class LetterCombination_17_Revisit {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(letterCombinations("5379"))
        }

        fun letterCombinations(digits: String): List<String> {
            val map = listOf("", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz")

            if (digits.length == 0)
                return emptyList()
            val solution = mutableListOf<String>()
            val queue: Queue<String> = LinkedList()
            queue.add("")

            while (!queue.isEmpty()) {
                val answer = queue.poll()
                if (answer.length == digits.length) {
                    solution.add(answer)
                } else {
                    val item = map[digits[answer.length].toString().toInt()]
                    for (i in item) {
                        queue.add(answer + i.toString())
                    }
                }
            }
            return solution
        }

    }
}