package com.big.ds_algo.leetcode

//Beautiful Strings - A beautiful subarray is defined as an array of any length having a specific number of odd elements. Given an array of integers and a number of odd elements that constitutes beauty, create as many distinct beautiful subarrays as possible. Distinct means the arrays do not share identical starting and ending indices, though they may share one of the two.
//Example
//arr = [1, 2, 3, 4, 5]
//numOfOdds = 2
//
//The following beatuiful subarrays can be formed:
//[1, 2, 3]
//[1, 2, 3, 4]
//[2, 3, 4, 5]
//[3, 4, 5]
//
//Constraints:
//
//1 <= length of the array <= 2 * 1e5
//1 <= arr[i] <= 1e9
//The array consists of distinct positive integers
//0 <= numOfOdds <= 2 * 1e5
class _BeautifulString {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            beautifulString(intArrayOf(1, 2, 3, 4, 5), 2).forEach {
                it.forEach { print( " $it") }
            }
        }

        fun beautifulString(arr: IntArray, oddNum: Int): List<IntArray> {
            val sol = mutableListOf<IntArray>()
            for (i in 0 until (arr.size - 1 - oddNum)) {
                var runningOdd = oddNum
                var intArr = mutableListOf<Int>()
                var localRun = i
                while (runningOdd >= 0 && localRun < arr.size - 1) {
                    if (arr[i] % 2 != 0 && runningOdd == 0) {
                        sol.add(intArr.toIntArray())
                        runningOdd--
                    } else if (arr[i] % 2 == 0 && runningOdd == 0) {
                        intArr.add(arr[localRun])
                        sol.add(intArr.toIntArray())
                        localRun++
                    } else {
                        if (arr[i] % 2 != 0) {
                            runningOdd--
                        }
                        intArr.add(arr[localRun])
                        localRun++
                    }
                }
            }

            return sol.toList()
        }
    }

}