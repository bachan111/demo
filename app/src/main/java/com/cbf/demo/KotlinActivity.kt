package com.cbf.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_kotlin.*

class KotlinActivity : AppCompatActivity() {

//    lateinit var mEditText:EditText
//    lateinit var mLoginBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        initView()
    }

    fun initView(){
        mLoginBtn.setOnClickListener{
            val mEditTextLabel = mEditText.text.toString()
            if (mEditTextLabel.isEmpty()){
                Toast.makeText(this,"输入不能为空",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(this,"输入内容："+mEditTextLabel,Toast.LENGTH_SHORT).show()

        }
    }
    
    fun funTest(valueInt:Int,valueStr:String):String{
        return ""
    }

}
