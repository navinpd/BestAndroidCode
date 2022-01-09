package com.big.ds_algo.leetcode

class _12_IntToRoman {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(intToRoman(3888))
        }

        var solution = ""
        fun intToRoman(num: Int): String {
            var i = num
            if (i >= 999) {
                val localI = i / 1000
                repeat(localI, "M")
                i %= 1000
            }
            if (i >= 99) {
                val localI = i / 100
                when {
                    localI < 4 -> {
                        repeat(localI, "C")
                    }
                    localI == 4 -> {
                        solution += "CD"
                    }
                    localI == 9 -> {
                        solution += "CM"
                    }
                    localI >= 5 -> {
                        solution += "D"
                        repeat(localI - 5, "C")
                    }
                }
                i %= 100
            }
            if (i > 9) {
                val localI = i / 10
                when {
                    localI < 4 -> {
                        repeat(localI, "X")
                    }
                    localI == 4 -> {
                        solution += "XL"
                    }
                    localI == 9 -> {
                        solution += "XC"
                    }
                    localI >= 5 -> {
                        solution += "L"
                        repeat(localI - 5, "X")
                    }
                }
                i %= 10
            }
            if(i > 0) {
                val localI = i
                when {
                    localI < 4 -> {
                        repeat(localI, "I")
                    }
                    localI == 4 -> {
                        solution += "IV"
                    }
                    localI == 9 -> {
                        solution += "IX"
                    }
                    localI >= 5 -> {
                        solution += "V"
                        repeat(localI - 5, "I")
                    }
                }
            }

            return solution
        }

        fun repeat(count: Int, str: String) {
            var localCount = count
            while (localCount > 0) {
                solution += str
                localCount--
            }
        }

    }
}