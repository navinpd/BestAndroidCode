package com.big.ds_algo.leetcode

class ShuffleTheArray {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
//            val mat1 = intArrayOf(2, 5, 1, 3, 4, 7)
//            print1D(shuffle(mat1, 3))

            higherfunc(iAm = 111, myfunc = ::printMe, str = 12)

        }

        fun printMe(s: Int) {
            println(s)
        }

        // higher-order function definition
        fun higherfunc(str: Int, myfunc: (Int) -> Unit, iAm: Int) {
            // invoke regular function using local name
            myfunc(12)
        }

        fun shuffle(nums: IntArray, n: Int): IntArray {
            val res = IntArray(2 * n)
            for (i in 0 until n) {
                res[2 * i] = nums[i]
                res[2 * i + 1] = nums[n + i]
            }
            return res
        }

        fun print1D(mat: IntArray) {
            for (i in mat.indices) print(mat[i].toString() + " ")
        }
    }
}