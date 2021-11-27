package com.example.bestandroidcode.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.bestandroidcode.data.remote.model.Cat
import com.example.bestandroidcode.data.remote.model.CatResponse
import com.example.bestandroidcode.data.remote.repository.DataRepository
import com.example.bestandroidcode.presentation.viewmodel.MainViewModel.CurrentViewState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: DataRepository) : ViewModel() {

    private val randomCatData = MutableLiveData<CurrentViewState>()
    val randomCatLiveData: LiveData<CurrentViewState>
        get() = randomCatData
    private lateinit var randCatObserver: Observer<CatResponse>

    private val categoryCatData = MutableLiveData<CurrentViewState>()
    val categoryCatLiveData: LiveData<CurrentViewState>
        get() = categoryCatData
    private lateinit var categoryCatObserver: Observer<CatResponse>

    fun getRandomCat() {
        passRandomCatState(ShowLoading)

        viewModelScope.launch(IO) {
            val catResponse = repository.fetchRandomCat()
            passRandomCatState(HideLoading)
            if (catResponse.throwable == null) {
                passRandomCatState(ShowData(catResponse.cat))
            } else {
                passRandomCatState(ShowError(catResponse.throwable!!.message ?: "Unknown Error"))
            }
        }
    }

    fun getCatByCategory(categoryId: String) {
        passCategoryViewState(ShowLoading)
        viewModelScope.launch(IO) {
            val catResponse = repository.getCatByCategory(categoryId)
            passCategoryViewState(HideLoading)

            if (catResponse.throwable == null) {
                passCategoryViewState(ShowData(catResponse.cat))
            } else {
                passCategoryViewState(ShowError(catResponse.throwable.message ?: "Unknown Error"))
            }
        }
    }

    private fun passRandomCatState(currentViewState: CurrentViewState) {
        Log.d(javaClass.simpleName, "VM state passRandomCatState $currentViewState")
        randomCatData.postValue(currentViewState)

    }

    private fun passCategoryViewState(currentViewState: CurrentViewState) {
        Log.d(javaClass.simpleName, "VM state passCategoryViewState $currentViewState")
        categoryCatData.postValue(currentViewState)

    }

    override fun onCleared() {
        if (this::categoryCatObserver.isInitialized) {
            repository.randomCatLiveData.removeObserver(categoryCatObserver)
        }
        if (this::randCatObserver.isInitialized) {
            repository.randomCatLiveData.removeObserver(randCatObserver)
        }
        super.onCleared()
    }

    sealed class CurrentViewState {
        object ShowLoading : CurrentViewState()
        object HideLoading : CurrentViewState()
        data class ShowError(val message: String) : CurrentViewState()
        data class ShowData(val item: Cat?) : CurrentViewState()
    }
}