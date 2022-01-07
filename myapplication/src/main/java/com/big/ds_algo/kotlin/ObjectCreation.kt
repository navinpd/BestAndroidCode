package com.big.ds_algo.kotlin

class ObjectCreation {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val i1: Int? = 200
            val i2: Int? = 200
            println(i1 == i2) // true
            println(i1 === i2) //false //127 is threshold below which it's true. The values are cached

            val ob = ObjectCreation()
            ob.pair.first
            ob.pair.second
            var (one, two) = ob.pair

            val l = listOf<Int>(1,2,2)
        }
    }

    sealed class LinkedList<out T>

    class Node<T>(val head: T, val tail: LinkedList<T>) : LinkedList<T>()

    object Empty : LinkedList<Nothing>()

    // Usage
    val list1: LinkedList<Int> = Node(1, Node(2, Node(3, Empty)))
    val list2: LinkedList<String> = Node("A", Node("B", Empty))

    val pair = 1 to false

    interface Source<out T> {
        fun nextT(): T
    }

    fun demo(strs: Source<String>) {
        val objects: Source<Any> = strs // This is OK, since T is an out-parameter
        // ...
    }

    interface Comparable<in T> {
        operator fun compareTo(other: T): Int
    }

    fun demo(x: Comparable<Number>) {
        x.compareTo(1.0) // 1.0 has type Double, which is a subtype of Number
        // Thus, you can assign x to a variable of type Comparable<Double>
        val y: Comparable<Double> = x // OK!
    }

    //Java's Array<? extends Object>
    fun copy(from: Array<out Any>, to: Array<Any>) {}

    //Java's Array<? super String>
    fun fill(dest: Array<in String>, value: String) {}



}