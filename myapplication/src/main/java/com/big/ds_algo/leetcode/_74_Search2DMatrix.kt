package com.big.ds_algo.leetcode

class _74_Search2DMatrix {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(searchMatrix(arrayOf(intArrayOf(1)), 1))
        }

        fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
            var v = matrix.size
            var h = matrix[0].size
            //search in column
            var row = 0
            for (i in matrix.indices) {
                if (matrix[i][h - 1] >= target) {
                    row = i
                    break
                }
            }
            var sol = false
            for (j in matrix[0].indices) {
                if(matrix[row][j] == target) {
                    sol = true
                }
            }
            return sol
        }
    }

}