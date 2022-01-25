package com.big.ds_algo.leetcode

import java.lang.Math.max


class _55_JumpGame {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(canJump(intArrayOf(0)))
        }

        fun canJump(nums: IntArray): Boolean {
            var temp = nums[0]
            for (i in 1 until nums.size) {
                temp -= 1
                if(temp < 0)
                    return false
                temp = max(temp, nums[i])
            }
            return true
        }
    }
}