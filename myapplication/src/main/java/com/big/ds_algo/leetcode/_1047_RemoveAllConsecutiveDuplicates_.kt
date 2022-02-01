package com.big.ds_algo.leetcode

//TL EXCEEDED
//Think of using Stack
class _1047_RemoveAllConsecutiveDuplicates_ {


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            print(removeDuplicates("aaaaaaaaa"))
        }

        fun removeDuplicates(s: String): String {
            var isDuplicateFound = true
            var s1 = s
            var solution = ""

            while (isDuplicateFound) {
                isDuplicateFound = false
                var i = 0
                while (i <= s1.length - 2) {
                    if (s1[i] == s1[i + 1]) {
                        isDuplicateFound = true
                        i += 2
                        if (i == s1.length - 1)
                            solution += s1[i]
                    } else {
                        solution += s1[i]
                        i++
                        if (i == s1.length - 1)
                            solution += s1[i]
                    }
                }
                if (solution.isNotEmpty())
                    s1 = solution
                solution = ""
            }
            return s1
        }
    }

}