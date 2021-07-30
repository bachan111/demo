package com.cbf.demo.kt

class LambdaDemo {

    fun main() {
        //不可变
        val list = listOf("A", "B", "C")
        for (item in list) {
            print(item)
        }

        //可变
        var multableList = mutableListOf("A", "B", "C", "D")

        val map = HashMap<String, Int>()
        map.put("A", 1)
        map.put("B", 2)
        //推荐
        map["S"] = 3//存
        val value = map["S"]//取

        //map遍历
        val map1 = mapOf("A" to 1, "B" to 1)
        for ((value, number) in map1) {
            print("data is " + value + ",number is " + number)
        }
    }

    fun maxLengthValue(){
        val list = listOf("A", "AB", "ABC")
        //第一种
        val maxLengthData = list.maxBy { it.length }

        //lambda
        val lambda = {fruit:String -> fruit.length}
//        val maxLengthData1 = list.maxBy { lambda }


        print("maxlength:"+maxLengthData)
    }
}