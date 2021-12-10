package com.big.ds_algo.leetcode

class Zigzag_6 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(convert("AB", 1))
        }

        fun convert(s: String, numRows: Int): String {
            var output = ""
            val arrayItem = Array(numRows) { "" }

            var count = 0
            var isIncreasing = true
            var arrayPosition = 0
            while (count < s.length) {
                arrayItem[arrayPosition] += s[count].toString()

                if (numRows > 1)
                    if (isIncreasing) {
                        arrayPosition++
                    } else {
                        arrayPosition--
                    }
                if (arrayPosition == 0) {
                    isIncreasing = true
                } else if (arrayPosition == numRows - 1) {
                    isIncreasing = false
                }
                count++
            }

            for (str in arrayItem)
                output += str
            return output
        }


    }

}