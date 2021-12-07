package com.big.ds_algo.kotlin

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

// Code inside the flow { ... } builder block can suspend.
// Values are collected from the flow using collect function.
// Flows are cold streams similar to sequences — the code inside a flow builder does not run until the flow is collected.
// The flow starts every time it is collected.
// Usually, withContext is used to change the context in the code using Kotlin coroutines, but code in the flow { ... } builder has to honor the context preservation property and is not allowed to emit from a different context.
//
class Flow_Learning {

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

//            simpleThreadChnage()
//                // .buffer { } // buffer emissions quickly, don't wait
//                // .conflate { } // conflate emissions, don't process each one like debounced
//                // .collectLatest { }  // cancel & restart on the latest value, instead of .collect {}
//                // we have zip operator || combine operator
//                .collect {
//                    println("Collected $it")
//                }
            //operators
//            val nums = (1..3).asFlow().onEach { delay(200) }
//            val strs = flowOf("One", "Two", "Three").onEach { delay(400) }
//            nums.zip(strs) { a, b ->
//                "$a and $b"
//            }.collect { println("Zip $it") }
//
//            nums.combine(strs) { a, b ->
//                "$a & $b"
//            }.collect { println("Combine $it") }
            //flatMapConcat
//            val startTime = System.currentTimeMillis()
//            (1..3).asFlow().onEach{ delay(100)}
//                .flatMapConcat{ requestFlow(it) }
//                .collect { value ->
//                    println("$value at ${System.currentTimeMillis() - startTime} ms from Start")
//                }
            //flatMapMerge
//            val startTime = System.currentTimeMillis()
//            (1..3).asFlow().onEach{ delay(100)}
//                .flatMapMerge{ requestFlow(it) }
//                .collect { value ->
//                    println("$value at ${System.currentTimeMillis() - startTime} ms from Start")
//                }
            //flatMapLatest

//            simpleWithException()
//                .onCompletion { cause -> if (cause != null) println("Flow completed successfully") }
//                .catch { cause -> println("Caught exception ${cause.message}") }
//                .collect { value -> println(value) }
            events()
                .onEach { println("Event $it") }
                .launchIn(this)
            println("Done")
        }

        fun events(): Flow<Int> = (1..3).asFlow().onEach { delay(100) }

        fun simpleWithException(): Flow<Int> = flow {
            emit(12)
            throw RuntimeException("I can throw exceptions")
        }

        fun requestFlow(int: Int): Flow<String> = flow {
            emit("First $int")
            delay(500)
            emit("Second $int")
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

