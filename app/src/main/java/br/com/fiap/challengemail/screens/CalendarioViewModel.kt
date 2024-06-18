package br.com.fiap.challengemail.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalendarioViewModel : ViewModel(){
    private val _title = MutableLiveData<String>("")
    val title : LiveData<String> = _title

    fun setEvent(value: String){
        _title.value = value
    }

    private val _initialDate = MutableLiveData<String>("")
    val initialDate : LiveData<String> = _initialDate

    fun setInitialDate(value: String){
        _initialDate.value = value
    }

    private val _finalDate = MutableLiveData<String>("")
    val finalDate : LiveData<String> = _finalDate

    fun setFinalDate(value: String){
        _finalDate.value = value
    }

    private val _formError = MutableLiveData<String>("")
    val formError : LiveData<String> = _formError

    fun setFormError(value: String){
        _formError.value = value
    }


}
