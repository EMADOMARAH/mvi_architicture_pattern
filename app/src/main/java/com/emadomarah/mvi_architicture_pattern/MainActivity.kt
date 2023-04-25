package com.emadomarah.mvi_architicture_pattern

import android.arch.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty


class MainActivity : AppCompatActivity() {
    lateinit var numberTV: TextView
    lateinit var addNumberBtn: Button

    private val viewModel: AddNumberViewModel by lazy {
            ViewModelProviders.of(this).get(AddNumberViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberTV = findViewById(R.id.number_textview)
        addNumberBtn = findViewById(R.id.add_number_btn)

        render()
        addNumberBtn.setOnClickListener {
            //send Intent
            lifecycleScope.launch{
                viewModel.intentChannel.send(MainIntent.AddNumber)
            }
        }


    }

    private fun render(){
        //render viewState when viewModel send data
        lifecycleScope.launch{
            viewModel.state.collect{
                when(it){
                    is MainViewState.Idle -> numberTV.text = "Idle"
                    is MainViewState.Number -> numberTV.text = it.number.toString()
                    is MainViewState.Error -> numberTV.text = it.error
                }
            }

        }
    }
}




