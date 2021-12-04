package com.big.ds_algo.array

import java.util.*

class ReverseInteger_7 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(reverse(-2147483412))
        }


        fun reverse(x: Int): Int {

            if (x < 10 && x > -10) {
                return x
            }
            val sign = if (x < 0) -1 else 1
            val queue = LinkedList<Int>()
            var localX = x
            localX *= sign

            var numbers = 1
            while (localX > 0) {
                queue.add(localX % 10)
                localX /= 10
                numbers *= 10
            }
            var answer = 0
            while (!queue.isEmpty()) {
                answer = answer * 10 + queue.remove()
                if (answer > Int.MAX_VALUE / 10 || answer < Int.MIN_VALUE / 10) {
                    return 0
                }
            }

            answer *= sign
            return answer
        }
    }
}