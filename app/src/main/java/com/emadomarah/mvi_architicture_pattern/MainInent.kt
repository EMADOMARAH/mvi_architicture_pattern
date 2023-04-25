package com.emadomarah.mvi_architicture_pattern

//we make it sealed ? why ?
//we make it sealed cause when we use this class(MainIntent) we must
//use any thing from it not out of it :)
sealed class MainIntent {
    object AddNumber : MainIntent()
}