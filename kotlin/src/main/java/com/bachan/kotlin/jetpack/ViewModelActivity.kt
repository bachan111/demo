package com.bachan.kotlin.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bachan.kotlin.R
import kotlinx.android.synthetic.main.activity_view_model.*

class ViewModelActivity : AppCompatActivity() {
    lateinit var viewModel: ViewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
//        viewModel = ViewModelProvider(this).get(ViewViewModel::class.java)
        val  aa = 0
        viewModel = ViewModelProvider(this,ViewViewModelFactory(aa)).get(ViewViewModel::class.java)

        plusBtn.setOnClickListener {
            viewModel.counter++
            refreshCounter()
        }
    }

    private fun refreshCounter(){
        infoText.setText(viewModel.counter.toString())
    }
}