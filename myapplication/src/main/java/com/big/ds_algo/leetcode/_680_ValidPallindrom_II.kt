package com.big.ds_algo.leetcode

class _680_ValidPallindrom_II {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(validPalindrome("abc"))
        }

        fun validPalindrome(s: String): Boolean {
            var l = 0
            var h = s.length - 1

            while (l < h) {
                if (s[l] == s[h]) {
                    l++
                    h--
                } else {
                    return isPalindrome(l, h - 1, s) || isPalindrome(l + 1, h, s)
                }
            }
            return true
        }

        fun isPalindrome(low: Int, high: Int, s: String): Boolean {
            var l = low
            var h = high
            while (l < h) {
                if(s[l] != s[h])
                    return false
                l++
                h--
            }
            return true
        }

    }

}