package com.big.ds_algo.leetcode

class _73_SetMatrixZero {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arr1 = intArrayOf(2, 4, 6)
            val arr2 = intArrayOf(1, 0, 5)
            val solution = arrayOf(arr1, arr2)
            setZeroes(solution)
            println(solution)
        }

        fun setZeroes(matrix: Array<IntArray>): Unit {
            val listItems = mutableListOf<Pair<Int, Int>>()
            for (i in matrix.indices) {
                for (j in matrix[i].indices) {
                    if (matrix[i][j] == 0)
                        listItems.add(i to j)
                }
            }
            for (pair in listItems) {
                val i = pair.first
                val j = pair.second
                for (k in 0 until (matrix[0].size)) {
                    matrix[i][k] = 0
                }
                for (k in matrix.indices) {
                    matrix[k][j] = 0
                }
            }
        }
    }

}