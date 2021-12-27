package com.big.ds_algo.recursion

class CalculatePowUsingRecursion {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(calculatePowUsingRecursion(2, 5))
        }

        fun calculatePowUsingRecursion(n: Int, pow: Int): Int {
            if (pow == 1)
                return n
            val i = calculatePowUsingRecursion(n, pow / 2)
            return if (pow % 2 != 0) {
                n * i * i
            } else {
                i * i
            }
        }
    }

}