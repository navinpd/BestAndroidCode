package com.big.ds_algo.leetcode

class _238_ProductArrayExceptSelf_ {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val nums = arrayOf(-1,1,0,-3,3)
            productExceptSelf(nums = nums.toIntArray()).forEach { print(" $it") }
        }

        fun productExceptSelf(nums: IntArray): IntArray {
            var temp = nums[0]
            val products = Array(nums.size) { 1 }

            for (i in nums.indices) {
                if (i == 0)
                    continue
                products[i] = temp
                temp *= nums[i]
            }
            products.forEach {
                print(" $it")
            }
            temp = 1
            for (i in (0..nums.size - 2).reversed()) {
                temp *= nums[i+1]
                products[i] = products[i] * temp
            }
            println()

            return products.toIntArray()
        }

    }
}