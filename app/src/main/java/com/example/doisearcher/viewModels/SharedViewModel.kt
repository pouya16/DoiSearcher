package com.example.doisearcher.viewModels

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doisearcher.datamodels.DataClass
import com.example.doisearcher.retrofit.DataCiteApi
import com.example.doisearcher.retrofit.DataCiteApiService
import kotlinx.coroutines.launch

class SharedViewModel:ViewModel() {


    private val _status = MutableLiveData<ConsApiStatus>()
    private val _doiList = MutableLiveData<List<DataClass>>()

    val status: LiveData<ConsApiStatus> = _status
    val doiList: LiveData<List<DataClass>> = _doiList

    fun getCons(search:String){
        viewModelScope.launch {
            _status.value = ConsApiStatus.LOADING
            try {
                val response = DataCiteApi.retrofitService.getDoisByConsortiumId(search)
                _doiList.postValue(response.data)
                _status.value = if(response.data.isEmpty()) ConsApiStatus.NO_DATA else ConsApiStatus.DONE
            }catch (e:Exception){
                _status.postValue(ConsApiStatus.ERROR)
            }

        }
    }
}



enum class ConsApiStatus{LOADING,ERROR,DONE,NO_DATA}