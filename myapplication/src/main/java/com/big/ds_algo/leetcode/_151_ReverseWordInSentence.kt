package com.big.ds_algo.leetcode

class _151_ReverseWordInSentence {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            print(reverseWords("   sky is limit  "))
        }
        fun reverseWords(s: String): String {
            val rs = s.trim().split("\\s+".toRegex())
            val sb = StringBuilder()

            for (i in rs.size - 1 downTo 0)
                sb.append(rs[i] + " ")

            return sb.toString().trim()
        }
//        fun reverseWords(s: String): String {
//            var items = Stack<String>()
//
//            var word = ""
//            for (i in s) {
//                if (i == ' ' && word.isNotEmpty()) {
//                    items.push(word)
//                    word = ""
//                } else if (i != ' ') {
//                    word += i
//                }
//            }
//            if(word.isNotEmpty())
//                items.push(word)
//            var sentence = items.pop()
//            while (items.isNotEmpty()) {
//                sentence += " ${items.pop()}"
//            }
//            return sentence
//        }
    }
}