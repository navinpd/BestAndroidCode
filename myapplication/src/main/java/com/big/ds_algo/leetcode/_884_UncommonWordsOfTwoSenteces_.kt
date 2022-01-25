package com.big.ds_algo.leetcode

import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
class _884_UncommonWordsOfTwoSenteces_ {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            uncommonFromSentences("wire for gate", "wire got late").forEach {
                println(it)
            }
        }

        fun uncommonFromSentences(s1: String, s2: String): Array<String> {
            val hashMap = mutableMapOf<String, Int>()
            for (value in s1.split(" ")) {
                hashMap[value] = (hashMap.getOrDefault(value,0)) + 1
            }
            for (value in s2.split(" ")) {
                hashMap[value] = (hashMap.getOrDefault(value,0)) + 1
            }
            val muta = mutableListOf<String>()
            for (value in hashMap.keys) {
                if (hashMap[value] == 1)
                    muta.add(value)
            }
            return muta.toTypedArray()
        }

    }

}