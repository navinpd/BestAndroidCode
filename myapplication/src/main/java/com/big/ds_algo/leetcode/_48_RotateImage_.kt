package com.big.ds_algo.leetcode

class _48_RotateImage_ {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arr1 = intArrayOf(5, 1, 9, 11)
            val arr2 = intArrayOf(2, 4, 8, 10)
            val arr3 = intArrayOf(13, 3, 6, 7)
            val arr4 = intArrayOf(15, 14, 12, 16)
            val solution = arrayOf(arr1, arr2, arr3, arr4)
            rotate(solution)
            println(solution)
        }

        fun rotate(matrix: Array<IntArray>) {
            //First transpose the matrix
            for (i in 0 until (matrix[0].size)) {
                for (j in 0 until i) {
                    val temp = matrix[i][j]
                    matrix[i][j] = matrix[j][i]
                    matrix[j][i] = temp
                }
            }
            // Swap 1st column with last. 2nd with 2nd last and so on
            for (i in 0..((matrix[0].size-1) / 2)) {
                val lastIndex = (matrix[0].size - 1 - i)
                for (j in matrix.indices) {
                    val temp = matrix[j][lastIndex]
                    matrix[j][lastIndex] = matrix[j][i]
                    matrix[j][i] = temp
                }
            }
        }
    }

}