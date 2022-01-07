package com.big.ds_algo.kotlin

class InterfaceLearning : Learning1 {
    override var looking: String
        get() = "Hello"
        set(value){}

    override fun doSomethingElse() {

    }

    override fun letsDoSomething() {
        super.letsDoSomething()
    }

    companion object {
        private val learning = InterfaceLearning()

        @JvmStatic
        fun main(args: Array<String>) {

            println(learning.letsDoSomething())
        }

        fun stuffDone(a : InterfaceLearning) {
            println(a.letsDoSomething())
        }
    }
}

fun main(args: Array<String>) {
    val learning = InterfaceLearning()
    println(learning.looking)
}

interface Learning1 {
    val looking : String

    fun letsDoSomething() {
    }

    fun doSomethingElse()

}

abstract class ToDo {
    var log = "Looking"
    val print = "Playing"

    fun letsMeet() {

    }

    abstract fun nonImpl()
}

class TodoImpl : ToDo() {
    override fun nonImpl() {

    }
}


