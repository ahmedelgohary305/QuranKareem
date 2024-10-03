package com.example.qurankareem.HomePage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf

class TabsStateHandler{
    private val _selectedTabIndex: MutableState<Int> = mutableStateOf(0)
    val selectedTabIndex: State<Int> = _selectedTabIndex


    fun changeIndex(index:Int){
        _selectedTabIndex.value = index

    }
}