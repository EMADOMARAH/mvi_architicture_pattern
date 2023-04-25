package com.emadomarah.mvi_architicture_pattern
//we make it sealed ? why ?
//we make it sealed cause when we use this class(MainViewState) we must
//use any thing from it not out of it :)

/*
the standard states : not added yet(idle) , result(number) , error
*/
sealed class MainViewState {
    //idle
    object Idle:MainViewState()
    //number
    data class Number(val number:Int):MainViewState()
    //error
    data class Error(val error:String):MainViewState()
}