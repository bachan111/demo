package com.bachan.kotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SecondActivity : AppCompatActivity() {

    companion object{
        fun start(context:Context,data:String){
            val intent = Intent(context,SecondActivity::class.java)
            intent.putExtra("data",data)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sceond)
        val data = intent.getStringExtra("data")
    }
}