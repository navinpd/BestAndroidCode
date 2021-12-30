package com.big.ds_algo.kotlin

class Learning : Learning1 {
    override var looking: String
        get() = "Hello"
        set(value){}

    override fun doSomethingElse() {

    }

    override fun letsDoSomething() {
        super.letsDoSomething()
    }

    companion object {
        private val learning = Learning()

        @JvmStatic
        fun main(args: Array<String>) {

            println(learning.letsDoSomething())
        }

        fun stuffDone(a : Learning) {
            println(a.letsDoSomething())
        }
    }
}

fun main(args: Array<String>) {
    val learning = Learning()
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


