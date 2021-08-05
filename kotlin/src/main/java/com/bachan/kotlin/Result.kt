package com.bachan.kotlin

sealed class Result

class Success(val msg:String):Result()
class Failed(val error:Exception):Result()