package com.covalent.locationfinderapp.data.local

fun main() {
    val marks = listOf(0,1,3,4,5,6,7,8,9)

     val totalMask = marks.fold(0){
        acc, i -> acc + i
    }

    println(totalMask)
}