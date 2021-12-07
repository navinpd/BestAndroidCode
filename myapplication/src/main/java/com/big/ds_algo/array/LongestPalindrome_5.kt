package com.big.ds_algo.array

class LongestPalindrome_5 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Final Answer: ${longestPalindrome("bb")}")
        }

        private fun longestPalindrome(s: String): String {
            if (s.isEmpty())
                return ""
            if (s.length == 1)
                return s
            var palindrome = ""
            var start = 0
            while (start < s.length) {
                val pal = getPalindromeString(start, s)
                if (pal.length > palindrome.length)
                    palindrome = pal
                start++
            }

            return palindrome
        }

        private fun getPalindromeString(start: Int, s: String): String {
            var begin = start;
            var end = start + 1
            //check for even points
            var palEven = ""
            while (begin >= 0 && end < s.length) {
                if (s[begin] == s[end]) {
                    palEven = s.subSequence(begin, end + 1).toString()
                    begin--
                    end++
                } else {
                    break
                }
            }
            if (palEven.isNotEmpty())
                println("Even $palEven")

            //check for odd points
            begin = start; end = start
            var palOdd = ""
            while (begin >= 0 && end < s.length) {
                if (s[begin] == s[end]) {
                    palOdd = s.subSequence(begin, end + 1).toString()
                    begin--
                    end++
                } else {
                    break
                }
            }
            if (palOdd.length > 1)
                println("Odd $palOdd")
            return if (palEven.length > palOdd.length) palEven else palOdd
        }

    }
}