package com.big.ds_algo.leetcode

internal class _11_ContainerWithMostWater {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(getMaxCount(intArrayOf(7, 3, 5, 6, 2, 8, 1, 8)))
        }

        private fun getMaxCount(array: IntArray): Int {
            var start = 0
            var end = array.size - 1
            var area = 0
            var depth = array.size - 1
            while (start < end) {
                if (array[start] > array[end]) {
                    if (array[end] * depth > area) {
                        area = array[end] * depth
                    }
                    end--
                } else {
                    if (array[start] * depth > area) {
                        area = array[start] * depth
                    }
                    start++
                }
                depth--
            }
            return area
        }
    }
}