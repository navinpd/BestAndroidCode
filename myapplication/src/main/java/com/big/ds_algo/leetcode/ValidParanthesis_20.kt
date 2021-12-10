package com.big.ds_algo.leetcode

import java.util.*
import kotlin.collections.HashMap

class ValidParanthesis_20 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(isValid("{"))
        }

        fun isValid(s: String): Boolean {
            val map = HashMap<Char, Char>()
            map['('] = ')'
            map['['] = ']'
            map['{'] = '}'
            val stack = Stack<Char>()
            for (i in s) {
                if (map.containsKey(i)) {
                    stack.push(i)
                } else if (stack.isEmpty()) {
                    return false
                } else {
                    val j = stack.pop()
                    if (map[j] != i) {
                        return false
                    }
                }
            }
            return stack.isEmpty()
        }
    }
}