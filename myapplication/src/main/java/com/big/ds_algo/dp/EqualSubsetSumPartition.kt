package com.big.ds_algo.dp

class EqualSubsetSumPartition {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(partition(intArrayOf(1, 2, 3, 4)))
        }

        fun partition(args: IntArray): Boolean {
            var sum = 0
            args.forEach {
                sum += it
            }
            if (sum % 2 != 0)
                return false

//            return canPartitionRecursion(args, sum / 2, 0)

            val dp = Array(args.size) { BooleanArray(sum / 2 + 1) }
            return canPartitionTopDown(dp, args, sum / 2, 0)
        }


        fun canPartitionTopDown(
            data: Array<BooleanArray>,
            num: IntArray,
            sum: Int,
            i: Int
        ): Boolean {
            if (sum == 0)
                return true
            if (num.isEmpty() || i >= num.size)
                return false
            if (!data[i][sum]) {

                if (num[i] <= sum) {
                    if (canPartitionTopDown(data, num, sum - num[i], i + 1)) {
                        data[i][sum] = true
                        return true
                    }
                }
                data[i][sum] = canPartitionTopDown(data, num, sum, i + 1)
            }
            return data[i][sum]
        }

        private fun canPartitionRecursion(num: IntArray, sum: Int, i: Int): Boolean {
            if (sum == 0)
                return true
            if (num.isEmpty() || i >= num.size)
                return false

            if (num[i] <= sum) {
                if (canPartitionRecursion(num, sum - num[i], i + 1))
                    return true
            }

            return canPartitionRecursion(num, sum, i + 1)
        }
    }

}