package com.big.ds_algo.array

class LongestNonRepeatingSubstring_3 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(lengthOfLongestSubstring("pwwkew"))
        }

        fun lengthOfLongestSubstring(s: String): Int {
            if (s.isEmpty()) return 0

            val set = HashSet<Char>()
            var start = 0
            var end = 0
            var length = 0

            while (end < s.length) {
                if (set.contains(s[end])) {
                    set.remove(s[start++])
                } else {
                    if (length < end - start) {
                        length = end - start
                    }
                    set.add(s[end++])
                }
            }
            return length + 1
        }
    }
}