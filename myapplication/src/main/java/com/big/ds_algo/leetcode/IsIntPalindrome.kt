package com.big.ds_algo.leetcode

class IsIntPalindrome {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("value is " + isPalindrome(123))
        }

        private fun isPalindrome(v : Int) : Boolean {
            var reverseInt = 0
            var local = v
            while (local > 0) {
                reverseInt = reverseInt * 10 + (local % 10)
                local /= 10
            }

            return reverseInt == v
        }
     }


}