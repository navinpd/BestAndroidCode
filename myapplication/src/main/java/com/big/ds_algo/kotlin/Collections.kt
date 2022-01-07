package com.big.ds_algo.kotlin

data class Shape(val edges: Int, val angle: Int = 0, val color: Int)

val circle = Shape(edges = 0, color = 1)
val square = Shape(edges = 4, color = 2)
val rhombus = Shape(edges = 4, angle = 45, color = 3)
val triangle = Shape(edges = 3, color = 4)
val shapes = listOf(circle, square, rhombus, triangle)

fun main() {
    println(shapes.map {it.color}.toList())

    val yellowSquareSequence = shapes.asSequence().map {
        it.copy(color = 3)
    }.first {
        it.edges == 4
    }
    println(yellowSquareSequence)

    val yellowSquareCollection = shapes.map {
        it.copy(color = 8)
    }.first {
        it.edges == 4
    }
    println(yellowSquareCollection)
}