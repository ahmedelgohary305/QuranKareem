package com.example.qurankareem.viewModels



import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qurankareem.R
import com.example.qurankareem.data.QuranRepository
import com.example.qurankareem.data.QuranResponse
import com.example.qurankareem.data.SearchResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed class DataState<out T> {
    object Loading : DataState<Nothing>()
    data class Success<out T>(val data: T) : DataState<T>()
    object Error : DataState<Nothing>()
}


class QuranViewModel(private val repository: QuranRepository = QuranRepository()) : ViewModel() {
    private var _quranData : MutableStateFlow<QuranResponse?> = MutableStateFlow(null)
    val quranData: StateFlow<QuranResponse?> = _quranData.asStateFlow()

    private var _surahDataState : MutableStateFlow<DataState<QuranResponse?>> =
        MutableStateFlow(DataState.Loading)
    val surahDataState: StateFlow<DataState<QuranResponse?>> = _surahDataState.asStateFlow()

    private var _searchData : MutableStateFlow<SearchResponse?> = MutableStateFlow(null)


    private val _dataState = MutableStateFlow<DataState<SearchResponse?>>(DataState.Loading)
    val dataState: StateFlow<DataState<SearchResponse?>> = _dataState.asStateFlow()

    private val _isSearching: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching.asStateFlow()


    fun searchReset(){
        _searchData.value = null
    }


     fun searchQuran(query:String){
         _dataState.value = DataState.Loading
         _isSearching.value = true
         viewModelScope.launch{
             try {
                 _searchData.value = repository.searchQuran(query)
                 _dataState.value = DataState.Success(_searchData.value)
             } catch (e: Exception) {
                 _dataState.value = DataState.Error
             }
         }
    }



    init {
        _surahDataState.value = DataState.Loading
        viewModelScope.launch {
            try {
                _quranData.value = repository.getQuranData()
                _surahDataState.value = DataState.Success(_quranData.value)
            }catch (e:Exception){
                _surahDataState.value = DataState.Error
            }
        }

    }




}