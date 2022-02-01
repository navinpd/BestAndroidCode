package com.big.ds_algo.leetcode



class _46_Permutations {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            permute(intArrayOf(-1, 2, 3)).forEach {
                it.forEach { item -> print(item.toString()) }
                println()
            }
//            val java_package: option = "com.example.application"
//            val java_multiple_files: option = true


        }

        var stringCombination = mutableListOf<String>()

        fun permute(nums: IntArray): List<List<Int>> {
            var item = ""
            nums.forEach { item += it.toString() }
            getCombination("", item)
            val solution = mutableListOf<List<Int>>()
            stringCombination.forEach {
                val numbers: List<Int> = it.map(Character::getNumericValue)
                solution.add(numbers)
            }
            return solution
        }

        fun getCombination(str: String, sub: String) {
            if (sub.isEmpty()) {
                stringCombination.add(str)
                return
            }
            if (sub.length == 1) {
                getCombination(str + sub, "")
                return
            }
            for (i in sub.indices) {
                getCombination(str + sub[i], sub.substring(0, i) + sub.substring(i + 1))
            }
        }
    }

}