package com.bachan.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: RecycAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("activity",javaClass.simpleName)
        printParams(22)
        printParams1(num = 22)
        initView()
        val  count = "ADAD".lettersCount()

        if (!::adapter.isInitialized){
            // TODO: 2021/8/4 init adapter
        }
    }

    fun getResult(result: Result) = when (result){
        is Success -> result.msg
        is Failed -> result.error
    }

    private fun initView() {
        mButton.setOnClickListener {gotoSecondActivity() }
    }

    fun gotoSecondActivity() {
        val intent = Intent("com.bachan.kotlin.secondActivity")
        startActivity(intent)
    }

    fun gotoSecondActivityWithData(){
        SecondActivity.start(this,"hello ketty !")
    }

    /**
     * 设置默认参数
     */
    fun printParams(num: Int, str: String = "hello") {
        print("sum is $num,str is $str")
    }

    fun printParams1(str: String = "hello", num: Int) {
        print("sum is $num,str is $str")
    }

    fun withTest(){
        val list = listOf("a","b","c")
        val result = with(StringBuilder()){
            append("start=====")
            for (value in list){
                append(value).append("\n")
            }
            append("end=======")
            toString()
        }
        print(result)
    }

    fun runTest(){
        val list = listOf("a","b","c")
        val result = StringBuilder().run {
            append("start=====")
            for (value in list){
                append(value).append("\n")
            }
            append("end=======")
            toString()
        }
        print(result)
    }

    fun applyTest(){
        val list = listOf("a","b","c")
        val result = StringBuilder().apply {
            append("start=====")
            for (value in list){
                append(value).append("\n")
            }
            append("end=======")
        }
        print(result.toString())

        doAction()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> Toast.makeText(this, "add", Toast.LENGTH_LONG).show()
            R.id.remove -> Toast.makeText(this, "remove", Toast.LENGTH_LONG).show()
        }
        return true
    }
}