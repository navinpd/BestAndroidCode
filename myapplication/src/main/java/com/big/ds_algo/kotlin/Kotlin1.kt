package com.big.ds_algo.kotlin

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking

// Code inside the flow { ... } builder block can suspend.
// Values are collected from the flow using collect function.
// Flows are cold streams similar to sequences — the code inside a flow builder does not run until the flow is collected.
// The flow starts every time it is collected.
// Usually, withContext is used to change the context in the code using Kotlin coroutines, but code in the flow { ... } builder has to honor the context preservation property and is not allowed to emit from a different context.
//
class Kotlin1 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = runBlocking<Unit> {
//            suspendCheck().forEach {
//                delay(500)
//                println(it)
//            }
//            launch {
//                for (k in 1..3) {
//                    delay(400)
//                    println("I'm happy to code kotlin $k")
//                }
//            }
//            simple().collect { value ->
//                println("Yay values received from flow $value")
//            }
            //Map Operator
//            (1..5).asFlow()
//                .map { request -> performRequest(request) }
//                .collect { println(it) }
            //Transform Operator
//            (1..3).asFlow()
////            simple()
//                .filter {
//                    println("Filter $it")
//                    it % 2 == 0
//                }
//                .transform { request ->
//                    emit(request)
//                    emit(performRequest(request))
//                }.collect {
//                    println(it)
//                }
            //Limiting flow: .take(2) will absorb first 2 events only


            simpleThreadChnage()
                // .buffer { } // buffer emissions quickly, don't wait
                // .conflate { } // conflate emissions, don't process each one like debounced
                // .collectLatest { }  // cancel & restart on the latest value, instead of .collect {}
                // we have zip operator || combine operator
                //

                .collect {
                    println("Collected $it")
                }
        }

        fun simpleThreadChnage(): Flow<Int> = flow {
            for (i in 1..3) {
                delay(1000)
                println("Emitting $i")
                emit(i)
            }
        }.flowOn(Dispatchers.IO)

        fun performRequest(request: Int): Int {
            return request * 1000
        }

        suspend fun suspendCheck(): List<Int> {
            delay(1000)
            return listOf(1, 2, 3)
        }

        fun simple(): Flow<Int> = flow {
            for (i in 1..3) {
                delay(500)
                emit(i)
            }
            delay(1000)
            emit(100)
        }

    }
}

