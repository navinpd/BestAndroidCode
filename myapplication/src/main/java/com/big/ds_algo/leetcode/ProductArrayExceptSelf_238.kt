package com.big.ds_algo.leetcode

class ProductArrayExceptSelf_238 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val nums = arrayOf(1, 2, 3, 4, 5)
            productExceptSelf(nums = nums).forEach { print(" $it") }
        }

        fun productExceptSelf(nums: Array<Int>): IntArray {
            var temp = 1
            var products = Array(nums.size) { 1 }

            for (i in nums.indices) {
                if (i == 0)
                    continue
                products[i] = temp
                temp *= nums[i]
            }
            temp = 1
            for (i in (nums.size - 2)..0) {
                if (i == 0)
                    continue
                products[i] = temp
                temp *= nums[i]
            }

            return products.toIntArray()
        }

    }
}