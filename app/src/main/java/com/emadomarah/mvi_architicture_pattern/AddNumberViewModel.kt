package com.emadomarah.mvi_architicture_pattern

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class AddNumberViewModel : ViewModel() {
    //channal to send intent from activity to viewmodel
    val intentChannel = Channel<MainIntent>(Channel.UNLIMITED)

    private val _vieState = MutableStateFlow<MainViewState>(MainViewState.Idle)
    val state : StateFlow<MainViewState> get() = _vieState

    private var number = 0
    init {
        processIntent()
    }

    //process
    private fun processIntent(){
        //when intent come from activity through  channal we need to make process first
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect{
                when (it){
                    is MainIntent.AddNumber -> addNumber()
                }
            }
        }
    }
    //reduce
    private fun addNumber(){
        viewModelScope.launch {
            _vieState.value =
                try {
                    MainViewState.Number(++number)
                }catch (e : Exception){
                    MainViewState.Error(e.message!!)
                }
        }
    }
}