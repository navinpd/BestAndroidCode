package com.big.ds_algo.leetcode

class FindDuplicate_287_ {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(findDuplicate(intArrayOf(1,2,3,4,3)))
        }

//        fun findDuplicate(nums: IntArray): Int {
//            val size = nums.size
//            val sum = size * (size + 1) / 2
//            var sumLocal = 0
//            nums.forEach { sumLocal += it }
//            return size - (sum - sumLocal)
//        }

        fun findDuplicate(nums: IntArray): Int {
            //Fast & Slow
            var slow = nums[0]
            var fast = nums[0]
            do {
                slow = nums[slow]
                fast = nums[nums[fast]]
            } while (slow != fast)
            slow = nums[0]
            while (slow != fast) {
                slow = nums[slow]
                fast = nums[fast]
            }
            return fast
        }
    }

}