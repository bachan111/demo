package com.bachan.kotlin

fun String.lettersCount():Int{
    var count = 0
    for (char in this){
        if (char.isLetter()){
            count++
        }
    }
    return count
}