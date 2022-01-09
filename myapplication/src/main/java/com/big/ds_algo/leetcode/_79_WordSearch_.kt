package com.big.ds_algo.leetcode

class _79_WordSearch_ {
    //Given an m x n grid of characters board and a string word, return true if word exists in the grid.
    //The word can be constructed from letters of sequentially adjacent cells,
    // where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val arr1 = charArrayOf('A', 'B', 'C', 'E')
            val arr2 = charArrayOf('S', 'F', 'C', 'S')
            val arr3 = charArrayOf('A', 'D', 'E', 'E')
            val solution = arrayOf(arr1, arr2, arr3)
            println(exist(solution, "ABCA"))
        }

        fun exist(board: Array<CharArray>, word: String): Boolean {
            val pathSet = HashSet<Pair<Int, Int>>()

            fun checkIfExists(row: Int, column: Int, wordPos: Int): Boolean {
                if (wordPos == word.length)
                    return true
                if (row < 0 || column < 0
                    || row >= board.size || column >= board[0].size
                    || word[wordPos] != board[row][column]
                    || pathSet.contains(Pair(row, column))
                ) {
                    return false
                }

                pathSet.add(Pair(row, column))

                val result = (checkIfExists(row + 1, column, wordPos + 1) ||
                        checkIfExists(row - 1, column, wordPos + 1) ||
                        checkIfExists(row, column + 1, wordPos + 1) ||
                        checkIfExists(row, column - 1, wordPos + 1))
                pathSet.remove(Pair(row, column))
                return result
            }

            for (i in board[0].indices) {
                for (j in board.indices) {
                    if (checkIfExists(i, j, 0)) {
                        return true
                    }
                }
            }
            return false
        }
    }

}