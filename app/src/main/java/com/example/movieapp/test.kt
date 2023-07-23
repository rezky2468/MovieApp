package com.example.movieapp

import java.text.SimpleDateFormat

fun test() {
    val date = SimpleDateFormat("dd MMM yyyy").parse("14-02-2018")
    println(date.time)
}