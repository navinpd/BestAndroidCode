package com.big.ds_algo.leetcode

class SpiralMatrix_54_ {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val item1: IntArray = intArrayOf(1, 2, 3)//, 2, 3)//, 4)
            val item2: IntArray = intArrayOf(5, 6, 7)//, 8)
            val item3: IntArray = intArrayOf(9, 10, 11)//, 12)
            val item4: IntArray = intArrayOf(13, 14, 15)//, 16)

            val solution = arrayOf(item1, item2, item3, item4)
            spiralOrder(solution)
            sol.forEach {
                print(" $it")
            }
        }

        val sol = mutableListOf<Int>()
        fun spiralOrder(matrix: Array<IntArray>): List<Int> {
            var top = 0
            var left = 0
            var right = matrix[0].size - 1
            var bottom = matrix.size - 1
            var direction = 1
            while (top <= bottom && left <= right) {
                if (direction == 1) {
                    for (i in left..right) {
                        sol.add(matrix[top][i])
                    }
                    ++top
                    direction = 2
                } else if (direction == 2) {
                    for (i in top..bottom) {
                        sol.add(matrix[i][right])
                    }
                    --right
                    direction++
                } else if (direction == 3) {
                    for (i in (left..right).reversed()) {
                        sol.add(matrix[bottom][i])
                    }
                    --bottom
                    direction++
                } else if (direction == 4) {
                    for (i in (top..bottom).reversed()) {
                        sol.add(matrix[i][left])
                    }
                    ++left
                    direction = 1
                }

//                move(matrix, left, top, right, top) //top left to top right
//                move(matrix, right, top, right, bottom)// top right to bottom right
//                move(matrix, right, bottom, left, bottom)// bottom left to bottom right
//                move(matrix, left, bottom, left, top)// bottom left to top left
//                top++
//                left++
//                bottom--
//                right--
            }

//            if (top == bottom && left == right && bottom == left) {
//                sol.add(matrix[top][left])
//            }
            return sol
        }

//        fun move(matrix: Array<IntArray>, sX: Int, sY: Int, eX: Int, eY: Int) {
//            var left = sX
//            var top = sY
//            var right = eX
//            var bottom = eY
//            if (left == right) {
//                while (top != bottom) {
//                    if (top < bottom) {
//                        sol.add(matrix[top][left])
//                        top++
//                    } else if (top > bottom) {
//                        sol.add(matrix[top][left])
//                        top--
//                    }
//                }
//            } else {
//                while (left != right) {
//                    if (left < right) {
//                        sol.add(matrix[top][left])
//                        left++
//                    } else if (left > right) {
//                        sol.add(matrix[top][left])
//                        left--
//                    }
//                }
//            }
//        }
    }

}