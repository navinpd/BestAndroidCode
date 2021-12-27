package com.big.ds_algo.leetcode

class FirstAndLastPositionInSortedArray_34 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var arr = arrayOf(5, 7, 7, 8, 8, 10)
            searchRange(arr.toIntArray(), 10).forEach { print(" $it") }
        }

        fun searchRange(nums: IntArray, target: Int): IntArray {
            val answer = intArrayOf(-1, -1)
            var low = 0
            var high = nums.size - 1
            //search for low Index
            while (low <= high) {
                var mid = (high + low) / 2
                when {
                    nums[mid] > target -> {
                        high = mid - 1
                    }
                    nums[mid] < target -> {
                        low = mid + 1
                    }
                    else -> {
                        answer[0] = mid
                        high = mid - 1 //continue searching in left side
                    }
                }
            }

            //search for high index
            low = 0
            high = nums.size - 1
            while (low <= high) {
                val mid = (high + low) / 2
                when {
                    nums[mid] > target -> {
                        high = mid - 1
                    }
                    nums[mid] < target -> {
                        low = mid + 1
                    }
                    else -> {
                        answer[1] = mid
                        low = mid + 1 //continue searching in right side
                    }
                }
            }

            return answer
        }
    }
}